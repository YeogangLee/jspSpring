package kr.or.ddit.siteMat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.siteMat.dao.SiteMatDao;
import kr.or.ddit.siteMat.service.SiteMatService;
import kr.or.ddit.siteMat.vo.SiteMatVO;

@Service
public class SiteMatServiceImpl implements SiteMatService {

	@Autowired
	SiteMatDao siteMatDao;
	
	//방금 insert된 기본키 값 받아오기 : SiteMat 테이블은 복합기본키 이용 -> map이용
	@Override
	public SiteMatVO insert(SiteMatVO siteMatVo) {
		int affectRowCount = this.siteMatDao.insert(siteMatVo);
		
		if(affectRowCount > 0) { // insert 성공
			return siteMatVo;
		}else {
			return null;
		}
	}
	
	@Override
	public List<Map<String, Object>> selectConMatInfo() {
		return this.siteMatDao.selectConMatInfo();
	}
}
