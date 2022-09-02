package site.metacoding.red.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardsController {

	@GetMapping("/boards")
	public String getBoardList() {
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id) {
		return "boards/detail";
	}
	
	@GetMapping("/boards/writeForm")
	public String writeForm() {
		return "boards/writeForm";
	}
}
