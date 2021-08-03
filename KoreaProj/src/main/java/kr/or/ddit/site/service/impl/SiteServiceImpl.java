package kr.or.ddit.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.site.dao.SiteDao;
import kr.or.ddit.site.service.SiteService;
import kr.or.ddit.site.vo.SiteVO;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	SiteDao siteDao;
	
	//등록
	@Override
	public int insert(SiteVO siteVo) {
		int affectRowCount = siteDao.insert(siteVo);
		if(affectRowCount > 0) {
			System.out.println("SiteServiceImpl 성공");
			return siteVo.getSiteNum();
			
		}else {
			System.out.println("SiteServiceImpl 실패");
			return 0;
		}
	}
}
