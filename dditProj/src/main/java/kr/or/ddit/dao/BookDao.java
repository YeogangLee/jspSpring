package kr.or.ddit.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//Repository 어노테이션 
//: 스프링에게 데이터에 접근하는 클래스임을 알려준다.
//  스프링이 데이터를 관리하는 클래스라고 인지하여 자바빈(java bean)으로 등록하여 관리
//  자바빈으로 만든다 => 객체로 만든다
@Repository
public class BookDao {
	//매퍼 xml을 실행시키기 위해 SqlSessionTemplate 클래스의 객체를 멤버 변수로 선언
	//new 키워드를 통해 직접 생성하지 않고, Dependency Injection을 통해 주입 받음.
	//root-context.xml에서 미리 만들어 놓은 sqlSessionTemplate 타입 객체를
	//BookDao 객체에 주입하여 sqlSessionTemplate.select() 이런 식으로 사용할 수 있게 되었다.
	//멤버변수(필드) 위에 Autowired 어노테이션으로 의존성을 주입하는 방식을 필드 인젝션(Field Injection)이라고 한다.
	//이전 방식 :
	//SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(); 
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//map : Service 레이어로부터 전달받은 파라미터 그대로 매퍼 XML에 전달할 것이다.
	//int : insert 쿼리가 성공시 1개의 행이 생김, 성공(1), 실패(0)
	public int insert(Map<String, Object> map) {
		//insert(매퍼xml의 namespace.id, 전달할 데이터)
		//namespace.id : 매퍼 쿼리 이름
		return this.sqlSessionTemplate.insert("book.insert", map);
	}
	
	//Map형태를 반환, 해당 메서드 내부에서 사용하는 book.select_detail(쿼리 중 하나)를 찾아가면 마찬가지로 parameterMap을 Map형태로 받는다. 
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		//selectOne(매퍼xml의 namespace.id, 전달할 데이터) : 데이터를 1행만 가져올 때 이용
		/*
		 쿼리 결과 행의 수가 0 	--> selectOne() 메서드는 null을 반환
		 쿼리 결과 행의 수가 여러 개 --> TooManyResultException 예외를 throw함
		 */
		return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
}
