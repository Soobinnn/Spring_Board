package com.example.spring2.model.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
	   System.out.println(vo.getUserId());
	   System.out.println(vo.getUserEmail());
	   System.out.println(vo.getUserPw());
	   System.out.println(vo.getUserName());
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
   
   // 05. ȸ�� ���� ���� ó��
   @Override
   public void deleteMember(String userId) 
   {
       sqlSession.delete("member.deleteMember",userId);
   }

   // 06. ȸ�� ���� ���� �� ������ ���� ��й�ȣ üũ
   @Override
   public boolean checkPw(String userId, String userPw) 
   {
       boolean result = false;
       Map<String, String> map = new HashMap<String, String>();
       map.put("userId", userId);
       map.put("userPw", userPw);
       int count = sqlSession.selectOne("member.checkPw", map);
       if(count == 1) result= true;
       return result;
   }
   
   // 07. ȸ�� �α���üũ
   @Override
   public boolean loginCheck(MemberVO vo) 
   {
       String name = sqlSession.selectOne("member.loginCheck", vo);
       return (name == null) ? false : true;
   }
   // 08. ȸ�� �α��� ����
   @Override
   public MemberVO viewlogin(MemberVO vo) 
   {
       return sqlSession.selectOne("member.viewlogin", vo);
   }
   // 09. ȸ�� �α׾ƿ�
   @Override
   public void logout(HttpSession sessin) 
   {
   }
   
   @Override
   public void createAuthKey(String user_email, String user_authCode) throws Exception 
   {
   		// TODO Auto-generated method stub
   	MemberVO vo = new MemberVO();
   	
   	vo.setUserAuthCode(user_authCode);
   	vo.setUserEmail(user_email);

   	sqlSession.selectOne("member.createAuthKey", vo);
   }
   @Override
   public void userAuth(String user_email) throws Exception 
   {
   		// TODO Auto-generated method stub
   		sqlSession.update("member.userAuth", user_email);
   }
}
