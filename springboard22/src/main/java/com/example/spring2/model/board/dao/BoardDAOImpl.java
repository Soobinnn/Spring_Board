package com.example.spring2.model.board.dao;


import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring2.model.board.dto.BoardVO;

@Repository    // ���� Ŭ������ dao bean���� ���
public class BoardDAOImpl implements BoardDAO {
   @Inject
   SqlSession SqlSession;
   // 01. �Խñ� �ۼ�
   @Override
   public void create(BoardVO vo) throws Exception {
       SqlSession.insert("board.insert", vo);
   }
   // 02. �Խñ� �󼼺���
   @Override
   public BoardVO read(int bno) throws Exception {
       return SqlSession.selectOne("board.view", bno);
   }
   // 03. �Խñ� ����
   @Override
   public void update(BoardVO vo) throws Exception {
       SqlSession.update("board.updateArticle", vo);

   }
   // 04. �Խñ� ����
   @Override
   public void delete(int bno) throws Exception {
       SqlSession.delete("board.deleteArticle",bno);

   }
   // 05. �Խñ� ��ü ���
   @Override
   public List<BoardVO> listAll() throws Exception {
       return SqlSession.selectList("board.listAll");
   }
   // �Խñ� ��ȸ�� ����
   @Override
   public void increaseViewcnt(int bno) throws Exception {
       SqlSession.update("board.increaseViewcnt", bno);
   }

}
