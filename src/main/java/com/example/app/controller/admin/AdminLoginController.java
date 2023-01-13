package com.example.app.controller.admin;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Admin;
import com.example.app.domain.Login;
import com.example.app.login.LoginAuthority;
import com.example.app.login.LoginStatus;
import com.example.app.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

	@Autowired
	AdminService service;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute(new Login());
		return "admin/login-admin";
	}

	@PostMapping("/login")
	public String login(
			HttpSession session,
			@Valid Login login,
			Errors errors) throws Exception {

		if (errors.hasErrors()) {
			return "admin/login-admin";
		}

		Admin admin = service.getAdminByLoginId(login.getLoginId());
		if (admin == null || !login.isCorrectPassword(admin.getLoginPass())) {
			errors.rejectValue("loginId", "error.incorrect_id_or_password");
			return "admin/login-admin";
		}

		// セッションに認証情報を格納
		LoginStatus loginStatus = new LoginStatus(admin.getId(), admin.getName(),
				admin.getLoginId(), LoginAuthority.ADMIN);
		session.setAttribute("loginStatus", loginStatus);
		return "redirect:/admin/material/list";
	}

	@GetMapping("/logout")
	public String logout(
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		session.removeAttribute("loginStatus");
		redirectAttributes.addFlashAttribute("message", "ログアウトしました。");
		return "redirect:/admin/login";
	}

}
