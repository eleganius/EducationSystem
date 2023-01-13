package com.example.app.controller.admin;

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
import com.example.app.service.RentalRecordService;

@Controller
@RequestMapping("/admin/material")
public class MaterialController {

	private static final int NUM_PER_PAGE = 5;
	private static final int RECORD_NUM = 3;

	@Autowired
	MaterialService service;

	@Autowired
	RentalRecordService rentalRecordService;

	@GetMapping("/list")
	public String list(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			Model model) throws Exception {
		int totalPages = service.getTotalPages(NUM_PER_PAGE);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", page);
		model.addAttribute("materialList", service.getMaterialListPerPage(page, NUM_PER_PAGE));
		return "admin/list-material";
	}

	@GetMapping("/show/{id}")
	public String show(
			@PathVariable Integer id,
			Model model) throws Exception {
		model.addAttribute("material", service.getMaterialById(id));
		model.addAttribute("recordList",
				rentalRecordService.getLatestRentalRecordListByMaterialId(id, RECORD_NUM));
		return "admin/show-material";
	}

	@GetMapping("/add")
	public String add(Model model) throws Exception {
		model.addAttribute("material", new Material());
		model.addAttribute("materialTypeList", service.getMaterialTypeList());
		return "admin/add-material";
	}

	@PostMapping("/add")
	public String add(
			@Valid Material material,
			Errors errors,
			Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		if (errors.hasErrors()) {
			model.addAttribute("materialTypeList", service.getMaterialTypeList());
			return "admin/add-material";
		}

		service.addMaterial(material);
		redirectAttributes.addFlashAttribute("message", "教材を追加しました。");
		return "redirect:/admin/material/list";
	}

	@GetMapping("/edit/{id}")
	public String edit(
			@PathVariable Integer id,
			Model model) throws Exception {
		model.addAttribute("material", service.getMaterialById(id));
		model.addAttribute("materialTypeList", service.getMaterialTypeList());
		return "admin/edit-material";
	}

	@PostMapping("/edit/{id}")
	public String edit(
			@PathVariable Integer id,
			@Valid Material material,
			Errors errors,
			Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		if (errors.hasErrors()) {
			model.addAttribute("materialTypeList", service.getMaterialTypeList());
			return "admin/edit-material";
		}

		service.editMaterial(material);
		redirectAttributes.addFlashAttribute("message", "教材を編集しました。");
		return "redirect:/admin/material/list";
	}

	@GetMapping("/delete/{id}")
	public String delete(
			@PathVariable Integer id,
			RedirectAttributes redirectAttributes) throws Exception {
		service.deleteMaterialById(id);
		redirectAttributes.addFlashAttribute("message", "教材を削除しました。");
		return "redirect:/admin/material/list";
	}

}
