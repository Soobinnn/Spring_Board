package com.example.spring2.model.board.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring2.model.board.dto.BoardVO;

@Repository    // ���� Ŭ������ dao bean���� ���
public class BoardDAOImpl implements BoardDAO 
{
   @Inject
   SqlSession SqlSession;
   
   // 01. �Խñ� ��ü ���
/*   @Override
   public List<BoardVO> listAll() throws Exception 
   {
       return SqlSession.selectList("board.listAll"); // BoardVO
   }*/
   // 01. �Խñ� ��ü ���
   @Override
   public List<BoardVO> listAll(String searchOption, String keyword) throws Exception 
   {
	   // �˻��ɼ�, Ű���� �ʿ� ����
	   Map<String, String> map = new HashMap<String, String>();
	   map.put("searchOption", searchOption);
	   map.put("keyword", keyword);
	   return SqlSession.selectList("board.listAll", map);
   }
   // 02. �Խñ� �ۼ�
   @Override
   public void insert(BoardVO vo) throws Exception 
   {
       SqlSession.insert("board.insert", vo);
   }
   // 03. �Խñ� �󼼺���
   @Override
   public BoardVO view(int bno) throws Exception 
   {
       return SqlSession.selectOne("board.view", bno);
   }
   
   // 04. �Խñ� ��ȸ�� ����
   @Override
   public void increaseViewcnt(int bno) throws Exception 
   {
       SqlSession.update("board.increaseViewcnt", bno);
   }
   // 05. �Խñ� ����
   @Override
   public void updateArticle(BoardVO vo) throws Exception 
   {
       SqlSession.update("board.updateArticle", vo);

   }
   // 06. �Խñ� ����
   @Override
   public void deleteArticle(int bno) throws Exception 
   {
       SqlSession.delete("board.deleteArticle",bno);
   }
   
  
   // 07. �Խñ� ���ڵ� ����
   @Override
   public int countArticle(String searchOption, String keyword) throws Exception 
   {
	   // �˻��ɼ�, Ű���� �ʿ� ����
	   Map<String, String> map = new HashMap<String, String>();
	   map.put("searchOption", searchOption);
	   map.put("keyword", keyword);
	   return SqlSession.selectOne("board.countArticle", map);
   }

}
