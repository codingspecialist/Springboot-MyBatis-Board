package site.metacoding.red.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final HttpSession session;
	private final BoardsDao boardsDao;
	// @PostMapping("/boards/{id}/delete")
	// @PostMapping("/boards/{id}/update")

	@PostMapping("/boards/{id}/update")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
		// 1. 영속화
		Boards boardsPS = boardsDao.findById(id);
		Users principal = (Users) session.getAttribute("principal");
		// 비정상 요청 체크
		if (boardsPS == null) {
			return "errors/badPage";
		}
		// 인증 체크
		if (principal == null) {
			return "redirect:/loginForm";
		}
		// 권한 체크 ( 세션 principal.getId() 와 boardsPS의 userId를 비교)
		if (principal.getId() != boardsPS.getUsersId()) {
			return "errors/badPage";
		}
		
		// 2. 변경
		boardsPS.글수정(updateDto);
		
		// 3. 수행
		boardsDao.update(boardsPS);
		
		return "redirect:/boards/"+id;
	}
	
	@GetMapping("/boards/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		Boards boardsPS = boardsDao.findById(id);
		Users principal = (Users) session.getAttribute("principal");

		// 비정상 요청 체크
		if (boardsPS == null) {
			return "errors/badPage";
		}
		// 인증 체크
		if (principal == null) {
			return "redirect:/loginForm";
		}
		// 권한 체크 ( 세션 principal.getId() 와 boardsPS의 userId를 비교)
		if (principal.getId() != boardsPS.getUsersId()) {
			return "errors/badPage";
		}
		
		model.addAttribute("boards", boardsPS);

		return "boards/updateForm";
	}

	@PostMapping("/boards/{id}/delete")
	public String deleteBoards(@PathVariable Integer id) {
		Users principal = (Users) session.getAttribute("principal");
		Boards boardsPS = boardsDao.findById(id);

		// 비정상 요청 체크
		if (boardsPS == null) { // if는 비정상 로직을 타게 해서 걸러내는 필터 역할을 하는게 좋다.
			System.out.println("=================");
			System.out.println("없는 번호를 요청하였습니다");
			System.out.println("=================");
			return "errors/badPage";
		}

		// 인증 체크
		if (principal == null) {
			System.out.println("=================");
			System.out.println("로그인을 안하셨어요");
			System.out.println("=================");
			return "redirect:/loginForm";
		}

		// 권한 체크 ( 세션 principal.getId() 와 boardsPS의 userId를 비교)
		if (principal.getId() != boardsPS.getUsersId()) {
			System.out.println("=================");
			System.out.println("해당 글을 삭제할 권한이 없습니다");
			System.out.println("=================");
			return "redirect:/boards/" + id;
		}

		boardsDao.delete(id); // 핵심 로직
		return "redirect:/";
	}

	@PostMapping("/boards")
	public String writeBoards(WriteDto writeDto) {
		// 1번 세션에 접근해서 세션값을 확인한다. 그때 Users로 다운캐스팅하고 키값은 principal로 한다.
		Users principal = (Users) session.getAttribute("principal");

		// 2번 pricipal null인지 확인하고 null이면 loginForm 리다이렉션해준다.
		if (principal == null) {
			return "redirect:/loginForm";
		}

		// 3번 BoardsDao에 접근해서 insert 메서드를 호출한다.
		// 조건 : dto를 entity로 변환해서 인수로 담아준다.
		// 조건 : entity에는 세션의 principal에 getId가 필요하다.
		boardsDao.insert(writeDto.toEntity(principal.getId()));

		return "redirect:/";
	}

	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, Integer page) { // 0 -> 0, 1->10, 2->20
		if (page == null)
			page = 0;
		int startNum = page * 3;

		List<MainDto> boardsList = boardsDao.findAll(startNum);
		PagingDto paging = boardsDao.paging(page);
		paging.makeBlockInfo();

		model.addAttribute("boardsList", boardsList);
		model.addAttribute("paging", paging);

		return "boards/main";
	}

	@GetMapping("/boards/{id}")
	public String getBoardDetail(@PathVariable Integer id, Model model) {
		model.addAttribute("boards", boardsDao.findById(id));
		return "boards/detail";
	}

	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/loginForm";
		}

		return "boards/writeForm";
	}
}
