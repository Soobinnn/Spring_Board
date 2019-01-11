package com.example.spring2.service.member;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring2.model.member.dao.MemberDAOImpl;
import com.example.spring2.model.member.dto.MemberVO;

//���� Ŭ������ ���������� �����ϴ� service bean���� ���
@Service
public class MemberServiceImpl implements MemberService 
{
   // MemberDAOImpl ��ü�� ���������� �����Ͽ� ���Խ�Ŵ
   @Inject
   MemberDAOImpl memberDao;
   
   // 01. ȸ�� ���
   @Override
   public List<MemberVO> memberList() 
   {
       return memberDao.memberList();
   }
   
   // 02. ȸ�� ���
   @Override
   public void insertMember(MemberVO vo) 
   {
       memberDao.insertMember(vo);
   }
   
   // 03. ȸ�� ���� �� ��ȸ 
   @Override
   public MemberVO viewMember(String userId) 
   {
       return memberDao.viewMember(userId);
   }

   // 04. ȸ�� ���� ���� ó��
   @Override
   public void updateMember(MemberVO vo) 
   {
       memberDao.updateMember(vo);
   }
   
   // 05. ȸ�� ���� ���� ó��
   @Override
   public void deleteMember(String userId) 
   {
       memberDao.deleteMember(userId);
   }
   
   // 06. ȸ�� ���� ���� �� ������ ���� ��й�ȣ üũ
   @Override
   public boolean checkPw(String userId, String userPw) 
   {
       return memberDao.checkPw(userId, userPw);
   }
   
   // 07. ȸ�� �α���üũ
   @Override
   public boolean loginCheck(MemberVO vo, HttpSession session) 
   {
       boolean result = memberDao.loginCheck(vo);
       if (result) 
       { // true�� ��� ���ǿ� ���
           MemberVO vo2 = viewlogin(vo);
           // ���� ���� ���
           session.setAttribute("userId", vo2.getUserId());
           session.setAttribute("userName", vo2.getUserName());
       } 
       return result;
   }
   // 08. ȸ�� �α��� ����
   @Override
   public MemberVO viewlogin(MemberVO vo) 
   {
       return memberDao.viewlogin(vo);
   }
   
   // 09. ȸ�� �α׾ƿ�
   @Override
   public void logout(HttpSession session) 
   {
       // ���� ���� ���� ����
       // session.removeAttribute("userId");
       // ���� ������ �ʱ�ȭ ��Ŵ
       session.invalidate();
   }
}