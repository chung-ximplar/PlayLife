package com.playlife.presentation.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playlife.presentation.converters.JSONConverter;
import com.playlife.utility.LocaleService;

@Controller
@RequestMapping("/*")
public class MainController {
	ObjectMapper mapper = JSONConverter.mapper;
	
	@RequestMapping(value="/*")
	protected String registerRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);

		return "home";
	}
	
	@RequestMapping(value="/language")
	protected String languageRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);

		return "language";
	}
}
