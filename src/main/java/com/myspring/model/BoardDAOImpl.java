package com.myspring.model;

import java.util.ArrayList;
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
		sqlMap.update(sqlid, board);
		
	}
	
	//삭제
	@Override
	public void delete(String sqlid, int seq) {
		sqlMap.delete(sqlid, seq);
	}
	
	//상세보기
	@Override
	public BoardDTO view(String sqlid, int seq) {
		BoardDTO board = sqlMap.selectOne(sqlid, seq);
		return board;
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

	@Override
	public boolean passCheck(String sqlid, int seq, String pass) {
		// TODO Auto-generated method stub
		return false;
	}

	//조회수 증가
	public void countUp(String sqlid, int seq) {
		sqlMap.update(sqlid, seq);
	}

	public void reInsert(String sqlid, BoardDTO board) {
		sqlMap.insert(sqlid, board);
	}
	public void steps(String sqlid, BoardDTO board) {
		sqlMap.update(sqlid, board);
	}
	public List<CommentDTO> commentList(String sqlid, int seq) {
		List<CommentDTO> arr = sqlMap.selectList(sqlid, seq);
		return arr;
	}
	
	public void coInsert(String sqlid, CommentDTO cd) {
		sqlMap.insert(sqlid, cd);
	}

}
