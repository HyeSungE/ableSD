package com.able.ableSD.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.able.ableSD.DTO.SolutionFiles;

@Service
public class SolutionFilesMapper {

	@Autowired
	private SqlSession sqlSession;

	public List<SolutionFiles> selectTypeIxSolution() {

		List<SolutionFiles> list = sqlSession.selectList("selectTypeIxSolution");
		return list;
	}

	public List<SolutionFiles> selectSolution(String solutionCode) {

		List<SolutionFiles> list = sqlSession.selectList("selectSolution",solutionCode);
		return list;
	}
	
	public List<Integer> countMaxLines() {
		return 	sqlSession.selectList("countMaxLines");
	}
	
	
	public int countSection1Max() {
		return 	sqlSession.selectOne("countSection1Max");
	}
	
	public int countSection2Max() {
		return 	sqlSession.selectOne("countSection2Max");
	}
	
	public int countSection3Max() {
		return 	sqlSession.selectOne("countSection3Max");
	}

	public int insertHistory(String SOLUTION_CD, String FILE_NAME, String ST_DATE, String ST_TIME, String USR_NM, String WORK_TYPE ) {
		Map<String,String> map = new HashMap<>();
		map.put("SOLUTION_CD",SOLUTION_CD);
		map.put("FILE_NAME",FILE_NAME);
		map.put("ST_DATE", ST_DATE);
		map.put("ST_TIME", ST_TIME);
		map.put("USR_NM", USR_NM);
		map.put("WORK_TYPE", WORK_TYPE);
		return sqlSession.insert("insertHistory", map);
	}
}
