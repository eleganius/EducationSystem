package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Material;
import com.example.app.service.MaterialService;

@Controller
@RequestMapping("/admin/material")
public class MaterialController {

	private static final int NUM_PER_PAGE = 5;

	@Autowired
	MaterialService service;

	@GetMapping("/list")
	public String list(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "status", required = false) String status,
			Model model) throws Exception {
		model.addAttribute("materials", service.getMaterialListByPage(page, NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("totalPages", service.getTotalPages(NUM_PER_PAGE));
		model.addAttribute("statusMessage", getStatusMessage(status));
		return "admin/material/list-material";
	}

	//status の値に応じたメッセージを作成する
	private String getStatusMessage(String status) {
		String message = null;
		if (status == null) {
			return message;
		}
		switch (status) {
		case "add":
			message = "教材を追加しました。";
			break;
		case "edit":
			message = "教材情報を更新しました。";
			break;
		case "delete":
			message = "教材情報を削除しました。";
			break;
		}
		return message;
	}

	@GetMapping("/show/{id}")
	public String show(
			@PathVariable Integer id,
			Model model) throws Exception {
		model.addAttribute("material", service.getMaterialById(id));
		return "admin/material/show-material";
	}

	@GetMapping("/add")
	public String addGet(Model model) throws Exception {
		model.addAttribute("material", new Material());
		model.addAttribute("types", service.getTypeList());
		return "admin/material/add-material";
	}

	@PostMapping("/add")
	public String addPost(
			@Validated Material material,
			Errors errors,
			Model model) throws Exception {
		if (errors.hasErrors()) {
			model.addAttribute("types", service.getTypeList());
			return "admin/material/add-material";
		}

		service.addMaterial(material);
		return "redirect:/admin/material/list?status=add";
	}

	@GetMapping("/edit/{id}")
	public String editGet(
			@PathVariable Integer id,
			Model model) throws Exception {
		model.addAttribute("material", service.getMaterialById(id));
		model.addAttribute("types", service.getTypeList());
		return "admin/material/edit-material";
	}

	@PostMapping("/edit/{id}")
	public String editPost(
			@PathVariable Integer id,
			@Validated Material material,
			Errors errors,
			Model model) throws Exception {
		if (errors.hasErrors()) {
			model.addAttribute("types", service.getTypeList());
			return "admin/material/edit-material";
		}

		material.setId(id);
		service.editMaterial(material);
		return "redirect:/admin/material/list?status=edit";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) throws Exception {
		service.deleteMaterial(id);
		return "redirect:/admin/material/list?status=delete";
	}

}
