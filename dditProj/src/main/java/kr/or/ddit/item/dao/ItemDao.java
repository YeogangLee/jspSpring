package kr.or.ddit.item.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.item.vo.Item;

//자바빈 클래스로 등록해서 관리하기 위함
@Repository
public class ItemDao {

	//root-context.xml에서 미리 만들어놓은 connection 관련 객체를 사용하기 위함
	//ItemDao객체에 SqlSessionTemplate타입 객체 주입(스프링에서 자동으로 해줌 IoC)  
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//ITEM 테이블
	public int insert(Item item) {
		//템플릿.insert(namespace(xml).id)
		int result = this.sqlSessionTemplate.insert("item.insert", item);
		int itemId = 0;
		if(result > 0) {//insert 성공
			itemId = item.getItemId();	//xml의 selectKey를 통해 setItemId()된 정보를 itemId 변수에 저장
			return itemId;	//insert하고 왔으면 쿼리문에서 itemId를 받아왔을 것이므로, 성공했을 때의 조건문에서 받아준다.
			
		}else {
			return itemId;	//0 리턴
		}
	}
	
	//ITEM_ATCH 테이블
	public int insertAtch(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("item.insertAtch", map);
	}
	
	
}
