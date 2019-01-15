package com.example.spring2.service.board;

import java.util.List;

import javax.inject.Inject;
 
import org.springframework.stereotype.Service;
 
import com.example.spring2.model.board.dao.ReplyDAO;
import com.example.spring2.model.board.dto.ReplyVO;
 
@Service
public class ReplyServiceImpl implements ReplyService 
{
    @Inject
    ReplyDAO replyDao;
    
    // ´ñ±Û ¸ñ·Ï
    @Override
    public List<ReplyVO> list(Integer bno) 
    {
        return replyDao.list(bno);
    }
    // ´ñ±Û ÀÛ¼º
    @Override
    public void create(ReplyVO vo) 
    {
        replyDao.create(vo);
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
 
