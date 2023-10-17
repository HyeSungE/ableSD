<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>에이블컴 솔루션 다운로드</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	padding: 0;
	font-weight: lighter;
	font-size: small;
	caret-color: transparent; /* 타이핑 커서 숨기기*/
}


body::-webkit-scrollbar {
	width: 0em;
}
.header, .grid-container {
	margin-left: 10vw;
	margin-right: 10vw;
}
.header {
	height: 13vh;
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	z-index: 1000; /* 다른 요소 위에 헤더가 보이도록 함 */
	padding: 10px;
	background-color: #f0f0f0;
	background-image: url('resources/ableSDImage/Rectangle 3.png');
	background-size: cover;
	background-repeat: round;
	display: flex;
	justify-content: center;
	align-items: center;
}

.header p {
	text-align: right;
	margin: 0;
}



.header-title {
	display: flex;
	align-items: center;
	justify-content: center;
}

.user-info {
	position: absolute;
	bottom: 0;
	right: 0;
	margin: 20px;
	display: flex;
	align-items: center;
}

.logout_span {
	display: flex;
	align-items: center;
	border-radius: 5px;
	margin-left: 10px;
	background-image: url('resources/ableSDImage/Rectangle 4.png');

	padding: 3px;
	
}

.logout_span:hover {
	background-image: url('');
	background-color: #ffb000;
	border-radius: 10px;
}

#logoutImage {
	margin-legt : 7px;
}

.grid-container {
	min-width: fit-content;
	min-height: fit-content;

	display: grid;
	grid-template-columns: repeat(5, 1fr);
	padding: 0px;
	gap: 20px;
	background-color: transparent;
	justify-content: center;
	align-content: center;
	margin-top: 120px;
}

.grid-item {
	text-align: center;
	padding: 0px;
	background-color: white;
	height: fit-content;
}

.solution_title_image {
	margin: auto;
}

ul {
	padding: 20px;
	margin-bottom: 0px;
	margin-top: 0px;
}

ul li {
	height: 25px;
	text-align: left;
	
	align-items: center;
	list-style: none;
	
}

.ul_section1 li,.ul_section2 li {
	margin-bottom : 15px;
	
}

.ul_section3 li{
	padding-top : 12.5px;
	padding-bottom : 12.5px;
}
.no-dot {
	list-style: none;
	pointer-events: none;
}

#section_3 {
	height: 0px;
}

.ul_section3 {
	background-image: url('resources/ableSDImage/Rectangle 10.png');
	background-size: cover;
	background-repeat: round;
}

.list_span {
	display: flex;
	align-items: center;
}

.list_span:hover {
	cursor: pointer;
}

.list_span>img {
	width: 18px;
	height: 18px;
	margin-right: 8px;
}

#list_span3 {
	display: flex;
	align-items: center;
}

#list_span3>img {
	width: 18px;
	height: 18px;
	margin-right: 8px;
}

#list_span_section3 {
	background-image: url('resources/ableSDImage/manualBackGround.png');
	background-size: cover;
	background-repeat: no-repeat;
	padding: 5px;
	border-radius: 5px;
}

#list_span_section3:hover {
	background-image: url('resources/ableSDImage/manualBackGroundHover.png');
	background-size: cover;.
	background-repeat: no-repeat;
	border-radius: 5px;
}

.text_section_1 {
	color: #22668d;
}

.text_section_2 {
	color: #e55604;
}

.text_section_3 {
	color: black;
}


@media screen and (max-width: 670px) {
	.header {
		background-repeat: round;
	}
	.user-info {
		margin-bottom: 10px;
		margin-right: 10px;
	}
	.header-descript {
		visibility: hidden;
	}
	.grid-container {
		grid-template-columns: repeat(1, 1fr);
	}
	.grid-item:nth-child(2), .grid-item:nth-child(3), .grid-item:nth-child(4),
		.grid-item:nth-child(5) {
		margin-top: 200px;
	}
	.image-container {
		max-width: 77%;
		margin: 0 auto;
	}
	.image-container img {
		max-width: 100%;
		height: auto;
	}
}

@media screen and (min-width: 670px) and (max-width: 889px) {
	.header {
		background-repeat: round;
	}
	.user-info {
		margin-bottom: 10px;
		margin-right: 20px;
	}
	.header-descript {
		visibility: hidden;
	}
	.grid-container {
		grid-template-columns: repeat(2, 1fr);
	}
	.grid-item:nth-child(3), .grid-item:nth-child(4), .grid-item:nth-child(5)
		{
		margin-top: 200px;
	}
	.image-container {
		max-width: 77%;
		margin: 0 auto;
	}
	.image-container img {
		max-width: 100%;
		height: auto;
	}
}

