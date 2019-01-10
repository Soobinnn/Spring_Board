package com.example.spring2.model.member.dao;

import java.util.List;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring2.model.member.dto.MemberVO;

//���� Ŭ������ DAO bean���� ��Ͻ�Ŵ
@Repository
public class MemberDAOImpl implements MemberDAO 
{  
   // SqlSession ��ü�� ���������� �����Ͽ� ���Խ����ش�.
   // �������� ����(Dependency Injection, DI)
   // ������ ����
   // IoC(Inversion of Control, ������ ����)
   @Inject
   // Inject�ֳ����̼��� ������ sqlSession�� null����������
   // Inject�ֳ����̼��� ������ �ܺο��� ��ü�� ���Խ����ְ� �ȴ�. 
   // try catch��, finally��, ��ü�� close�� �ʿ䰡 ��������.
   SqlSession sqlSession;
   
   // 01. ȸ�� ���
   @Override
   public List<MemberVO> memberList() 
   {
       return sqlSession.selectList("member.memberList");
   }
   // 02. ȸ�� ���
   @Override
   public void insertMember(MemberVO vo) 
   {
       sqlSession.insert("member.insertMember", vo);
   }
   
   // 03. ȸ�� ���� �� ��ȸ
   @Override
   public MemberVO viewMember(String userId) 
   {
       return sqlSession.selectOne("member.viewMember", userId);
   }
   
   // 04. ȸ�� ���� ���� ó��
   @Override
   public void updateMember(MemberVO vo) 
   {
	   System.out.println(vo.getUserPw());
	   System.out.println(vo.getUserId());
	   System.out.println(vo.getUserName());
	   System.out.println(vo.getUserEmail());
       sqlSession.update("member.updateMember", vo);
   }

   @Override
   public void deleteMember(String userId) 
   {
       // TODO Auto-generated method stub
   }
}
