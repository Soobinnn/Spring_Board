package com.example.spring2.service.member;


import java.util.List;
import javax.servlet.http.HttpSession;

import com.example.spring2.model.member.dto.MemberVO;

public interface MemberService 
{
   //01. 회원 목록 
   public List<MemberVO> memberList();
   //02. 회원 입력 ++ 이메일 추가
   public void insertMember(MemberVO vo) throws Exception;
   //03. 회원 정보 상세보기
   public MemberVO viewMember(String userId);
   //04. 회원정보 수정
   public void updateMember(MemberVO vo);
   //05. 회원삭제
   public void deleteMember(String userId);
   //06. 비밀번호 체크
   public boolean checkPw(String userId, String userPw);
   
   // 07. 회원 로그인 체크
   public boolean loginCheck(MemberVO vo, HttpSession session);
   // 08. 회원 로그인 정보
   public MemberVO viewlogin(MemberVO vo);
   // 09. 회원 로그아웃
   public void logout(HttpSession session);
   
   public void userAuth(String userEmail) throws Exception;
   
}