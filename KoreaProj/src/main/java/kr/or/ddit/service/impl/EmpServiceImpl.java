package kr.or.ddit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.EmpDao;
import kr.or.ddit.service.EmpService;
import kr.or.ddit.vo.EmpVO;

//@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpDao empDao;
	
	//VO 사용X
	//map : controller로부터 전달받은 파라미터
	//등록
//	@Override
//	public String create(Map<String, Object> map) {
//		int affectRowCount = empDao.insert(map);
//		if(affectRowCount == 1) {
//			System.out.println("EmpServiceImpl 성공");
//			return "1";
//		}
//		System.out.println("EmpServiceImpl 실패");
//		return null;
//	}

	//전체 목록 조회
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return this.empDao.selectList(map);
	}

	//상세 조회
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return this.empDao.selectDetail(map);
	}

	//수정
	@Override
	public int update(Map<String, Object> map) {
		return this.empDao.update(map);
	}

	//삭제
	@Override
	public int delete(Map<String, Object> map) {
		return this.empDao.delete(map);
	}

	//등록
	@Override
	public String create(EmpVO empVo) {
		int affectRowCount = empDao.insert(empVo);
		if(affectRowCount == 1) {
			System.out.println("EmpServiceImpl 성공");
			return "1";
		}
		System.out.println("EmpServiceImpl 실패");
		return null;
	}
}
