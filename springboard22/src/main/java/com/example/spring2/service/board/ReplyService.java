package com.example.spring2.service.board;

import java.util.List;

import com.example.spring2.model.board.dto.ReplyVO;
 
public interface ReplyService 
{
    // ��� ���
    public List<ReplyVO> list(Integer bno);
    // ��� �Է�
    public void create(ReplyVO vo);
    // ��� ����
    public void update(ReplyVO vo);
    // ��� ����
    public void delete(Integer rno);
}
 
