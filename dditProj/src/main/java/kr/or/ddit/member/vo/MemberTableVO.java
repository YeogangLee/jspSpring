package kr.or.ddit.member.vo;

//MEMBER 테이블 용, 자바빈 클래스
public class MemberTableVO {

	private String memberid;
	private String password;
	private String name;
	private String email;
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "MemberTableVO [memberid=" + memberid + ", password=" + password + ", name=" + name + ", email=" + email
				+ "]";
	}
	
	
}
