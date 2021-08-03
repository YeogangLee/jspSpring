package kr.or.ddit.emp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.emp.dao.EmpDao;
import kr.or.ddit.emp.service.EmpService;
import kr.or.ddit.emp.vo.EmpVO;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpDao empDao;
	
	//등록
	@Override
	public int insert(EmpVO empVo) {
		int affectRowCount = empDao.insert(empVo);
		if(affectRowCount > 0) {
			System.out.println("SiteServiceImpl 성공");
			return empVo.getEmpNum();
			
		}else {
			System.out.println("SiteServiceImpl 실패");
			return 0;
		}
	}
}
