package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingDto {
	private Integer blockPage; // 변수
	private Integer blockPageCount; //  상수  한페이지에 페이지 넘수 개수(5) 1-5, 6-10
	private Integer startPageNum; // 변수 1 -> 6 -> 11
	private Integer lastPageNum; // 변수 5 -> 10 -> 15
	private Integer totalCount;
	private Integer totalPage;
	private Integer currentPage;
	private boolean isLast; // getter가 만들어지면 isLast() 이름으로 만들어짐. -> el에서는 last로 찾음
	private boolean isFirst; // getter가 만들어지면 isFirst() 이름으로 만들어짐. -> el에서는 first로 찾음
}
