package member.vo;

import java.util.Map;

public class JoinRequest_my {
	private String memberid;
	private String name;
	private String password;
	private String confirmPassword;
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@Override
	public String toString() {
		return "JoinRequest [memberid=" + memberid + ", name=" + name + ", password=" + password + ", confirmPassword="
				+ confirmPassword + "]";
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, "memberid", memberid);
		checkEmpty(errors, "name", name);
		checkEmpty(errors, "password", password);
		
		//confirmPassword가 값이 있다면  errors의 confirmPassword 키와 TRUE 값이 있을까?
		//-> ㄴㄴ, 모두 값이 없을 때 에러가 발생하는 것
		//그런데 비밀번호와 다른 값이 들어갔다면? -> 오류로 처리되지 않지만, 오류를 발생시켜야 한다! 
		checkEmpty(errors, "confirmPassword", confirmPassword);
		
		//password, confirmPassword랑 모두 값이 있을 때 errors에 아무것도 안 들어감(put)
		//하지만 두 값의 입력값이 다르다면, errors에 오류를 넣어줘야 한다.
		
		//errors 맵에서 키를 찾는다
		//!errors의 의미는 confirmPassword에 값이 있다는 뜻
		if(!errors.containsKey("confirmPassword")) { 
			if(!isPasswordEqualToConfirm()) {	//password, confirmPassword 두 값이 다를 때 에러에 추가 
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}
	
	//errors : 공유되어 있는 errors, fieldName : vo의 멤버변수, value : vo의 멤버변수의 값
	public void checkEmpty(Map<String, Boolean> errors, String fieldName, String value) {
		if(value==null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
	
	//password, confirmPassword 두 값이 같은지 비교한다. 같으면 true, 다르면 false 리턴
	public boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPassword);
	}
	
		
}
