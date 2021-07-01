<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>회원가입</title>
	<script type="text/javascript">
	
		function fnPost(){
			var frm = document.getElementById("frm");
			frm.method = "post";
			frm.action = "registOk.jsp";
			frm.submit();
		}
		
		function fn_chk() {

			var memId = document.getElementById("memId");
			var memName = document.getElementById("memName");
			var genderMale = document.getElementById("genderMale");
			var genderFemale = document.getElementById("genderFemale");
			var sel = document.getElementsByName("sel")[0];

			if(memId.value == "") {
				alert("아이디를 입력해주세요.");
				return false;
			}
			if(memName.value == "") {
				alert("이름을 입력해주세요.");
				return false;
			}
			if(!genderMale.checked && !genderFemale.checked) {
				alert("성별을 입력해주세요.");
				return false;
			}
// 			if() {
				var length = document.getElementsByName("sel")[0].length;
				
				var i = 1;
				while(!document.getElementsByName("sel")[0].children[i].selected) {
					i++;
					if(i == length) {
						alert("나이를 입력해주세요.");
						return false;
					}
				}
// 			}
			return true; //submit이 실행됨.
		}
	</script>
</head>
<body>
	<!-- registOk.jsp에서 파라미터를 받아 출력 (한글처리까지) -->
	<!-- 출력 후에 registOk2.jsp로 redirect해서 결과 한번 더 출력 -->	
	<form if="frm" name="frm" method="post" action="registOk.jsp" onsubmit="return fn_chk()">
		아이디 : <input type="text" id="memId" name="memId"><br>
		이름 : <input type="text" id="memName" name="memName"><br>
		<label for="gender">성별 : </label>
		<input type="radio" name="gender" id="genderMale" value="남성"><label for="genderMale">남성</label>
		<input type="radio" name="gender" id="genderFemale" value="여성"><label for="genderFemale">여성</label><br>
		나이 :
		<!-- jsp로 넘겨주기 위해 name속성 사용, 선택된 option값이 자동으로 지정되어 select만 보내도 같이 보내진다. -->
		<select name="sel">
			<option value="">선택</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<!-- ...반복... -->
			<option value="50" name="age">50</option>
		</select>
		<br>
		<br>
		<!-- get방식을 쓰면 url을 일일이 파싱해야 한다, 귀찮으니 post 방식 사용 -->
		<button type="submit">회원 등록</button>
<!-- 		<button type="button" onclick="fnPost()">post버튼</button> -->
	</form>
</body>
</html>