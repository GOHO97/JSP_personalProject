<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="loginController" onsubmit="return loginCheck()" name="loginF" method="post">
	<table id="loginMainTable">
			<tr>
				<td align="center" colspan="2">
					<input name="id" maxlength="15" placeholder="ID" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input name="pw" maxlength="15" type="password" placeholder="PW" autocomplete="off">
				</td>
			</tr>
			<tr>
				<td align="center">
					<button>Login</button>
				</td>
				<td align="center">
					<div onclick="goJoinPage();" id="joinDiv" align="center">Join</div>
				</td>
			</tr>
	</table>
</form>
</body>
</html>