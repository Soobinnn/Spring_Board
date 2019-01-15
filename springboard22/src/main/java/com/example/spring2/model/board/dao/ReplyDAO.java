package com.example.spring2.model.board.dao;

import java.util.List;

import com.example.spring2.model.board.dto.ReplyVO;
 
public interface ReplyDAO 
{
    // ´ñ±Û ¸ñ·Ï
    public List<ReplyVO> list(Integer bno);
    // ´ñ±Û ÀÔ·Â
    public void create(ReplyVO vo);
    // ´ñ±Û ¼öÁ¤
    public void update(ReplyVO vo);
    // ´ñ±Û »èÁ¦
    public void delete(Integer rno);
}