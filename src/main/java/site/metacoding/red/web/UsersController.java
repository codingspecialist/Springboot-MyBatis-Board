package site.metacoding.red.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

	@GetMapping("/loginForm")
	public String loginForm() {
		return "users/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
}
