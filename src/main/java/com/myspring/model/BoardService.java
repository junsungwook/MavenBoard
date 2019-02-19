package com.myspring.model;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("mService")
public class BoardService {

	@Resource(name="mDao")
	private BoardDAOImpl mDao;
	
	public void write(BoardDTO board) {
		mDao.insert("boardInsert", board);
	}
	public int boardCount(String field,String word) {
		int cnt = 0;
		cnt = mDao.boardCount("boardCount", field, word);
		return cnt;
	}
	public List<BoardDTO> boardList(String field,String word,int startRow,int endRow) {
		List<BoardDTO> arr = mDao.list("boardList",field,word,startRow,endRow);
		return arr;
	}
	
	public BoardDTO view(int seq) {
		BoardDTO board = mDao.view("detailView", seq);
		return board;
	}
	
	public void update(BoardDTO board) {
		mDao.update("update", board);
	}
	public void delete(BoardDTO board) {
		int seq = board.getSeq();
		mDao.delete("delete", seq);
	}
}
