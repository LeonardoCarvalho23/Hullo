package com.hullo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hullo.service.AlunoService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
}
