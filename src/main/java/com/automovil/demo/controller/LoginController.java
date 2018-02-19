package com.automovil.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.automovil.demo.constant.ViewConstant;

@Controller
public class LoginController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@GetMapping("/login")
	public String showLoginFrom(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		LOG.info("METHOD: showLoginFrom() -- >PARAM: error=" + error + "logout:" + logout);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		LOG.info("returning to login view");
		return ViewConstant.LOGIN;
	}

	@GetMapping({"/loginsuccess","/"})
	public String loginCheck() {
		LOG.info("METHOD: loginCheck() -- >PARAM: error=" );
//		if (userCredential.getUserName().replace(" ", "").equals("user")
//				&& userCredential.getPassword().equals("user")) {
		LOG.info("returning to login view");
		return "redirect:/automovil/viewcar";
//		}
//		LOG.info("returning to login?error");
//		return "redirect:/login?error";
	}
}
