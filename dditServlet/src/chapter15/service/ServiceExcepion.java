package chapter15.service;

public class ServiceExcepion extends RuntimeException {

	//�����ε� : �ϳ��� Ŭ�������� ���� �̸��� �޼��带 ���� �� ���
	//�����ڵ� �Ķ���� ������ �ٸ��� �ؼ� �����ε��Ͽ� ����� �� �ִ�.
	
	//������ - �޽����� ���θ� ����
	private ServiceExcepion(String message, Exception cause) {
		super(message, cause);
	}
	
	//������ - �޽����� ����
	public ServiceExcepion(String message) {
		super(message);
	}
	
}
