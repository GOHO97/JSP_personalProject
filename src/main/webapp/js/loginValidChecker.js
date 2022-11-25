function loginCheck(){
	var id = document.loginF.id;
	var pw = document.loginF.pw;
	
	if(reqCk(id)){
		alert("id를 입력해주세요");
		id.value = "";
		id.focus();
		
		return false;
	}
	
	if(reqCk(pw)){
		alert("비밀번호를 입력해주세요");
		pw.value = "";
		pw.focus();
		
		return false;
	}
	
	return true;
}