function goJoinPage(){
	location.href="JoinController";
}

function goMyInfoPage(){
	location.href= "MemberInfoController";
}

function logOut(){
	location.href="loginController";
}

function goMemberDelete(){
	var really = prompt("진짜 탈퇴할거면 '탈퇴하겠습니다.'라고 입력하시오");
	if(really=="탈퇴하겠습니다."){
		location.href="MemberDeleteController";
	}
}

function goNftGalleryDelete(no, photo){
	var really = confirm("진짜 삭제 하시렵니까?");
	
	if(really){
		location.href = "DeleteController?no=" + no + "&photo=" + photo;
	}
}

function goNftComment(no, page){
	location.href="CommentController?no=" + no + "&pageNum=" + page;
}

function goReplyDelete(no, page){
	var really = confirm("댓글을 삭제하시겠습니까?");
	
	if(really){
		location.href="ReplyDeleteController?cno=" + no + "&pageNum=" + page;
	}
}