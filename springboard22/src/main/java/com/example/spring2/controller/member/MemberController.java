package com.example.spring2.controller.member;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring2.model.member.dto.MemberVO;
import com.example.spring2.service.member.MemberService;

@Controller // 현재의 클래스를 controller bean에 등록시킴
public class MemberController 
{
	//로깅
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
   // MemberService 객체를 스프링에서 생성하여 주입시킴
   @Inject
   MemberService memberService;
   
   // 01 회원 목록
   // url pattern mapping
   @RequestMapping("member/list.do")
   public String memberList(Model model)
   {
   // controller => service => dao 요청
       List<MemberVO> list = memberService.memberList();
       model.addAttribute("list", list);
       return "member_list";
   }
   
   // 02_01 회원 등록 페이지로 이동
   @RequestMapping("member/write.do")
   public String memberWrite()
   {
       
       return "member_write";
   }
   
   // 02_02 회원 등록 처리 후 ==> 회원목록으로 리다이렉트
   // @ModelAttribute에 폼에서 입력한 데이터가 저장된다.
   @RequestMapping("member/insert.do")
   // * 폼에서 입력한 데이터를 받아오는 법 3가지 
   //public String memberInsert(HttpServlet request){
   //public String memberInsert(String userId, String userPw, String userName, String userEmail){
   public String memberInsert(@ModelAttribute MemberVO vo) throws Exception
   {
       // 테이블에 레코드 입력
       memberService.insertMember(vo);
       // * (/)의 유무에 차이
       // /member/list.do : 루트 디렉토리를 기준
       // member/list.do : 현재 디렉토리를 기준
       // member_list.jsp로 리다이렉트
       return "redirect:/member/list.do";
   }
   
   // 03 회원 상세정보 조회
   @RequestMapping("member/view.do")
   public String memberView(String userId, Model model)
   {
       // 회원 정보를 model에 저장
       model.addAttribute("dto", memberService.viewMember(userId));
       //System.out.println("클릭한 아이디 확인 : "+userId);
       logger.info("클릭한 아이디 : "+userId);
       // member_view.jsp로 포워드
       return "member_view";
   }
   
   // 04. 회원 정보 수정 처리
   @RequestMapping("member/update.do")
   public String memberUpdate(@ModelAttribute MemberVO vo, Model model)
   {
       // 비밀번호 체크
       boolean result = memberService.checkPw(vo.getUserId(), vo.getUserPw());
       if(result)
       { // 비밀번호가 일치하면 수정 처리후, 전체 회원 목록으로 리다이렉트
           memberService.updateMember(vo);
           return "redirect:/member/list.do";
       } 
       else 
       { // 비밀번호가 일치하지 않는다면, div에 불일치 문구 출력, viwe.jsp로 포워드
           // 가입일자, 수정일자 저장
           MemberVO vo2 = memberService.viewMember(vo.getUserId());
           vo.setUserRegdate(vo2.getUserRegdate());
           vo.setUserUpdatedate(vo2.getUserUpdatedate());
           model.addAttribute("dto", vo);
           model.addAttribute("message", "비밀번호 불일치");
           return "member_view";
       }  
   }
   // 05. 회원정보 삭제 처리
   // @RequestMapping : url mapping
   // @RequestParam : get or post방식으로 전달된 변수값
   @RequestMapping("member/delete.do")
   public String memberDelete(@RequestParam String userId, @RequestParam String userPw, Model model)
   {
       // 비밀번호 체크
       boolean result = memberService.checkPw(userId, userPw);
       if(result)
       { // 비밀번호가 맞다면 삭제 처리후, 전체 회원 목록으로 리다이렉트
           memberService.deleteMember(userId);
           return "redirect:/member/list.do";
       } 
       else 
       { // 비밀번호가 일치하지 않는다면, div에 불일치 문구 출력, viwe.jsp로 포워드
           model.addAttribute("message", "비밀번호 불일치");
           model.addAttribute("dto", memberService.viewMember(userId));
           return "member_view";
       }
   }
   
   // 06. 로그인 화면 
   @RequestMapping("member/login.do")
   public String login()
   {
       return "login";    // views/member/login.jsp로 포워드
   }
   
   // 07. 로그인 처리
   @RequestMapping("member/loginCheck.do")
   public ModelAndView loginCheck(@ModelAttribute MemberVO vo, HttpSession session)
   {
       boolean result = memberService.loginCheck(vo, session);
       ModelAndView mav = new ModelAndView();
       if (result == true) 
       { // 로그인 성공
           // main.jsp로 이동
           mav.setViewName("home");
           mav.addObject("msg", "success");
       } 
       else 
       {
    	   // 로그인 실패
           // login.jsp로 이동
           mav.setViewName("login");
           mav.addObject("msg", "failure");
       }
       
       return mav;
   }
   
   // 08. 로그아웃 처리
   @RequestMapping("member/logout.do")
   public ModelAndView logout(HttpSession session)
   {
       memberService.logout(session);
       ModelAndView mav = new ModelAndView();
       mav.setViewName("login");
       mav.addObject("msg", "logout");
       return mav;
   }
   @RequestMapping(value = "member/emailConfirm", method = RequestMethod.GET)
   public String emailConfirm(String userEmail, Model model) throws Exception 
   { 	// 이메일인증
	   	System.out.println(userEmail);
   		memberService.userAuth(userEmail);
   		model.addAttribute("user_email", userEmail);
   		return "emailConfirm";
   }
   /*   @RequestMapping(value="member/joinPost", method=RequestMethod.POST)
	public String joinPost(@ModelAttribute("uVO") MemberVO uVO) throws Exception 
   {
		logger.info("currnent join member: " + uVO.toString());
		memberService.create(uVO);
		
		return "joinPost";
	}*/
   /*@RequestMapping(value="member/joinConfirm", method=RequestMethod.GET)
	public String emailConfirm(@ModelAttribute("uVO") MemberVO uVO, Model model) throws Exception 
   {
		logger.info(uVO.getUserEmail() + ": auth confirmed");
		uVO.setAuthstatus(1);	// authstatus를 1로,, 권한 업데이트
		memberService.updateAuthstatus(uVO);
		
		model.addAttribute("auth_check", 1);
		
		return "joinPost";
	}*/
   
}