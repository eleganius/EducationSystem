package com.example.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			Model model) throws Exception {
		model.addAttribute("totalPages", service.getTotalPages(NUM_PER_PAGE));
		model.addAttribute("page", page);
		model.addAttribute("materials", service.getMaterialListByPage(page, NUM_PER_PAGE));
		return "admin/material/list-material";
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
			@Valid Material material,
			Errors errors,
			Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		if (errors.hasErrors()) {
			model.addAttribute("types", service.getTypeList());
			return "admin/material/add-material";
		}

		service.addMaterial(material);
		redirectAttributes.addFlashAttribute("message", "教材を追加しました。");
		return "redirect:/admin/material/list";
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
			@Valid Material material,
			Errors errors,
			Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		if (errors.hasErrors()) {
			model.addAttribute("types", service.getTypeList());
			return "admin/material/edit-material";
		}

		service.editMaterial(material);
		redirectAttributes.addFlashAttribute("message", "教材を編集しました。");
		return "redirect:/admin/material/list";
	}

	@GetMapping("/delete/{id}")
	public String delete(
			@PathVariable Integer id,
			RedirectAttributes redirectAttributes) throws Exception {
		service.deleteMaterial(id);
		redirectAttributes.addFlashAttribute("message", "教材を削除しました。");
		return "redirect:/admin/material/list";
	}

}