@media screen and (min-width: 890px) and (max-width: 1110px) {
	.header {
		background-repeat: round;
	}
	.user-info {
		margin: 20px;
	}
	.header-descript {
		visibility: hidden;
	}
	.grid-container {
		grid-template-columns: repeat(3, 1fr);
		/* Adjust the number of columns as needed */
	}
	.grid-item:nth-child(4), .grid-item:nth-child(5) {
		margin-top: 200px;
	}
}

@media screen and (min-width: 1110px) and (max-width: 1200px) {
	.header {
		background-repeat: round;
	}
	.user-info {
		margin: 20px;
	}
	.header-descript {
		visibility: hidden;
	}
	.grid-container {
		grid-template-columns: repeat(4, 1fr);
	}
	.grid-item:nth-child(5) {
		margin-top: 200px;
	}
}

@media screen and (min-width: 1201px) and (max-width: 1346px) {
	.header {
		background-repeat: round;
	}
	.user-info {
		margin: 20px;
	}
	.grid-container {
		grid-template-columns: repeat(4, 1fr);
	}
	.grid-item:nth-child(5) {
		margin-top: 200px;
	}
}

@media screen and (max-height: 999999px) {
	.header {
		height: 90px;
		background-repeat: round;
	}
	.user-info {
		margin: 20px;
		margin-bottom: 10px;
	}
}

.textSection{
	font-size : 15px;
}

