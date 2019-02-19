package com.myspring.model;

import java.util.List;




public interface BoardDAO {
	//전체보기
	List<BoardDTO> list(String sqlid,String field, String word, int startRow, int endRow);
	//추가
	void insert(String sqlid,BoardDTO board);
	//수정
	void update(String sqlid,BoardDTO board);
	//삭제
	void delete(String sqlid,int seq);
	//상세보기
	BoardDTO view(String sqlid,int seq);
	//패스워드체크
	boolean passCheck(String sqlid,int seq, String pass);
	//게시글 수 
	int boardCount(String sqlid,String field, String word);
	
	
}
