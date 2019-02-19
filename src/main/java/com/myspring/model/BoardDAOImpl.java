package com.myspring.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("mDao")
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession sqlMap;
	
	//리스
	@Override
	public List<BoardDTO> list(String sqlid, String field, String word, int startRow, int endRow) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("startRow", startRow);
		hm.put("endRow", endRow);
		hm.put("field", field);
		hm.put("word", word);
		List<BoardDTO> arr = sqlMap.selectList(sqlid, hm);
		return arr;
	}

	//추가
	@Override
	public void insert(String sqlid, BoardDTO board) {
		sqlMap.insert(sqlid, board);
	}
	
	//수정
	@Override
	public void update(String sqlid, BoardDTO board) {
		// TODO Auto-generated method stub
		
	}
	
	//삭제
	@Override
	public void delete(String sqlid, String id) {
		// TODO Auto-generated method stub
		
	}
	
	//상세보기
	@Override
	public BoardDTO view(String sqlid, int seq) {
		BoardDTO board = sqlMap.selectOne(sqlid, seq);
		return board;
	}

	//비번체크
	@Override
	public int passCheck(String sqlid, int seq, String pass) {
		// TODO Auto-generated method stub
		return 0;
	}

	//글카운팅
	@Override
	public int boardCount(String sqlid,String field, String word) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("field", field);
		hm.put("word", word);
		int cnt = sqlMap.selectOne(sqlid, hm);
		return cnt;
	}



}
