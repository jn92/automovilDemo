package com.automovil.demo.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.automovil.demo.constant.ViewConstant;
import com.automovil.demo.dto.GeneralCarDto;
import com.automovil.demo.dto.ViewCarDto;
import com.automovil.demo.exception.ServiceException;
import com.automovil.demo.message.MessageList;
import com.automovil.demo.service.CarService;
import com.automovil.demo.service.ICarService;
import com.automovil.demo.service.OptionalService;
import com.automovil.demo.service.VariantModelService;

@Controller
@RequestMapping("/automovil")
@PreAuthorize("permitAll()")
public class AutomovilController {

	private static final Log LOG = LogFactory.getLog(AutomovilController.class);

	@Autowired
	@Qualifier("optionalService")
	private OptionalService optionalService;

	@Autowired
	@Qualifier("variantModelService")
	private VariantModelService variantModelService;

	@Autowired
	@Qualifier("carService")
	private ICarService carService;

	@GetMapping("/viewcar")
	public String viewList(Model model, MessageList messageList) throws ServiceException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userName", user.getUsername());
		model.addAttribute("carList", carService.ViewCarList());
		return ViewConstant.CARS;
	}


	@GetMapping("/car/detail")
	public String detailCar(Model model,@RequestParam(name= "carId", required= false) Integer carId) throws ServiceException {
		GeneralCarDto generalCarDto = carService.ViewCarDetail(carId);
		model.addAttribute("optionRequestId", generalCarDto.getOptionalListId());
		model.addAttribute("optionalList", optionalService.optionalList());
		model.addAttribute("variantModelList", variantModelService.getVariantModelList());
		model.addAttribute("createCar",generalCarDto);
		return ViewConstant.CAR_VIEW;
	}

	@GetMapping("/car")
	public String createCar(Model model,@RequestParam(name= "carId", required= false) Integer carId) throws ServiceException {
		model.addAttribute("optionalList", optionalService.optionalList());
		model.addAttribute("variantModelList", variantModelService.getVariantModelList());
		model.addAttribute("createCar",new GeneralCarDto());
		return ViewConstant.CAR_CREATE;
	}
	
	@PostMapping("/car/create")
	public String createCar(@ModelAttribute("createCar") GeneralCarDto generalCarDto, Model model, BindingResult result)
			throws ServiceException {
		LOG.info("METHOD: createCar() -- PARAMS:" + generalCarDto.toString());
		try {
			if (result.hasErrors()) {
				MessageList messageList = new MessageList();
				messageList.addWarning("Creación Automovil:", "Por favor, Complete Los campos obligatorios.");
				model.addAttribute("model", generalCarDto);
				model.addAttribute("messageList", messageList);
				model.addAttribute("variantModelList", variantModelService.getVariantModelList());
				model.addAttribute("optionalList", optionalService.optionalList());
			}
			carService.CreateCar(generalCarDto);
		} catch (ServiceException e) {
			model.addAttribute("model", generalCarDto);
			model.addAttribute("messageList", e.getMessageList());
			return "redirect:/automovil/car";
		}
		MessageList messageList = new MessageList();
		messageList.addInformation("Creacón Automovil:", "Su creación fue exitosa.");
		model.addAttribute("result", 1);
		return viewList(model, messageList);
	}

	@GetMapping("/car/update")
	public String updateCar(Model model,@RequestParam(name= "carId", required= false) Integer carId) throws ServiceException {
		GeneralCarDto generalCarDto = new GeneralCarDto();
		if(carId != null) {
			generalCarDto = carService.ViewCarDetail(carId);
		}
		model.addAttribute("optionalList", optionalService.optionalList());
		model.addAttribute("variantModelList", variantModelService.getVariantModelList());
		model.addAttribute("updateCar",generalCarDto);
		return ViewConstant.CAR_UPDATE;
	}
	
	@PostMapping("/car/update")
	public String updateCar(@ModelAttribute("updateCar") GeneralCarDto generalCarDto, Model model, BindingResult result) throws ServiceException {
		LOG.info("METHOD: updateCar() -- PARAMS:" + generalCarDto.toString());
		try {
			if (result.hasErrors()) {
				MessageList messageList = new MessageList();
				messageList.addWarning("Creación Automovil:", "Por favor, Complete Los campos obligatorios.");
				model.addAttribute("model", generalCarDto);
				model.addAttribute("messageList", messageList);
				model.addAttribute("variantModelList", variantModelService.getVariantModelList());
				model.addAttribute("optionalList", optionalService.optionalList());
			}
			carService.UpdateCar(generalCarDto);
		} catch (ServiceException e) {
			model.addAttribute("model", generalCarDto);
			model.addAttribute("messageList", e.getMessageList());
			return "redirect:/automovil/car";
		}
		MessageList messageList = new MessageList();
		messageList.addInformation("Creacón Automovil:", "Su creación fue exitosa.");
		model.addAttribute("result", 1);
		return viewList(model, messageList);
	}

	@GetMapping("/car/delete")
	public String DeleteCar(@RequestParam(name= "carId") Integer carId) throws ServiceException {
		carService.DeleteCar(carId);
		return "redirect:/automovil/viewcar";
	}
	
	@GetMapping("/cancel")
	public String cancel() throws ServiceException {
		return "redirect:/automovil/viewcar";
	}
}
