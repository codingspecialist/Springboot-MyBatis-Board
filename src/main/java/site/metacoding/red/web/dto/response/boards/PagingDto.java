package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingDto {
	private Integer totalCount;
	private Integer totalPage;
	private Integer currentPage;
	private boolean isLast; // getter가 만들어지면 isLast() 이름으로 만들어짐.
	private boolean isFirst; // getter가 만들어지면 isFirst() 이름으로 만들어짐.
}
