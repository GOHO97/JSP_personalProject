//일반적
//컨셉 : 부정적
//잘못 되면 true, 제대로면 false

//필수검사
//<input>을 넣었을 때
// 안썻으면 true, 썼으면 false

function reqCk(input) {
	return !input.value;
}

//<input>/최소글자수를 넣었을 때
// 짧으면 true, 길면 false
function lengthCk(input, len) {
	return input.value.length < len;
}

//한글이나 특수문자 썼으면 걸리게
//허용해놓으면 개발자가 할일이 폭증하기 때문에 개발자가 귀찮아서 한글 못쓰게 하는 것이다.
//그렇기 때문에 프로젝트가 달라도 이건 그냥 통일된 형태일 것이다.
function korCk(input) {
	var unkor = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_@."
	for (var i = 0; i < input.value.length; i++) {
			if(unkor.indexOf(input.value[i]) == -1) {
				return true;
			}
		}
		return false;
}

//그런데 비밀번호는 아닐 것이다. 
//그렇기 때문에 어떤 프로젝트나 통용될 수 있게 직접 문자열 조합을 넣을 수 있게 한다.
//<input>, 문자열 조합를 넣었을 때
//그 문자열세트에 있는 글자가 없으면 true, 있으면 false
function pwPattern(input, set) {
		for (var i = 0; i < set.length; i++) {
			if(input.value.indexOf(set[i]) != -1) {
				return false;
			}
		}
		return true;
}

//비번검사
//<input> X 2개 넣었을 때, 내용이 다르면 true 같으면 false

function pwCrossCk(input1, input2) {
	return input1.value != input2.value;
}

//<input> 넣었을 때
//숫자가 아니면 true, 숫자만 있으면 false
function isNotNumber(input) {
	return isNaN(input.value);
}

//<input> 넣었을 때
//음수면 true, 양수면 false
function isNegative(input) {
	return input.value < 0;
}

//<input>, 확장자 넣었을 때
//그게 아니면 true, 그거면 false
function fileType(input, type) {
	type = "." + type;
	var fName = input.value.toLowerCase();
	 //대문자로 확장자 입력 했을 때 소문자로 바꿔줌.
	return fName.indexOf(type) == -1;
}


