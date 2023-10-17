<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>AbleSD 로그인</title>
<script src="https://kit.fontawesome.com/f3a2532bc1.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">

<style>
body {
	padding: 0px;
	margin: 0px;
	font-size : medium;
	caret-color: transparent; /* 타이핑 커서 숨기기*/
}
/* 로그인 박스 배경  */
.login-background {
	top: 50%;
	left: 50%;
	padding: 5px;
	transform: translate(-50%, -50%);
	position: absolute;
	background-color: rgba(48, 133, 195, 1);
	width: 500px;
	height: 300px;
	border-radius: 10px;
}

/* 로그인 박스*/
.login-box {
	position: absolute;
	top: 50%;
	left: 50%;
	padding: 5px;
	transform: translate(-50%, -50%);
	position: absolute;
}

/* 로그인 로고 */
.login-title {
	text-align: center;
}

/* 로그인 폼  */
.login-form {
	display: grid;
}

/* 아이디 비밀번호 입력 박스 */
.input-box {
	background-color: white;
	margin: 0px;
	border: 1px solid black;
	margin-bottom: 10px;
	border-radius: 10px;
}

/* 아이디 비밀번호 입력 인풋  */
.login-input {
	width : 86%;
	background-color: transparent;
	height: 30px;
	border: none; 
	outline: none; 
	font-size : 15px;
}

/* 아이디 비밀번호 인풋 아이콘 */
.fa-solid {
	margin-left: 10px;
	margin-right: 10px;
}

/* 로그인 버튼 */
.login-button {
	width: 100%;
	height: 100%;
	color: black;
	border-radius: 5px;
	background: white;
	cursor: pointer;
	transition: all 0.3s ease;
	box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, .5), 7px 7px 20px
		0px rgba(0, 0, 0, .1), 4px 4px 5px 0px rgba(0, 0, 0, .1);
	outline: none;
	font-size : 15px;
}

/* 로그인 버튼 호버액션  */
.login-button:hover {
	background-color: #ffb000;
	color: black;
	cursor: pointer;
}
/* 아이디 저장 체크박스 */
.save-id-cb {
	height: 13px;
	vertical-align: text-top
}
/* 로그인 버튼이 있는 td  */
.login-btn-td {
	height: 0px;
	padding-bottom: 30px;
}
.show-caret {
    caret-color: auto; /* 또는 원래 커서 색상 */
}
</style>

