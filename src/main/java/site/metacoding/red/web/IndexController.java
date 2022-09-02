package site.metacoding.red.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;

@RequiredArgsConstructor
@Controller
public class IndexController {

	private final UsersDao usersDao;
	
	@GetMapping("/test")
	public String index() {
		return "index";
	}
	
	@GetMapping("/test/users/{id}")
	public @ResponseBody Users getUsers(@PathVariable Integer id) {
		return usersDao.findById(id);
	}
}
