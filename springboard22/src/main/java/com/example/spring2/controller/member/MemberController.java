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

@Controller // ������ Ŭ������ controller bean�� ��Ͻ�Ŵ
public class MemberController 
{
	//�α�
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
   // MemberService ��ü�� ���������� �����Ͽ� ���Խ�Ŵ
   @Inject
   MemberService memberService;
   
   // 01 ȸ�� ���
   // url pattern mapping
   @RequestMapping("member/list.do")
   public String memberList(Model model)
   {
   // controller => service => dao ��û
       List<MemberVO> list = memberService.memberList();
       model.addAttribute("list", list);
       return "member_list";
   }
   
   // 02_01 ȸ�� ��� �������� �̵�
   @RequestMapping("member/write.do")
   public String memberWrite()
   {
       
       return "member_write";
   }
   
   // 02_02 ȸ�� ��� ó�� �� ==> ȸ��������� �����̷�Ʈ
   // @ModelAttribute�� ������ �Է��� �����Ͱ� ����ȴ�.
   @RequestMapping("member/insert.do")
   // * ������ �Է��� �����͸� �޾ƿ��� �� 3���� 
   //public String memberInsert(HttpServlet request){
   //public String memberInsert(String userId, String userPw, String userName, String userEmail){
   public String memberInsert(@ModelAttribute MemberVO vo) throws Exception
   {
       // ���̺� ���ڵ� �Է�
       memberService.insertMember(vo);
       // * (/)�� ������ ����
       // /member/list.do : ��Ʈ ���丮�� ����
       // member/list.do : ���� ���丮�� ����
       // member_list.jsp�� �����̷�Ʈ
       return "redirect:/member/list.do";
   }
   
   // 03 ȸ�� ������ ��ȸ
   @RequestMapping("member/view.do")
   public String memberView(String userId, Model model)
   {
       // ȸ�� ������ model�� ����
       model.addAttribute("dto", memberService.viewMember(userId));
       //System.out.println("Ŭ���� ���̵� Ȯ�� : "+userId);
       logger.info("Ŭ���� ���̵� : "+userId);
       // member_view.jsp�� ������
       return "member_view";
   }
   
   // 04. ȸ�� ���� ���� ó��
   @RequestMapping("member/update.do")
   public String memberUpdate(@ModelAttribute MemberVO vo, Model model)
   {
       // ��й�ȣ üũ
       boolean result = memberService.checkPw(vo.getUserId(), vo.getUserPw());
       if(result)
       { // ��й�ȣ�� ��ġ�ϸ� ���� ó����, ��ü ȸ�� ������� �����̷�Ʈ
           memberService.updateMember(vo);
           return "redirect:/member/list.do";
       } 
       else 
       { // ��й�ȣ�� ��ġ���� �ʴ´ٸ�, div�� ����ġ ���� ���, viwe.jsp�� ������
           // ��������, �������� ����
           MemberVO vo2 = memberService.viewMember(vo.getUserId());
           vo.setUserRegdate(vo2.getUserRegdate());
           vo.setUserUpdatedate(vo2.getUserUpdatedate());
           model.addAttribute("dto", vo);
           model.addAttribute("message", "��й�ȣ ����ġ");
           return "member_view";
       }  
   }
   // 05. ȸ������ ���� ó��
   // @RequestMapping : url mapping
   // @RequestParam : get or post������� ���޵� ������
   @RequestMapping("member/delete.do")
   public String memberDelete(@RequestParam String userId, @RequestParam String userPw, Model model)
   {
       // ��й�ȣ üũ
       boolean result = memberService.checkPw(userId, userPw);
       if(result)
       { // ��й�ȣ�� �´ٸ� ���� ó����, ��ü ȸ�� ������� �����̷�Ʈ
           memberService.deleteMember(userId);
           return "redirect:/member/list.do";
       } 
       else 
       { // ��й�ȣ�� ��ġ���� �ʴ´ٸ�, div�� ����ġ ���� ���, viwe.jsp�� ������
           model.addAttribute("message", "��й�ȣ ����ġ");
           model.addAttribute("dto", memberService.viewMember(userId));
           return "member_view";
       }
   }
   
   // 06. �α��� ȭ�� 
   @RequestMapping("member/login.do")
   public String login()
   {
       return "login";    // views/member/login.jsp�� ������
   }
   
   // 07. �α��� ó��
   @RequestMapping("member/loginCheck.do")
   public ModelAndView loginCheck(@ModelAttribute MemberVO vo, HttpSession session)
   {
       boolean result = memberService.loginCheck(vo, session);
       ModelAndView mav = new ModelAndView();
       if (result == true) 
       { // �α��� ����
           // main.jsp�� �̵�
           mav.setViewName("home");
           mav.addObject("msg", "success");
       } 
       else 
       {
    	   // �α��� ����
           // login.jsp�� �̵�
           mav.setViewName("login");
           mav.addObject("msg", "failure");
       }
       
       return mav;
   }
   
   // 08. �α׾ƿ� ó��
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
   { 	// �̸�������
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
		uVO.setAuthstatus(1);	// authstatus�� 1��,, ���� ������Ʈ
		memberService.updateAuthstatus(uVO);
		
		model.addAttribute("auth_check", 1);
		
		return "joinPost";
	}*/
   
}