</head>
<body>

	<div class="login-background"></div>
	<div class="login-box">
		<h2 class="login-title">
			<img src="resources/ableSDImage/ablecom.png" alt="로고"><br /> <img src="resources/ableSDImage/Solution Download.png" alt=타이틀">
		</h2>
		<form class="login-form">
			<table>
				<tr>
					<td>
						<div class="input-box">
							<span><i class="fa-solid fa-user"></i></span><input class="login-input show-caret" type="text" id="account" name="account" placeholder="아이디" required tabIndex='1' /> <br />
						</div>
					</td>
					<td class="login-btn-td" rowspan='2'>
						<button type="button" class="login-button custom-btn btn-7" tabIndex='3' >로그인</button>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input-box">
							<span><i class="fa-solid fa-lock"></i></span><input class="login-input show-caret" type="password" id="passwd"  name="passwd" placeholder="비밀번호" required tabIndex='2' /> <br />
						</div>
						<input type="checkbox" id="save-id-cb" name="save-id-cb" tabIndex='4' /> 아이디 저장 <br />
				</tr>
			</table>
		</form>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script>
	
	/* 중복 로그인 클릭 방지를 위한 로그인 클릭 변수   */
	var loginButtonClicked = false; 
	
	/* 로그인 클릭 시 실행 함수*/
	$(".login-button").click(function () {
		
		  var account = $("#account").val(); /* 아이디 입력 */
		  var passwd = $("#passwd").val(); /* 비밀번호 입력  */
		  console.log("ajax 입장하십니다 ~"); /*  */
		  
		  if (account.trim() === "" ) { /* 아이디를 입력하지 않았을 경우 */
	            alert("아이디를 입력해 주세요.");
		  } else if (passwd.trim() === "") { /* 비밀번호를 입력하지 않았을 경우 */
	            alert("비밀번호를 입력해 주세요.");
		  }else{
			  if( loginButtonClicked ) { /* 이미 로그인 버튼이 클릭 된 상태 -> 로그인 진행 중 */
					alert("로그인 진행 중입니다.")	
				}else{
					loginButtonClicked = true; /* 로그인 버튼이 눌리지 않았으면 -> 로그인 진행 중 X */
			
					retrieveAPI() /* login API를 호출하기 위해 먼저 retrieveAPI 호출 */
				    .then(function (result) { /*  Promise 객체의 메서드 Promise가 실행되고 후에 실행되는 콜백 함수 / result는 먼저 실행된 Promise객체의 결과값*/
				      console.log(result)
				      
				      $.ajax({ /* loginAPI 호출을 위해 입력한 아이디와 비밀번호를 컨트롤러에 넘김 */
				        url: "loginAPI",
				        method: "POST",
				        dataType: "text",
				        data: {
				        	account: account,
				        	passwd: passwd
		                },
				        success: function (data) { /* ajax 성공 시, 반환 값*/
				        	console.log("loginAPI 요청 성공 . . .");
				        	if(data == 'success'){ /* 성공이면 로그인 버튼 클릭을 false로 만들고 다운로드 페이지로 이동  */
				        		loginButtonClicked = false;
				        		location.href="./home"
				        		
				        	}else{ /* 성공이 아니면 로그인 버튼 클릭을 false로 만들고 코드에 따라 alert창 출력 */
				        		console.log(data)
				        		if(data == 400) {
				        			alert("아이디 또는 비밀번호를 확인해 주세요")
				        		}else{
				        			alert("로그인 실패 [ "+data+" ]")	
				        		}
				        		loginButtonClicked = false;
				        	}
				        	
				        },
				        error: function (error) { /* loginAPI 에러 */
				        	console.log("loginAPI 요청 에러 . . . : " + error);
				        	alert("관리자에게 문의해주세요")
				        	loginButtonClicked = false;
				        },
				      });
				    })
				    .catch(function (error) { /* retrieveAPI 에러 */
				      console.log("retrieveAPI 요청 에러 . . . : " + error);
				      alert("관리자에게 문의해주세요")
				      loginButtonClicked = false;
				    });		
				}  
		}
	});
	/* 엔터키 클릭 시, 로그인 버튼 액션 */
	$('.login-input').on('keypress', function(e){
		  if(e.keyCode == '13'){ /* 엔터키 코드 */
		  	$('.login-button').click();
		  }
		});

		function retrieveAPI() { /* retrieveAPI 호출  */
		  return new Promise(function (resolve, reject) {
		    
		    $.ajax({
		      url: "retrieveAPI",
		      method: "GET",
		      dataType: "json",
		      success: function (data) {
		        console.log("retrieveAPI 요청 성공 . . .");
		        resolve(data);
		      },
		      error: function (error) {
		        console.log("retrieveAPI 요청 실패 . . .");
		        alert("관리자에게 문의해주세요")
		        reject(error);
		      },
		    });
		  });
		}
		
		
		$(document).ready(function(){
		    var key = getCookie("saveId");  /* 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감. */
		    $("#account").val(key); 
		     
		    if($("#account").val() != ""){  /* 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면, */
		        $("#save-id-cb").attr("checked", true);  /* ID 저장하기를 체크 상태로 두기. */
		    }
		     
		    $("#save-id-cb").change(function(){  /* 체크박스에 변화가 있다면, */
		        if($("#save-id-cb").is(":checked")){ /*  ID 저장하기 체크했을 때, */
		            setCookie("saveId", $("#account").val(), 3650);
		        }else{  /* ID 저장하기 체크 해제 시, */
		            deleteCookie("saveId");
		        }
		    });
		     
		     /* ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장. */
		    $("#account").keyup(function(){  /* ID 입력 칸에 ID를 입력할 때, */
		        if($("#save-id-cb").is(":checked")){ /*  ID 저장하기를 체크한 상태라면, */
		            setCookie("key", $("#account").val(), 3650);  /* 7일 동안 쿠키 보관 */
		        }
		    });
		});
		 
		/* 쿠키를 설정하는 함수 */
		function setCookie(cookieName, value, exprieDays){
		    var todayDate = new Date(); /* 현재 날짜를 가져옴 */
		    todayDate.setDate(todayDate.getDate() + exprieDays); /* 쿠키 만료 날짜를 계산 */
		    var cookieValue = escape(value) + ( (todayDate==null) ? "" : "; expires=" + todayDate.toGMTString() ); /* 쿠키 값에 이스케이프(escape)를 적용하고 만료 날짜를 추가 */
		    document.cookie = cookieName + "=" + cookieValue;   /* 쿠키를 브라우저에 설정 */
		}
		 
		/* 쿠키를 삭제하는 함수 */
		function deleteCookie(cookieName){
		    var expireDate = new Date();  /* 쿠키를 만료시키기 위해 만료 날짜를 어제로 설정	 */
		    expireDate.setDate(expireDate.getDate() - 1);
		    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();   /* 쿠키를 브라우저에서 삭제 */
		}
		 
		function getCookie(key) {
			key = new RegExp(key + '=([^;]*)');  /* 쿠키들을 세미콘론으로 구분하는 정규표현식 정의 */
			return key.test(document.cookie) ? unescape(RegExp.$1) : '';  /* 인자로 받은 키에 해당하는 키가 있으면 값을 반환 */
		    /*cookieName = cookieName + '=';
		    var cookieData = document.cookie;
		    var start = cookieData.indexOf(cookieName);
		    var cookieValue = '';
		    if(start != -1){
		        start += cookieName.length;
		        var end = cookieData.indexOf(';', start);
		        if(end == -1)end = cookieData.length;
		        cookieValue = cookieData.substring(start, end);
		    }
		    return unescape(cookieValue);*/
		}
	</script>
</body>
</html>
