package chap08.member;

import java.util.Date;

/**
 * �ڹٺ� : �Ӽ�(������), ���� �̺�Ʈ, ��ü ����ȭ�� ���� ǥ��
 * JSP������ �Ӽ��� ǥ���ϱ� ���� �뵵�� ���
 * �ڹٺ� �Ծ��� ������ Ŭ������ '�ڹٺ�JavaBean'�̶�� �θ���.
 *  
 * @author PC-14
 *
 */
public class MemberInfo {
	
	// 1) �����͸� �����ϴ� �ʵ�
	private String id;
	private String password;
	private String name;
	private String address;
	private Date registerDate;
	private String email;
	
	// 2) getter : �����͸� �о�� �� ���, ī�� ǥ����� �ؼ�
	public String getId() {
		return id;
	}
	
	// 3) setter : ���� ������ �� ���, ī�� ǥ����� �ؼ�
	public void setId(String id) {
		this.id = id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
