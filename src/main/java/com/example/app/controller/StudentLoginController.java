package com.example.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Login;
import com.example.app.domain.Student;
import com.example.app.login.LoginAuthority;
import com.example.app.login.LoginStatus;
import com.example.app.service.StudentService;

@Controller
public class StudentLoginController {

	@Autowired
	StudentService service;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute(new Login());
		return "login-student";
	}

	@PostMapping("/login")
	public String login(
			HttpSession session,
			@Valid Login login,
			Errors errors) throws Exception {

		if (errors.hasErrors()) {
			return "login-student";
		}

		Student student = service.getStudentByLoginId(login.getLoginId());
		if (student == null || !login.isCorrectPassword(student.getLoginPass())) {
			errors.rejectValue("loginId", "error.incorrect_id_or_password");
			return "login-student";
		}

		LoginStatus loginStatus = new LoginStatus(student.getId(),
				student.getName(), student.getLoginId(), LoginAuthority.STUDENT);
		session.setAttribute("loginStatus", loginStatus);
		return "redirect:/rental";
	}

	@GetMapping("/logout")
	public String logout(
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		session.removeAttribute("loginStatus");
		redirectAttributes.addFlashAttribute("message", "ログアウトしました。");
		return "redirect:/login";
	}
}
