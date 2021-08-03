package kr.or.ddit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.SiteDao;
import kr.or.ddit.service.SiteService;
import kr.or.ddit.vo.SiteVO;

//@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	SiteDao siteDao;

	//전체 목록 조회
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return this.siteDao.selectList(map);
	}

	//상세 조회
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return this.siteDao.selectDetail(map);
	}

	//수정
	@Override
	public int update(Map<String, Object> map) {
		return this.siteDao.update(map);
	}

	//삭제
	@Override
	public int delete(Map<String, Object> map) {
		return this.siteDao.delete(map);
	}

	//등록
	@Override
	public String create(SiteVO siteVo) {
		int affectRowCount = siteDao.insert(siteVo);
		if(affectRowCount == 1) {
			System.out.println("SiteServiceImpl 성공");
			return "1";
		}
		System.out.println("SiteServiceImpl 실패");
		return null;
	}
}
