package member.vo;

import java.util.Map;

public class JoinRequest {
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
		//confirmPassword가 값이 있다면 errors의 confirmPassword 키와 TRUE 값이 있을까?
		checkEmpty(errors, "confirmPassword", confirmPassword);
		//password랑 confirmPassword랑 모두 값이 있을 때 errors에 아무것도 안들어감(put)
		//but, password랑 confirmPassword랑 다른 값일 때 errors에 오류를 넣어줘야 함
		//!errors의 의미는 confirmPassword에 값이 있다는 뜻
		if(!errors.containsKey("confirmPassword")) {
			if(!isPasswordEqualToconfirm()) {	//password랑 confirmPasswor랑 다를때
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}
	//errors : 공유되어 있는 errors, fieldName : vo의 멤버변수, value : vo의 멤버변수의 값
	private void checkEmpty(Map<String, Boolean> errors, String fieldName, String value) {
		if(value==null||value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
	
	//password랑 confirmPassword랑 값이 같은지 비교. 같으면 True, 다르면 False 리턴
	public boolean isPasswordEqualToconfirm() {
		return password != null && password.equals(confirmPassword);
	}
}
