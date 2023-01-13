package com.example.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.login.LoginStatus;
import com.example.app.service.MaterialService;
import com.example.app.service.RentalRecordService;

@Controller
public class RentalController {

	private static final int NUM_PER_PAGE = 5;
	private static final int BORROWABLE_LIMIT = 3;

	@Autowired
	MaterialService materialService;

	@Autowired
	RentalRecordService rentalRecordService;

	@GetMapping("/rental")
	public String rental(
			HttpSession session,
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			Model model) throws Exception {

		// セッション（生徒IDを含んでいる）を取得
		LoginStatus loginStatus = (LoginStatus) session.getAttribute("loginStatus");

		// 全体のページ数
		int totalPages = materialService.getTotalBorrowableMaterialPages(NUM_PER_PAGE);
		model.addAttribute("totalPages", totalPages);

		// 現在のページ番号
		model.addAttribute("currentPage", page);

		// 現在借りている教材のリスト
		model.addAttribute("borrowingList",
				materialService.getBorrowingMaterialList(loginStatus.getId()));

		// 貸し出し可能な教材のリスト
		model.addAttribute("materialList",
				materialService.getBorrowableMaterialListPerPage(page, NUM_PER_PAGE));

		return "list-rental";
	}

	// 借りるボタン
	@GetMapping("/rental/borrow/{materialId}")
	public String borrowMaterial(
			HttpSession session,
			@PathVariable("materialId") Integer materialId) throws Exception {
		LoginStatus loginStatus = (LoginStatus) session.getAttribute("loginStatus");

		// 借りている教材数が最大値を超えていないか確認
		if (!rentalRecordService.isAbleToBorrow(loginStatus.getId(), BORROWABLE_LIMIT)) {
			System.out.println("借りられる教材数を超えている");
			return "redirect:/rental";
		}

		// 借りようとしている教材が貸し出されていないか確認
		if (!materialService.isBorrowable(materialId)) {
			System.out.println("削除済み、または貸し出し中");
			return "redirect:/rental";
		}

		// 問題がなければ、借りる処理実行
		rentalRecordService.borrowMaterial(loginStatus.getId(), materialId);
		return "redirect:/rental";
	}

	// 返却ボタン
	@GetMapping("/rental/return/{materialId}")
	public String returnMaterial(
			HttpSession session,
			@PathVariable("materialId") Integer materialId) throws Exception {

		// 本人による返却か確認
		LoginStatus loginStatus = (LoginStatus) session.getAttribute("loginStatus");

		if (!rentalRecordService.byAuthenticatedStudent(loginStatus.getId(), materialId)) {
			System.out.println("他の生徒による返却");
			return "redirct:/rental";
		}

		rentalRecordService.returnMaterial(materialId);
		return "redirect:/rental";
	}

}
