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
		
		//confirmPassword�� ���� �ִٸ�  errors�� confirmPassword Ű�� TRUE ���� ������?
		//-> ����, ��� ���� ���� �� ������ �߻��ϴ� ��
		//�׷��� ��й�ȣ�� �ٸ� ���� ���ٸ�? -> ������ ó������ ������, ������ �߻����Ѿ� �Ѵ�! 
		checkEmpty(errors, "confirmPassword", confirmPassword);
		
		//password, confirmPassword�� ��� ���� ���� �� errors�� �ƹ��͵� �� ��(put)
		//������ �� ���� �Է°��� �ٸ��ٸ�, errors�� ������ �־���� �Ѵ�.
		
		//errors �ʿ��� Ű�� ã�´�
		//!errors�� �ǹ̴� confirmPassword�� ���� �ִٴ� ��
		if(!errors.containsKey("confirmPassword")) { 
			if(!isPasswordEqualToConfirm()) {	//password, confirmPassword �� ���� �ٸ� �� ������ �߰� 
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}
	
	//errors : �����Ǿ� �ִ� errors, fieldName : vo�� �������, value : vo�� ��������� ��
	public void checkEmpty(Map<String, Boolean> errors, String fieldName, String value) {
		if(value==null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
	
	//password, confirmPassword �� ���� ������ ���Ѵ�. ������ true, �ٸ��� false ����
	public boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPassword);
	}
	
		
}
