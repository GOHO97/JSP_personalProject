<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="js/hoValidChecker.js"></script>
<script type="text/javascript" src="js/joinValidCheker.js"></script>
<link rel="stylesheet" href="css/login.css">
</head>
<body id="joinBody">
	<form action="JoinController" method="post" name="joinF" onsubmit="return joinCheck();" enctype="multipart/form-data">
		<table id="joinTable">
			<tr>
				<td align="center" colspan="3" class="generalInput">
					<input name="photo" type="file">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					프로필 사진(img, jpg, png만 가능)
				</td>
			</tr>
			<tr >
				<td colspan="3">
					아이디
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3" class="generalInput">
					<input name="id" autocomplete="off" maxlength="15" autofocus="autofocus" placeholder="한글, 특수문자 제외">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					비밀번호
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3" class="generalInput"> 
					<input name="pw" type="password" autocomplete="off" maxlength="15" placeholder="한글 제외 특수문자는 !@#$%^만 가능">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					비밀번호 재확인
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3" class="generalInput">
					<input name="pw2" type="password" autocomplete="off" maxlength="15">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					닉네임
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3" class="generalInput">
					<input name="name" autocomplete="off" maxlength="10">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					생일
				</td>
			</tr>
			<tr>
				<td align="center">
					<select name="y">
						<c:forEach var="yy" begin="${curYear - 60 }" end="${curYear }">
							<option>${yy }</option>
						</c:forEach>
					</select>년
				</td>
				<td align="center">
					<select name="m">
						<c:forEach var="mm" begin="1" end="12">
							<option>${mm }</option>
						</c:forEach>
					</select>월
				</td>
				<td align="center">
					<select name="d">
						<c:forEach var="dd" begin="1" end="31">
							<option>${dd }</option>
						</c:forEach>
					</select>일
				</td>
			</tr>
			<tr>
				<td colspan="3">
					E-mail
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3" class="generalInput">
					<input name="email" type="email" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td colspan="3">
					주소
				</td>
			</tr>
			<tr>
				<td align="center" class="addrInput">
					<input name="addr1" autocomplete="off" placeholder="우편번호">
				</td>
				<td align="center" class="addrInput">
					<input name="addr2" autocomplete="off" placeholder="주소">
				</td>
				<td align="center" class="addrInput">
					<input name="addr3" autocomplete="off" placeholder="상세주소">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<button>회원가입</button>
				</td>			
			</tr>
		</table>
	</form>
</body>
</html>