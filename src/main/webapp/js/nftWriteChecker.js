function nftWriteCheck(){
	var title = document.nftWriteF.title;
	var photo = document.nftWriteF.photo;
	var txt = document.nftWriteF.txt;
	
	if(reqCk(title) || lengthCk(title, 5)) {
		alert("최소 글자 수 5글자 입니다.");
		title.value = "";
		title.focus();
		
		return false;
	}
	
	if(reqCk(photo) || fileType(photo, img) || fileType(photo, png) || fileType(photo, jpg)){
		alert("사진을 확인해주세요.");
		photo.value = "";
		photo.focus();
		
		return false;
	}
	
	if(reqCk(txt) || lengthCk(txt, 10)) {
		alert("최소 글자 수 10글자 입니다.");
		txt.value = "";
		txt.focus();
		
		return false;
	}
	
	return true;
}

function nftReWriteCheck(){
	var title = document.nftReWriteF.title;
	var photo = document.nftREWriteF.photo;
	var txt = document.nftReWriteF.txt;
	
	if(reqCk(title) || lengthCk(title, 5)) {
		alert("최소 글자 수 5글자 입니다.");
		title.value = "";
		title.focus();
		
		return false;
	}
	
	if(fileType(photo, img) || fileType(photo, png) || fileType(photo, jpg)){
		alert("파일 유형을 확인해주세요.");
		photo.value = "";
		photo.focus();
		
		return false;
	}
	
	if(reqCk(txt) || lengthCk(txt, 10)) {
		alert("최소 글자 수 10글자 입니다.");
		txt.value = "";
		txt.focus();
		
		return false;
	}
	
	return true;
}

function nftSelectCheck(){
	var serch = document.nftSelectF.serch;
	
	if(reqCk(serch)){
		alert("검색어를 입력해주세요");
		serch.value= "";
		serch.focus();
		
		return false;
	}	
	return true;
}

function nftCommentCheck(){
	var comment = document.commentF.txt;
	
	if(reqCk(comment) || lengthCk(comment, 5)){
		alert("최소 5글자");
		comment.value = "";
		comment.focus();
		
		return false;
	}
	return true;
}