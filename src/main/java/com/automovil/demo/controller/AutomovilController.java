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
	public ModelAndView viewList(MessageList messageList) throws ServiceException {
		ModelAndView mav = new ModelAndView("contacts");
		List<ViewCarDto> list =carService.ViewCarList();
		if(list.size() == 0) {
			 messageList.addInformation("Altas Pendientes:", "No se encontraron resultados relacionados con su b&uacute;squeda.");
			 mav.addObject("messageList", messageList);
		}
		mav.addObject("carList", carService.ViewCarList());
		return mav;
	}
	
	@GetMapping("/viewlist")
	public ModelAndView viewCarDetail(MessageList messageList) throws ServiceException {
		ModelAndView mav = new ModelAndView("contacts");
		List<ViewCarDto> list =carService.ViewCarList();
		if(list.size() == 0) {
			 messageList.addInformation("Altas Pendientes:", "No se encontraron resultados relacionados con su b&uacute;squeda.");
			 mav.addObject("messageList", messageList);
		}
		mav.addObject("carList", list);
		return mav;
	}
	
	@GetMapping("/car")
	public ModelAndView createCar() throws ServiceException {
		ModelAndView mav = new ModelAndView("contactform");
		mav.addObject("optionalList", optionalService.optionalList());
		mav.addObject("variantModelList", variantModelService.getVariantModelList());
		return mav;
	}
	
	@PostMapping("/car/create/")
	public String createCar(@Valid @ModelAttribute("model") CreateCarDto dto,
			BindingResult result, 
			Model model) throws ServiceException {
		try {
			if(result.hasErrors()) {
				MessageList messageList = new MessageList();
				messageList.addWarning("Creación Automovil:","Por favor, Complete Los campos obligatorios.");
				model.addAttribute("model", dto);
				model.addAttribute("messageList", messageList);
				model.addAttribute("variantModelList", variantModelService.getVariantModelList());
				model.addAttribute("optionalList", optionalService.optionalList());
			}
			carService.CreateCar(dto);
		} catch (ServiceException e) {
			model.addAttribute("model", dto);
			model.addAttribute("messageList", e.getMessageList());
			return "contactform";
		}
		MessageList messageList = new MessageList();
		messageList.addInformation("Creacón Automovil:","Su creación fue exitosa.");
		return "contacts";
	}
	
	@PutMapping("/car/update/{carId}")
	public String updateCar(@PathVariable Integer carId, Model model) {
		return null;	
	}
	
	@DeleteMapping("/car/delete/{carId}")
	public String DeleteCar(@PathVariable Integer carId, Model model) {
		return null;
	}
}
