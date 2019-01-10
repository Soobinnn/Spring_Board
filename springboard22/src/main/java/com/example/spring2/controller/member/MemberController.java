package com.example.spring2.controller.member;


import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring2.model.member.dto.MemberVO;
import com.example.spring2.service.member.MemberService;

@Controller // ������ Ŭ������ controller bean�� ��Ͻ�Ŵ
public class MemberController 
{
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
   public String memberInsert(@ModelAttribute MemberVO vo)
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
   public String memberUpdate(@ModelAttribute MemberVO vo)
   {
       memberService.updateMember(vo);
       return "redirect:/member/list.do";
   }
}