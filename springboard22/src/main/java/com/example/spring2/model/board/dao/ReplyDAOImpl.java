package com.example.spring2.model.board.dao;

import java.util.List;
 
import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 
import com.example.spring2.model.board.dto.ReplyVO;
 
@Repository
public class ReplyDAOImpl implements ReplyDAO 
{
    @Inject
    SqlSession sqlSession;
    
    // ´ñ±Û ¸ñ·Ï
    @Override
    public List<ReplyVO> list(Integer bno) 
    {
        return sqlSession.selectList("reply.listReply", bno);
    }
    // ´ñ±Û ÀÛ¼º
    @Override
    public void create(ReplyVO vo) 
    {
        sqlSession.insert("reply.insertReply", vo);
    }
    // ´ñ±Û ¼öÁ¤
    @Override
    public void update(ReplyVO vo) 
    {
        // TODO Auto-generated method stub
    }
    // ´ñ±Û »èÁ¦
    @Override
    public void delete(Integer rno) 
    {
        // TODO Auto-generated method stub
    }
 
}