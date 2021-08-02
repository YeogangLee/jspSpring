package kr.or.ddit.item.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.item.dao.ItemDao;
import kr.or.ddit.item.service.ItemService;
import kr.or.ddit.item.vo.Item;

//자바빈 클래스로 등록해서 관리하기 위함
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemDao itemDao;
	
	//ITEM
	@Override
	public int create(Item item) {
		//insert 후의 itemId를 return해줌
		return this.itemDao.insert(item);
	}
	
	//ITEM_ATCH
	@Override
	public int createAtch(Map<String, Object> map) {
		return this.itemDao.insertAtch(map);
	}

	@Override
	public int create(Item item, String[][] picturesUrl) {
		int itemId = this.itemDao.insert(item);
		
		//ITEM_ATCH 테이블로 insert
		for(int i=0; i<picturesUrl.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pictureUrl", picturesUrl[i][0]);
			map.put("pictureSize", picturesUrl[i][1]);
			map.put("itemId", itemId);
			this.itemDao.insertAtch(map);
		}
		
		//insert된 이미지의 개수 return
		return picturesUrl.length;
	}
}
