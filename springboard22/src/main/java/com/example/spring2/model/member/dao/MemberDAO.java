package com.example.spring2.model.member.dao;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.spring2.model.member.dto.MemberVO;

public interface MemberDAO 
{
   // 01. ȸ�� ��� 
   public List<MemberVO> memberList();
   // 02. ȸ�� �Է�
   public void insertMember(MemberVO vo);
   // 03. ȸ�� ���� �󼼺���
   public MemberVO viewMember(String userId);
   // 04. ȸ������ ����
   public void updateMember(MemberVO vo);
   // 05. ȸ������
   public void deleteMember(String userId);
   // 06. ȸ�� ���� ���� �� ������ ���� ��й�ȣ üũ 
   public boolean checkPw(String userId, String userPw);
   
   // 07. ȸ�� �α��� üũ
   public boolean loginCheck(MemberVO vo);
   // 08. ȸ�� �α��� ����
   public MemberVO viewlogin(MemberVO vo);
   // 09. ȸ�� �α׾ƿ�
   public void logout(HttpSession session);
}