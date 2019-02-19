package com.myspring.model;

import java.util.List;




public interface BoardDAO {
	//��ü����
	List<BoardDTO> list(String sqlid,String field, String word, int startRow, int endRow);
	//�߰�
	void insert(String sqlid,BoardDTO board);
	//����
	void update(String sqlid,BoardDTO board);
	//����
	void delete(String sqlid,String id);
	//�󼼺���
	BoardDTO view(String sqlid,int seq);
	//�н�����üũ
	int passCheck(String sqlid,int seq, String pass);
	//�Խñ� �� 
	int boardCount(String sqlid,String field, String word);
	
	
}