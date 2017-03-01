package com.hullo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
}
