function infoModifyCheck(){
	var photo = document.infoModifyF.photo;
	var pw = document.infoModifyF.pw;
	var pw2 = document.infoModifyF.pw2;
	var name = document.infoModifyF.name;
	var email = document.infoModifyF.email;
	var addr1 = document.infoModifyF.addr1;
	var addr2 = document.infoModifyF.addr2;
	var addr3 = document.infoModifyF.addr3;
	
	if(fileType(photo, img) || fileType(photo, png) || fileType(photo, jpg)){
		alert("프로필 사진을 확인해주세요.");
		photo.value = "";
		photo.focus();
		
		return false;
	}
	
	var pattern = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890!@#$%^";
	if(reqCk(pw) || pwPattern(pw, pattern)){
		alert("비밀번호를 정확히 입력 해주세요.");
		pw.value = "";
		pw2.value = "";
		pw.focus();
		
		return false;
	}
	
	if(pwCrossCk(pw, pw2)){
		alert("비밀번호가 일치하지 않습니다.");
		pw.value = "";
		pw2.value = "";
		pw.focus();
		
		return false;
	}
	
	if(reqCk(name)){
		alert("이름을 정확히 입력해주세요");
		name.value="";
		name.focus();
		
		return false;	
	}
	
	if(reqCk(email) || korCk(email)) {
		alert("이메일을 정확히 입력해주세요.");
		email.value="";
		email.focus();
		
		return false;
	}
	
	if(reqCk(addr1) || reqCk(addr2) || reqCk(addr3)){
		alert("주소를 정확히 입력해주세요.");
		addr1.value="";
		addr2.value="";
		addr3.value="";
		addr1.focus="";
		
		return false;
	}
	return true;
}