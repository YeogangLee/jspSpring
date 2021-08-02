<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Upload with Ajax</title>
	<script src="resources/js/jquery.min.js"></script>
	<script type="text/javascript">
	
		//첨부파일을 이용한 웹 공격 방지
		//- 특정 확장자를 제외한 파일들의 업로드 제한 
		//- exe, sh, zip 등의 업로드 제한 (sh는 리눅스의 실행파일, 윈도우의 exe 같은 것)
		//- 특정 크기 이상의 파일은 업로드 제한
		var regexp = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880;	//5MB
		
		//파일명과 파일의 크기를 파라미터로 받는다.
		function checkExtension(fileName, fileSize) {
			//5MB를 초과하지 못하게 함
			if(fileSize >= maxSize) {
				alert("업로드 가능한 최대 파일 사이즈를 초과했습니다.");
				return false;
			}
			//파일명의 정규식 테스트
			if(regexp.test(fileName)) {
				alert("해당 종류의 파일은 업로드 할 수 없습니다.");
				return false;
			}
			
			return true;
		}

		$(function(){
			
			function readURL(input) {
				//input 파라미터에 파일이 있다면..
				if(input.files && input.files[0]) {
					//파일을 읽기 위한 FileReader 객체를 생성
					var reader = new FileReader();
					reader.onload = function(e) {
						//파일 읽기 성공 시 처리
						//이미지요소의 src속성 <- 읽어들인 File내용을 지정함
						$("#blah").attr("src",e.target.result);
					}
					//File 내용을 읽어 dataURL형식의 문자열로 저장
					reader.readAsDataURL(input.files[0]);
				}
			}
			
			//파일 업로드 전 업로드 할 파일 미리보기
			$("#imgInp").change(function(){
				//선택한 이미지의 경로 표시
				console.log(this.value);
				//imgInp 즉, 파일 요소를 readURL 함수의 파라미터로 던진다.
				readURL(this);
			});
			
			
			$("#uploadBtn").on("click", function(e){
				
				//파일 업로드 시 jQuery를 이용하는 경우 FormData 객체를 이용함
				//FormData는 가상의 <form>태그와 같다.
				//Ajax를 이용한 파일 업로드는 FormData에 File파라미터를 담아 전송
				//File파라미터 : name은 String값, value는 File객체
				//..이름이 같으면 배열 형태로 받을 수 있다.
				var formData = new FormData();
				
				//input 태그들 중에서 name 속성의 값이 uploadFile인 요소(태그)를 찾음
// 				alert($("input[name='uploadFile']"));
				var inputFile = $("input[name='uploadFile']");
				//.files
				console.log(inputFile[0].files);
				var files = inputFile[0].files;
				for(var i=0; i<files.length; i++) {
					console.log(files[i].name);
					
					//파일의 크기와 확장자 테스트.(함수에 파일명과 파일사이즈를 파라미터로 던짐)
					if (!checkExtension(files[i].name, files[i].size)) {
						return false;
					}
					
					//가상의 form 태그 하위로 File파라미터를 넣어준다.
					formData.append("uploadFile",files[i]);
				}
				
				if($("#imgInp")[0].files.length == 0) {
					alert("파일을 선택하지 않았습니다.");
					return;
					
				}else {
					
				
				//첨부파일 데이터를 formData에 추가한 후 Ajax를 통해 formData 자체를 전송
				//processData와 contentType은 반드시 false로 지정해야만 전송이 된다.
				//컨트롤러에서는 MultipartFile타입을 이용하여 첨부파일 데이터를 처리한다.
				$.ajax({
					url: '/uploadAjaxAction'
					,processData: false
					,contentType: false
					,data: formData
					,type: 'POST'
					,dataType: 'json' //컨트롤러에서 오는 자료형
					,success: function(result){
// 						alert(result);
// 						console.log("-- success --")
						//파일 업로드 후 업로드한 파일 보여주기
// 						for(var i=0 in result) {
// 							console.log(result[i])
// 							$("#fileView").append("<img src='/resources/images/" + result[i] + "' />");
// 						}
						alert("업로드 완료");
					}
				}); //end ajax
				} //end if
				
			}); //end #uploadBtn
			
		});//end function
	</script>
</head>
<body>
	<form>
				
	</form>
	<h1>Ajax를 이용하여 파일 업로드 하기</h1>
	<div class="uploadDiv">
		<input type="file" id="imgInp" name="uploadFile" multiple />
	</div>
	<button id="uploadBtn">Upload</button><br/><br/>
	<div id="fileView">
		<img id="blah" src="/resources/images/Tulips.jpg" style="width: 400px" alt="이미지 미리보기" />
	</div>
</body>
</html>