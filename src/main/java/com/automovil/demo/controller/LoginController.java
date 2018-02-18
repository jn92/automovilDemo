package com.automovil.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.automovil.demo.constant.ViewConstant;
import com.automovil.demo.dto.UserCredential;

@Controller
public class LoginController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@GetMapping("/")
	public String redirectToLogin() {
		LOG.info("METHOD: redirectToLogin()");
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginFrom(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		LOG.info("METHOD: showLoginFrom() -- >PARAM: error=" + error + "logout:" + logout);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		model.addAttribute("userCredentials", new UserCredential());
		LOG.info("returning to login view");
		return ViewConstant.LOGIN;
	}

	@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name = "userCredentials") UserCredential userCredential) {
		LOG.info("METHOD: loginCheck() -- >PARAM: error=" + userCredential);
		if (userCredential.getUserName().replace(" ", "").equals("user")
				&& userCredential.getPassword().equals("user")) {
			LOG.info("returning to login view");
			return "contacts";
		}
		LOG.info("returning to login?error");
		return "redirect:/login?error";
	}
}
