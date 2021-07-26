package chapter15.service;

public class ServiceExcepion extends RuntimeException {

	//오버로딩 : 하나의 클래스에서 같은 이름의 메서드를 여러 개 사용
	//생성자도 파라미터 개수를 다르게 해서 오버로딩하여 사용할 수 있다.
	
	//생성자 - 메시지와 원인만 받음
	private ServiceExcepion(String message, Exception cause) {
		super(message, cause);
	}
	
	//생성자 - 메시지만 받음
	public ServiceExcepion(String message) {
		super(message);
	}
	
}
