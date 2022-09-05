package site.metacoding.red.web.dto.response.boards;

public class PagingDto {
	private Integer startNum;
	private Integer totalCount;
	private Integer totalPage; // 23 / 10
	private Integer currentPage;
	private boolean isLast;
	private boolean isFirst;
}
