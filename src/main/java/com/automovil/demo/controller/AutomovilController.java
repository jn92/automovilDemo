package com.automovil.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.servlet.ModelAndView;

import com.automovil.demo.constant.ViewConstant;
import com.automovil.demo.dto.CreateCarDto;
import com.automovil.demo.dto.ViewCarDto;
import com.automovil.demo.exception.ServiceException;
import com.automovil.demo.message.MessageList;
import com.automovil.demo.service.CarService;
import com.automovil.demo.service.OptionalService;
import com.automovil.demo.service.VariantModelService;

@Controller
@RequestMapping("/automovil")
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
	private CarService carService;

	@GetMapping("/viewcar")
	public String viewList(Model model, MessageList messageList) throws ServiceException {
//		LOG.in
		List<ViewCarDto> list = carService.ViewCarList();
		if (list.size() == 0) {
			messageList.addInformation("Altas Pendientes:",
					"No se encontraron resultados relacionados con su b&uacute;squeda.");
			model.addAttribute("messageList", messageList);
		}
		model.addAttribute("carList", carService.ViewCarList());
		return ViewConstant.CONTACTS;
	}


	@GetMapping("/car")
	public String createCar(Model model) throws ServiceException {
		model.addAttribute("createCar", new CreateCarDto());
		model.addAttribute("optionalList", optionalService.optionalList());
		model.addAttribute("variantModelList", variantModelService.getVariantModelList());
		return ViewConstant.CONTACTS_FROM;
	}

	@PostMapping("/car/create")
	public String createCar(@ModelAttribute("createCar") CreateCarDto createCarDto, Model model)
			throws ServiceException {
		LOG.info("METHOD: createCar() -- PARAMS:" + createCarDto.toString());
//		try {
//			if (result.hasErrors()) {
//				MessageList messageList = new MessageList();
//				messageList.addWarning("Creación Automovil:", "Por favor, Complete Los campos obligatorios.");
//				model.addAttribute("model", createCarDto);
//				model.addAttribute("messageList", messageList);
//				model.addAttribute("variantModelList", variantModelService.getVariantModelList());
//				model.addAttribute("optionalList", optionalService.optionalList());
//			}
			carService.CreateCar(createCarDto);
//		} catch (ServiceException e) {
//			model.addAttribute("model", createCarDto);
//			model.addAttribute("messageList", e.getMessageList());
//			return ViewConstant.CONTACTS_FROM;
//		}
//		MessageList messageList = new MessageList();
//		messageList.addInformation("Creacón Automovil:", "Su creación fue exitosa.");
		model.addAttribute("result", 1);
		return ViewConstant.CONTACTS;
	}

	@PutMapping("/car/update/{carId}")
	public String updateCar(@PathVariable Integer carId, Model model) {
		return null;
	}

	@DeleteMapping("/car/delete/{carId}")
	public String DeleteCar(@PathVariable Integer carId, Model model) {
		return null;
	}
	
	@GetMapping("/cancel")
	public String cancel(Model model, MessageList messageList) throws ServiceException {
		return viewList(model, messageList);
	}
}
