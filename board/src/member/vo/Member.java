package member.vo;

import java.util.Date;

//�ڹٺ� Ŭ����
public class Member {
	private String memberid;
	private String name;
	private String password;
	private Date regdate;
	
	public Member(String memberid, String name, String password, Date regdate) {
		this.memberid = memberid;
		this.name = name;
		this.password = password;
		this.regdate = regdate;
	}

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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	//��й�ȣ ���� ��� ���� �� ���, 
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}

	@Override
	public String toString() {
		return "Member [memberid=" + memberid + ", name=" + name + ", password=" + password + ", regdate=" + regdate
				+ "]";
	}

	public void changePassword(String newPwd) {
		//���� ��й�ȣ�� �ű� ��й�ȣ�� �ٲ�
		this.password = newPwd;
	}
	
	
}
