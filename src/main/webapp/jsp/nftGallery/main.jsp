<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table id="pageL">
		<tr>
			<td align="center">
				<c:if test="${page != 1 }">
					<a href="NftGalleryWriteController?pageNum=${page - 1 }">&lt;</a>
					<a href="NftGalleryWriteController?pageNum=1">&lt;&lt;</a>
				</c:if>
			</td>
		</tr>
	</table>
	<table id = "pageR">
		<tr>
			<td align="center">
				<c:if test="${page != pageCount }">
					<a href="NftGalleryWriteController?pageNum=${page + 1 }">&gt;</a>
					<a href="NftGalleryWriteController?pageNum=${pageCount }">&gt;&gt;</a>
				</c:if>
			</td>
		</tr>
	</table>
<c:if test="${sessionScope.loginMember != null }">	
<table id="nftInputMainTable">
	<tr>
<c:choose>
	<c:when test="${process == 'correct'}">
		<td>
			<form action="CorrectController" method="post" name="nftReWriteF" onsubmit="return nftReWriteCheck();" enctype="multipart/form-data">
			<table id = "nftInputTable">
				<tr>
					<th id="nftFirstTh" colspan="2">
						<input name="title" placeholder="제목 30자 제한" maxlength="30" autofocus="autofocus" autocomplete="off" value="${cd.title }">
					</th>
				</tr>
				<tr>
					<td align="left" class="leftMarginTd">
						${cd.owner }
					</td>
					<td align="right" class="rightMarginTd">
						${cd.date }
					</td>
				</tr>
				<tr align="center">
					<td align="center" id="NftfileTd">
						<input name="no" value="${cd.no }" type="hidden">
						<input name="process" value="${process }" type="hidden">
						<input name="oldphoto" value="${cd.photo }" type="hidden">
						<input name="token" value="${token }" type="hidden">
						<img src="img/${cd.photo }"><br>
						<input name="photo" type="file"><br><br>
						사진 업로드<br>
						(img, jpg, png만 가능)<br>
						미선택 시 기존 사진 업로드.
					</td>
					<td align="center" class="nftTextTd">
						<textarea name="txt" placeholder="내용 250자 제한" autocomplete="off" maxlength="250">${cd.txt }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button>수정</button>
					</td>
				</tr>
			</table>
			</form>
		</td>
	</c:when>
	<c:when test="${process == 'write' || process == null }">	
		<td>
			<form action="NftGalleryWriteController" method="post" name="nftWriteF" onsubmit="return nftWriteCheck();" enctype="multipart/form-data">
			<table id = "nftInputTable">
				<tr>
					<th id="nftFirstTh" colspan="2">
						<input name="title" placeholder="제목 30자 제한" maxlength="30" autofocus="autofocus" autocomplete="off">
					</th>
				</tr>
				<tr align="center">
					<td align="center">
						<input name="token" value="${token }" type="hidden">
						<input name="photo" type="file"><br><br>
						사진 업로드<br>
						(img, jpg, png만 가능)
					</td>
					<td align="center" class="nftTextTd">
						<textarea name="txt" placeholder="내용 250자 제한" autocomplete="off" maxlength="250"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button>업로드</button>
					</td>
				</tr>
			</table>
			</form>
		</td>
	</c:when>
</c:choose>
		<td>
			<form name="nftSelectF" action="NftGalleryWriteController" onsubmit="return nftSelectCheck();">
				<table id="nftSelectTable">
					<tr>
						<td colspan="2" align="center" id="selectTd">
							목록
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input name="search" placeholder="검색어 입력" autocomplete="off">
						</td>
					</tr>
					<tr>
						<td align="center">
							<button>검색</button>
						</td>
						<td align="center" id="showMyPostTd">
							<a href="NftGallerySelectController?search">내 글 보기</a>
						</td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
</table>
</c:if>	
	<table>
		<tr>
			<td align="center">
				<c:forEach var="n" items="${NftGalleryData }">
				<table class = "nftTable">
					<tr>
						<th colspan="2">
							${n.title }
						</th>
					</tr>
					<tr>
						<td align="left" class="leftMarginTd">
							${n.name }(${n.owner })<br>
							<img src="img/${n.ownerPhoto }">
						</td>
						<td align="right" class="rightMarginTd">
							${n.date }
						</td>
					</tr>
					<tr>
						<td align="left">
							<img src="img/${n.photo }">
						</td>
						<td align="center" class="nftTextTd">
							${n.txt }
						</td>
					</tr>
				<c:if test="${sessionScope.loginMember.id == n.owner }">
						<tr align="center">
							<td align="right" class="boardCorrectTd">
								<a href="CorrectController?no=${n.no }&process=correct">수정</a>
							</td>
							<td align="center">
								<div class="boardDeleteTd" onclick="goNftGalleryDelete(${n.no }, '${n.photo }');">삭제</div>
							</td>
						</tr>
				</c:if>
					<tr>
						<td class="showReplyTd" colspan="2">
							<div onclick="goNftComment(${n.no}, ${page });">댓글 보기</div>
						</td>
					</tr>
			  	<c:if test="${n.no == param.no && commentPage == 'comment.jsp'}">
					<tr>
						<td colspan="2" align="center">
							<table class="nftReplyTable">
							<c:forEach var="r" items="${replyData }">
								<tr>
									<td class="replyImgTd" align="left">
										<img src="img/${r.photo }"><br>
										${r.id }
									</td>
									<td align="center" class="replyTxtTd">
										${r.txt }
									</td>
									<td align="center" class="nftReplyDateTd">
										${r.date }
									</td>
										<c:if test="${sessionScope.loginMember.id == r.id }">
											<td align="center" class="nftDeleteReplyTd">
												<div onclick="goReplyDelete(${r.no}, ${page });">삭제</div>
											</td>
										</c:if>	
								</tr>
							</c:forEach>
								<c:if test="${sessionScope.loginMember != null }">
								<tr>
									<td colspan="4" align="center">
										<form name="commentF" action="CommentController" method="post" onsubmit="return nftCommentCheck();">
											<table id="replyWriteTable">
												<tr>
													<td align="center">
														<input name="token" value="${token }" type="hidden">
														<input name="PageNum" type="hidden" value=${page }>
														<input name="id" type="hidden" value=${sessionScope.loginMember.id }>
														<input name="n_no" type="hidden" value=${n.no }>
														<textarea name="txt" maxlength="100" placeholder="댓글 최소 5글자, 최대 100글자" autofocus="autofocus" autocomplete="off"></textarea>
													</td>
													<td>
														<button>댓글 작성</button>
													</td>
													
												</tr>
											</table>
										</form>
									</td>
								</tr>
								</c:if>   
							</table>
						</td>
					</tr>		
				</c:if>
				</table>
				</c:forEach>
			</td>
		</tr>
	</table>
	<table>
		<tr>	
			<c:forEach var="i" begin="1" end="${pageCount }">
				<td>
					<a href="NftGalleryWriteController?pageNum=${i }">${i }</a>
				</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>