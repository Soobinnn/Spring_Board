package com.example.spring2.service.member;


import java.util.List;

import javax.inject.Inject;

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
   
   @Override
   public void deleteMember(String userId) 
   {
       
   }
}