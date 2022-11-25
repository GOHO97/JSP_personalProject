<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table id="welcomeTable">
	<tr>
		<td align="center" colspan="2" id="welTd">
			${loginMember.name }님<br>
			환영합니다.
		</td>
	</tr>
	<tr>
		<td id="profilePhoto" align="center" rowspan="2">
			<img src="img/${loginMember.photo }">
		</td>
		<td align="center">
			<div onclick="goMyInfoPage();" id="MyInfoDiv" align="center">My Info</div>
		</td>
	</tr>
	<tr>
		<td align="center">
			<div onclick="logOut();" id="LogOutDiv" align="center">LogOut</div>
		</td>
	</tr>
</table>
</body>
</html>