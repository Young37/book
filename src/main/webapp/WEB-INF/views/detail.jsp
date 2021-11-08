<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav>
		<a href="/">목록</a>
	</nav>
	<br><br>
	<main>
		도서번호 : ${}<br>
		도서명 : ${list.writerName}<br>
		재고량 : #{}<br>
		<c:if test="${list.modifyDate!=null}">
		판매가 : <fmt:formatDate pattern="yyyy년 MM월 dd일 HH:mm:ss" value="${list.modifyDate}"/><br>
		</c:if>
		파일 목록 : ${list.file}<br>
		조회 : ${list.hit}<br>
		내용 : <pre>${list.content}</pre>

		<br>
		<c:if test="${sessionScope.id == list.writerName }">
		<form style="display:inline" action="modify" method="get" >
		<input type="hidden" name="id" value="${list.id}">
		<input type="submit" value="글 수정">
		</form>
		</c:if>
		<c:if test="${sessionScope.id == list.writerName || sessionScope.id.equals('admin')}">
		<form style="display:inline" action="" method="post">
			<input type="hidden" name="id" value="${list.id}">
			<input type="hidden" name="delete" value="list">
			<input type="submit" value="글 삭제">
		</form>
		<br>
		</c:if>
		
		<br>
		<nav>
			<c:if test="${nextList!=null}">
			다음 글 : <a href="?id=${nextList.id}">${nextList.title}</a>
			</c:if>
			<c:if test="${nextList==null}">
			마지막 글 입니다.
			</c:if>
		</nav>
		<nav>
			<c:if test="${prevList!=null}">
			이전 글 : <a href="?id=${prevList.id }">${prevList.title}</a>
			</c:if>
			<c:if test="${prevList==null}">
			이전 글이 없습니다.
			</c:if>
		</nav>
		
		<!-- 
		선택적 출력을 위해 뷰에서 만들어, 뷰에서만 자체 이용하는 파라미터들 (id + 파라미터형태로 요청)
		param.modify "${cmt.id}" -> 댓글 or 댓글수정창 구분 출력
		param.subcmt "${cmt.id}" -> 대댓글추가창 열기버튼 or 대댓글추가창 구분 출력
		param.nstdmodify "${nstdcmtid}" -> 대댓글 or 대댓글수정창 구분 출력
		 -->
		
		<c:if test="${empty param.modify && empty param.nstdmodify}"> <!--  댓글 추가부분 -->
		<br>
		<form action="?id=${list.id}" method="post">
		<textarea style="resize:none" rows="7" cols="40" name="cmtContent"></textarea><br><input type="submit" value="댓글 작성">
		</form>
		</c:if>
		<hr color="brown">
		
		<c:forEach var="cmt" items="${cmts}"> <!--  댓글 관련 시작 -->
			<c:if test="${cmt.id != param.modify}">
				<span>작성자 : ${cmt.writerId}</span><br>
				내용 : <pre>${cmt.content}</pre><br>
				<span>작성일 : <fmt:formatDate pattern="yyyy년 MM월 dd일 HH:mm:ss" value="${cmt.regdate}"/></span><br>
				<c:if test="${cmt.modifyDate != null}">
				최근 수정 : <fmt:formatDate pattern="yyyy년 MM월 dd일 HH:mm:ss" value="${cmt.modifyDate}"/><br>
				</c:if>
				<c:if test="${sessionScope.id == cmt.writerId }">
				<form style="display:inline">
					<input type="hidden" name="id" value="${list.id}">
					<input type="hidden" name="modify" value="${cmt.id}">
					<input type="submit" value="수정">
				</form>
				</c:if>
				
				<c:if test="${sessionScope.id == cmt.writerId || sessionScope.id.equals('admin') }">
				<form style="display:inline" action="?id=${list.id}&delete=comment&cmtid=${cmt.id}" method="post">
					<input type="submit" value="삭제">
				</form>
				</c:if>
				
				<c:if test="${param.subcmt!=cmt.id}">
				<form style="display:inline">
					<input type="hidden" name="id" value="${list.id}">
					<input type="hidden" name="subcmt" value="${cmt.id}">
					<input type="submit" value="대댓글">
				</form>
				</c:if>
				<c:if test="${param.subcmt==cmt.id}">
				<br>
				<form action="?id=${list.id}&cmtid=${cmt.id}" method="post">
				<textarea style="resize:none" rows="7" cols="40" name="nstdCmtContent"></textarea><br><input type="submit" value="확인">
				</form>
				</c:if>
				
			</c:if>
			<!-- 둘 중 하나 -->
			<c:if test="${cmt.id == param.modify}">
				<form action="?id=${list.id}&cmtid=${cmt.id}" method="post">
				<span>작성자 : ${cmt.writerId}</span><br>
				<span><textarea style="resize:none" rows="7" cols="40" name="modifycmt">${cmt.content}</textarea></span>
				<br><input type="submit" value="수정 완료"><br>
				<span>작성일 : ${cmt.regdate}</span><br>
				<c:if test="${cmt.modifyDate != null}">
				최근 수정 : ${cmt.modifyDate }<br>
				</c:if>
				</form>
			</c:if>
			
			<!--  --><!--  --><!--  -->
			<c:set var="line" value="0"/>
		
			<c:forEach var="nstdcmt" items="${nestedCmts}">
			<c:if test="${nstdcmt.commentId == cmt.id}">
			
			<c:set var="line" value="${line+1}"/>
			<c:if test="${line==1}">
			<br>
			─────────
			<br>
			</c:if>
			>>>
			<br>
				<c:if test="${nstdcmt.id != param.nstdmodify}">
				<span>작성자 : ${nstdcmt.writerId}</span><br>
				내용 : <pre>${nstdcmt.content}</pre><br>
				<span>작성일 : <fmt:formatDate pattern="yyyy년 MM월 dd일 HH:mm:ss" value="${nstdcmt.regdate}"/></span><br>
				<c:if test="${nstdcmt.modifyDate != null}">
				최근 수정 : <fmt:formatDate pattern="yyyy년 MM월 dd일 HH:mm:ss" value="${nstdcmt.modifyDate}"/><br>
				</c:if>
				<c:if test="${sessionScope.id == nstdcmt.writerId }">
				<form style="display:inline">
					<input type="hidden" name="id" value="${list.id}">
					<input type="hidden" name="nstdmodify" value="${nstdcmt.id}">
					<input type="submit" value="수정">
				</form>
				</c:if>
				<c:if test="${sessionScope.id == nstdcmt.writerId || sessionScope.id.equals('admin') }">
				<form style="display:inline" method="post">
					<input type="hidden" name="id" value="${list.id}">
					<input type="hidden" name="delete" value="nstdcmt">
					<input type="hidden" name="cmtid" value="${nstdcmt.id}">
					<input type="submit" value="삭제">
				</form>
				</c:if>
				<br>
				</c:if>
				<!-- 둘 중 하나 -->
				<c:if test="${nstdcmt.id == param.nstdmodify}">
				<form action="?id=${list.id}&nstdcmtid=${nstdcmt.id}" method="post">
				<span>작성자 : ${nstdcmt.writerId}</span><br>
				<span><textarea style="resize:none" rows="7" cols="40" name="modifynstdcmt">${nstdcmt.content}</textarea></span>
				<br><input type="submit" value="수정 완료"><br>
				<span>작성일 : ${nstdcmt.regdate}</span><br>
				<c:if test="${nstdcmt.modifyDate != null}">
				최근 수정 : ${nstdcmt.modifyDate}<br>
				</c:if>
				</form>
				</c:if>
			</c:if>
			</c:forEach>
			
			<hr>
		</c:forEach> <!--  댓글 관련 끝 -->
		
		<br>
		<span>접속 중 <span style="font-size:30px;color:green">${sessionScope.id}</span>님</span>
	</main>
	<script src="../js/time.js"></script>
</body>
</html>