package kr.or.ddit.item.vo;

import javax.validation.constraints.NotNull;

public class UserVO {
	
	//message 속성 : 해당 필드가 유효성 체크에 통과하지 못하면 보여줌
	@NotNull(message="번호에 값을 입력해주세요")
	private int no;
	@NotNull(message="이름에 값을 입력해주세요")
	private String name;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UserVO [no=" + no + ", name=" + name + "]";
	}
	
	

}
