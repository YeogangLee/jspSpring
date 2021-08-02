package kr.or.ddit.item.service;

import java.util.Map;

import kr.or.ddit.item.vo.Item;

public interface ItemService {

	//메소드 시그니처
	public int create(Item item);
	
	//오버로딩(하나의 클래스에 같은 이름의 메소드를 여러 개 만듦)
	public int create(Item item, String[][] picturesUrl);
	
	//ITEM_ATCH 입력
	public int createAtch(Map<String, Object> map);
	

}
