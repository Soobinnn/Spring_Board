package com.example.spring2.service.member;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring2.common.MailHandler;
import com.example.spring2.common.TempKey;
import com.example.spring2.model.member.dao.MemberDAOImpl;
import com.example.spring2.model.member.dto.MemberVO;

//현재 클래스를 스프링에서 관리하는 service bean으로 등록
@Service
public class MemberServiceImpl implements MemberService 
{
   // MemberDAOImpl 객체를 스프링에서 생성하여 주입시킴
   @Inject
   MemberDAOImpl memberDao;
   
   @Inject
	private JavaMailSender mailSender;	
   	
   // 01. 회원 목록
   @Override
   public List<MemberVO> memberList() 
   {
       return memberDao.memberList();
   }
   
   // 02. 회원 등록 ++ 이메일 인증
   @Override
   @Transactional
   public void insertMember(MemberVO vo) throws Exception
   {
       memberDao.insertMember(vo);
       
   		// 임의의 authkey 생성
		String authkey = new TempKey().getKey(50, false);

		memberDao.createAuthKey(vo.getUserEmail(), authkey);
		/*	vo.setAuthkey(authkey);
		memberDao.updateAuthkey(vo);*/
		// mail 작성 관련 
		MailHandler sendMail = new MailHandler(mailSender);
	
		sendMail.setSubject("[DevelopPR] 회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
				.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:8080/spring2/member/emailConfirm?userEmail=")
				.append(vo.getUserEmail())
				.append("&authkey=")
				.append(authkey)					
				.append("' target='_blenk'>이메일 인증 확인</a>")
				.toString());
				sendMail.setFrom("DevelopPRmail@gmail.com", "DevelopPR");
				sendMail.setTo(vo.getUserEmail());
				sendMail.send();
   }
   
   // 03. 회원 정보 상세 조회 
   @Override
   public MemberVO viewMember(String userId) 
   {
       return memberDao.viewMember(userId);
   }

   // 04. 회원 정보 수정 처리
   @Override
   public void updateMember(MemberVO vo) 
   {
       memberDao.updateMember(vo);
   }
   
   // 05. 회원 정보 삭제 처리
   @Override
   public void deleteMember(String userId) 
   {
       memberDao.deleteMember(userId);
   }
   
   // 06. 회원 정보 수정 및 삭제를 위한 비밀번호 체크
   @Override
   public boolean checkPw(String userId, String userPw) 
   {
       return memberDao.checkPw(userId, userPw);
   }
   
   // 07. 회원 로그인체크
   @Override
   public boolean loginCheck(MemberVO vo, HttpSession session) 
   {
       boolean result = memberDao.loginCheck(vo);
       if (result) 
       { // true일 경우 세션에 등록
           MemberVO vo2 = viewlogin(vo);
           // 세션 변수 등록
           session.setAttribute("userId", vo2.getUserId());
           session.setAttribute("userName", vo2.getUserName());
       } 
       return result;
   }
   // 08. 회원 로그인 정보
   @Override
   public MemberVO viewlogin(MemberVO vo) 
   {
       return memberDao.viewlogin(vo);
   }
   
   // 09. 회원 로그아웃
   @Override
   public void logout(HttpSession session) 
   {
       // 세션 변수 개별 삭제
       // session.removeAttribute("userId");
       // 세션 정보를 초기화 시킴
       session.invalidate();
   }
   
   @Override
	public void userAuth(String userEmail) throws Exception 
   {
		memberDao.userAuth(userEmail);
	}

}