</style>
</head>
<body>
	<div class="header">
		<div class="image-container">
			<div class="header-title">
				<img src="resources/ableSDImage/ablecom.png" alt="로고"> <img src="resources/ableSDImage/Solution Download.png" alt=타이틀">
			</div>
			<div class='header-descript'>
				<img src="resources/ableSDImage/ableSD_description.png" alt="설명">
			</div>
		</div>

		<div class='user-info'>
			${sessionScope.currentUser.account} 님&nbsp; <span class="logout_span"> <img src="resources/ableSDImage/logoutIcon.png" alt="logout"> <img src="resources/ableSDImage/logout.png" id="logoutImage" alt="logout">
			</span>
		</div>



	</div>
	<div class="grid-container">
		<c:forEach var="solutionMap" items="${solutionMap}">
			<div class="grid-item">

				<img class="solution_title_image" src="resources/ableSDImage/${solutionMap.key}.png" alt="${solutionMap.key}">

				<c:forEach var="section" begin="1" end="3">
					<div id="section_${section}">
						<ul id="${solutionMap.key}" class="ul_section${section}">
							<c:set var="count" value="0" />
							<c:forEach var="solution" items="${solutionMap.value}">
								<c:if test="${fn:substring(solution.FILE_SEQ,0,1) eq section}">
									<li id="${solution.FILE_NAME}" class="clickable_${solution.FILE_USE}">
										<span class="list_span" id="list_span_section${section}" 
											onmouseover="HoverAction(${section}, true,'${solutionMap.key}_${solution.FILE_NAME}','${solution.FILE_USE }')" 
											onmouseout="HoverAction(${section}, false,'${solutionMap.key}_${solution.FILE_NAME}','${solution.FILE_USE }')" 
											onclick="downloadClick('${solution.FILE_NAME}', '${solution.FILE_PATH}', '${solutionMap.key}','${solution.FILE_USE }')"> 
											
											<c:if test="${section eq 1 or section eq 2}">
												<img id="download_image_${solutionMap.key}_${solution.FILE_NAME}" class="section${section }_download_image downloadImage_${solution.FILE_USE}" src="resources/ableSDImage/section_${section}_download.png" alt="download">
											</c:if>
											<c:if test="${section eq 3}">
												<img id="section_${section }_download_image" class="section${section }_download_image downloadImage_${solution.FILE_USE}" src="resources/ableSDImage/section_${section}_download.png" alt="download">
											</c:if>
											<span id="text_section_${solutionMap.key}_${solution.FILE_NAME}" class="textSection text_section_${section } file_name_text_${solution.FILE_USE}">${solution.FILE_NAME}</span>
											
											 
											
										</span>
									</li>
									
									<c:set var="count" value="${count + 1}" />
								</c:if>
							</c:forEach>
							<c:forEach var="i" begin="${count + 1}" end="${countMaxLines[section-1]}">
								<li class="no-dot">&nbsp;</li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>
		</c:forEach>
	</div>


	<script>
	
	document.addEventListener('DOMContentLoaded', function() {
	    // DOM이 로드된 후 실행되는 코드
	    var downloadImages = document.querySelectorAll('.downloadImage_N');

	    for (var i = 0; i < downloadImages.length; i++) {
	        downloadImages[i].style.filter = 'contrast(0)'; // 그레이스케일 스타일 적용
	    }
	    
	    var fileNameText = document.querySelectorAll('.file_name_text_N');

	    for (var i = 0; i < fileNameText.length; i++) {
	    	   fileNameText[i].style.color = 'gray'; // 글씨 색깔을 회색으로 변경
	    }
	    
	    var clickableListItems = document.querySelectorAll('.clickable_N');

	    for (var i = 0; i < clickableListItems.length; i++) {
	        clickableListItems[i].style.pointerEvents = 'none'; // 클릭 불가능하게 설정
	    }

	});
	
    function HoverAction(section, isMouseOver,id,fileUse) {
   	 if (section == 1 || section == 2) {
   		   var image = document.getElementById('download_image_'+id);
   		   var textSection = document.getElementById('text_section_' + id);
		
   	        if (image && fileUse=='Y') {
   	            if (isMouseOver) {
   	                image.src = 'resources/ableSDImage/section_' + section + '_download_hover.png';
   	                textSection.style.color = '#ffa41b';
   	            } else {
   	                image.src = 'resources/ableSDImage/section_' + section + '_download.png';
   	                textSection.style.color = '';
   	            }
   	        }
   	 }  
    
   }
	
		var loginMessage = "<c:out value='${loginMessage}'/>";
		console.log(loginMessage);
		console.log(loginMessage);
		if (loginMessage != '') {
			alert(loginMessage);
			location.href = "./";
		}

		$(".logout_span").click(function() {
			const logoutConfirm = window.confirm('로그아웃 하시겠습니까?');
			if (logoutConfirm) {
				$.ajax({
					url : "logoutAPI",
					method : "POST",
					success : function(data) {
						if (data == "success") {
							alert("로그아웃 되었습니다.");
						} else if (data == 'fail') {
							alert("로그인 정보가 없습니다.");
						}
						location.href = "./";
					},
					error : function(error) {
						console.log("로그아웃 요청 실패: " + error);
						location.href = "./";
					}
				});
			}
		});

		function downloadClick(FILE_NAME, FILE_PATH, SOLUTION_CD,fileUse) {
			if(fileUse=='Y'){
				
				console.log(FILE_PATH + FILE_NAME + "downloading" );
				const downloadConfirm = window.confirm(FILE_NAME + ' 파일을 다운로드 하시겠습니까?');
				if (downloadConfirm) {
					console.log("downloadAPI ajax start");
					$.ajax({
						url : "downloadAPI",
						method : "GET",
						data : {
							"FILE_NAME" : FILE_NAME,
							"FILE_PATH" : FILE_PATH,
							"SOLUTION_CD" : SOLUTION_CD
						},
						xhrFields : {
							responseType : 'blob' // 응답을 블롭(blob)으로 받아옴
						},
						success : function(data, textStatus, xhr) {
							console.log(textStatus)
							if (textStatus == 'success') {
								var contentDisposition = xhr
										.getResponseHeader('Content-Disposition');
								console.log(contentDisposition);
								var fileName = "";
								if (contentDisposition) {
									// Content-Disposition 헤더 값에서 큰따옴표("")를 제거하고 파일 이름을 추출합니다.

									// /\"/g 글로벌로 "를 찾아 공백으로 바꾸고 filename= 으로 split
									fileName = contentDisposition.replace(/"/g, "")
											.split('filename=')[1];
									fileName = decodeURIComponent(fileName); //인코딩된 파일이름 디코딩
									fileName = fileName.replace(/\+/g, ' '); // /\+/g 글로벌로 +를 찾아 공백으로 바꿈

								} else {
									// contentDisposition가 없는 경우에 대한 처리
									console.log("Content-Disposition 헤더가 없습니다.");
								}

								var url = window.URL.createObjectURL(data);
								var a = document.createElement("a");
								a.href = url;
								a.download = fileName;
								document.body.appendChild(a);
								a.click();
								window.URL.revokeObjectURL(url);
							} else if (textStatus == 'nocontent') {
								alert("파일이 존재하지 않습니다.")
							}

						},
						error : function(error) {
							console.log(error)
							console.log(error.status)
							var errorStatus = error.status;
							if(errorStatus == 302) {
								alert("다운로드 실패 [" + error.status + "]\n로그인 정보가 없습니다.\n다시 로그인 해주세요.");
								location.replace("/ableSD");
							}else if(errorStatus == 204) {
								alert("다운로드 실패 [" + error.status + "]\n다운로드가 경로가 존재하지 않습니다.");
								
							}else if(errorStatus == 404){
								alert("다운로드 실패 [" + error.status + "]\n잘못된 경로입니다.");		
							}
							
						}
					});

				}
				
				
			}
			
		}
	</script>


</body>
</html>
