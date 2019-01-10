package com.example.spring2.service.member;


import java.util.List;

import com.example.spring2.model.member.dto.MemberVO;

public interface MemberService 
{
   // ȸ�� ��� 
   public List<MemberVO> memberList();
   // ȸ�� �Է�
   public void insertMember(MemberVO vo);
   // ȸ�� ���� �󼼺���
   public MemberVO viewMember(String userId);
   // ȸ������ ����
   public void updateMember(MemberVO vo);
   // ȸ������
   public void deleteMember(String userId);
  
}