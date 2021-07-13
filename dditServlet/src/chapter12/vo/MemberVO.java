package chapter12.vo;

public class MemberVO {
	
	private String memNo;
	private String memName;

	//객체가 생성시 회원id, 회원명 필수
	public MemberVO(String memNo, String memName) {
		this.memNo = memNo;
		this.memName = memName;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	@Override
	public String toString() {
		return "MemberVO [memNo=" + memNo + ", memName=" + memName + "]";
	}
	
		
}
