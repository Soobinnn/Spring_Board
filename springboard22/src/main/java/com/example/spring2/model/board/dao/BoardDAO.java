package com.example.spring2.model.board.dao;

import java.util.List;

import com.example.spring2.model.board.dto.BoardVO;

public interface BoardDAO 
{ 
	/*   // 01. �Խñ� ��ü ���
   public List<BoardVO> listAll() throws Exception;*/
	
	// 01. �Խñ� ��ü ��� ==> �˻��ɼ�, Ű���� �Ű����� �߰�
   /*public List<BoardVO> listAll(String searchOption, String keyword) throws Exception;*/
	
	// 01. �Խñ� ��ü ���
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
   
   // 02. �Խñ� �ۼ�
   public void insert(BoardVO vo) throws Exception;
   
   // 03. �Խñ� �󼼺���
   public BoardVO view(int bno) throws Exception;
   
   // 04. �Խñ� ��ȸ ����
   public void increaseViewcnt(int bno) throws Exception;
   
   // 05. �Խñ� ����
   public void updateArticle(BoardVO vo) throws Exception;
   
   // 06. �Խñ� ����
   public void deleteArticle(int bno) throws Exception;

   // 07. �Խñ� ���ڵ� ���� �޼��� �߰�
   public int countArticle(String searchOption, String keyword) throws Exception;
   
}
