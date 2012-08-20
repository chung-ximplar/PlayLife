package com.playlife.presentation.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.playlife.presentation.converters.JSONConverter;
import com.playlife.utility.LocaleService;
import com.playlife.utility.Request;
import com.playlife.utility.exceptions.LogicException;
import com.playlife.utility.exceptions.PersistenceException;
import com.playlife.utility.exceptions.PresentationException;
import com.playlife.utility.exceptions.ValidationException;

@Controller
@RequestMapping(value="/error")
public class ErrorController {
	@Autowired
	MessageSource messageSource;
	
	private static final Logger log = Logger.getLogger("Error"); 
	
	@RequestMapping(value="/*")
	protected String registerRequest(String s_displayMessage, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);
		model.put("persistence", Request.getArrayObject(request, "s_persistence", PersistenceException.class));
		model.put("logic", Request.getArrayObject(request, "s_logic", LogicException.class));
		model.put("presentation", Request.getArrayObject(request, "s_presentation", PresentationException.class));
		model.put("validation", Request.getArrayObject(request, "s_validation", ValidationException.class));
		model.put("exception", Request.getArrayObject(request, "s_exception", Exception.class));
		model.put("displayMessage", s_displayMessage);
		
		String s_persistence = Request.getString(request, "s_persistence");
		String s_logic = Request.getString(request, "s_logic");
		String s_presentation = Request.getString(request, "s_presentation");
		String s_validation = Request.getString(request, "s_validation");
		String s_exception = Request.getString(request, "s_exception");
		
		model.put("s_log", "Persistence : " + s_persistence.replace("'", "\"") + " Logic : " + s_logic.replace("'", "\"") + " Presentation : " + s_presentation.replace("'", "\"") + " Validation : " + s_validation.replace("'", "\"") + " Exception : " + s_exception.replace("'", "\""));
		return "error/show";
	}
	
	@RequestMapping(value="/log.json")
	@ResponseBody
	protected String logRequest(String s_log, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		LocaleService.resolve(request, response);
		
		log.info(s_log);
		JSONObject obj_return = new JSONObject();
		obj_return.put("status", "ok");
		return obj_return.toString();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handlerException(HttpServletRequest request, Exception ex){
		JSONObject obj_return = new JSONObject();
		obj_return.put("error", JSONConverter.constructError(ex, messageSource, LocaleService.getLocale(request)));
		obj_return.put("status", "error");
		return obj_return.toString();
	}
}
