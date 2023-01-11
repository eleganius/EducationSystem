package com.example.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Autowired
	MaterialService service;

	@GetMapping("/list")
	public String list(
			@RequestParam(name = "status", required = false) String status,
			Model model) throws Exception {
		model.addAttribute("statusMessage", getStatusMessage(status));
		model.addAttribute("materials", service.getMaterialList());
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
	public String addGet() {
		return "admin/material/add-material";
	}

	@PostMapping("/add")
	public String addPost(
			Material material,
			HttpSession session) {

		if (material.getName().isBlank()) {
			return "admin/material/add-material";
		}

		session.setAttribute("material", material);

		return "redirect:/admin/material/list";
	}

	@GetMapping("/edit/{id}")
	public String editGet(
			@PathVariable Integer id,
			Model model) {

		return "admin/material/edit-material";
	}

	@PostMapping("/edit/{id}")
	public String editPost(
			Material material,
			HttpSession session) {

		if (material.getName().isBlank()) {
			return "admin/material/edit-material";
		}

		session.setAttribute("material", material);

		return "redirect:/admin/material/list";
	}

}
