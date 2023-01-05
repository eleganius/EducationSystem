package com.example.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.Material;

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

	@GetMapping("/add")
	public String addGet() {
		return "admin/add-material";
	}

	@PostMapping("/add")
	public String addPost(
			Material material,
			HttpSession session) {

		if(material.getName().isBlank()) {
			return "admin/add-material";
		}

		session.setAttribute("material", material);

		return "redirect:/admin/material/list";
	}

	@GetMapping("/edit/{id}")
	public String editGet(
			@PathVariable Integer id,
			Model model) {

		return "admin/edit-material";
	}

	@PostMapping("/edit/{id}")
	public String editPost(
			Material material,
			HttpSession session) {

		if(material.getName().isBlank()) {
			return "admin/edit-material";
		}

		session.setAttribute("material", material);

		return "redirect:/admin/material/list";
	}


}
