function joinCheck(){
	var photo = document.joinF.photo;
	var id = document.joinF.id;
	var pw = document.joinF.pw;
	var pw2 = document.joinF.pw2;
	var name = document.joinF.name;
	var email = document.joinF.email;
	var addr1 = document.joinF.addr1;
	var addr2 = document.joinF.addr2;
	var addr3 = document.joinF.addr3;
	
	if(reqCk(photo) || fileType(photo, img) || fileType(photo, png) || fileType(photo, jpg)){
		alert("프로필 사진을 확인해주세요.");
		photo.value = "";
		photo.focus();
		
		return false;
	}
	
	if(reqCk(id) || korCk(id)){
		alert("아이디를 정확히 입력 해주세요.");
		id.value = "";
		id.focus();
		
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