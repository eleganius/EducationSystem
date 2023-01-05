package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/material") //URL
public class MaterialController {

	@GetMapping("/list") //URL
	public String list() {
		return "admin/list-material";//File
	}

	@GetMapping("/show/{id}")
	public String show(
			@PathVariable Integer id,
			Model model) {

		return "admin/show-material";

	}

}
