<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
		    <!-- 여기에 forEach 필요하겠죠? -->
			<tr>
				<td>1</td>
				<td><a href="/boards/1">스프링1강</a></td>
				<td>ssar</td>
			</tr>
		</tbody>
	</table>


</div>

<%@ include file="../layout/footer.jsp"%>

