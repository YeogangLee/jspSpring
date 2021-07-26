package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	
	//모든 명령어를 다루는 클래스들이 공통적으로 구현해야 하는 메서드를 선언함
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	
}
