package com.example.spring2.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
 
import com.example.spring2.model.board.dto.ReplyVO;
import com.example.spring2.service.board.ReplyService;
 
// REST : Representational State Transfer
// �ϳ��� URI�� �ϳ��� ������ ���ҽ��� ��ǥ�ϵ��� ����� ����
 
// http://localhost/spring02/list?bno=1 ==> url+�Ķ����
// http://localhost/spring02/list/1 ==> url
// RestController�� ������ 4.0���� ����
// @Controller, @RestController �������� �޼��尡 ����Ǹ� ȭ����ȯ�� ����
//@Controller
@RestController
@RequestMapping("/reply/*")
public class ReplyController 
{
    @Inject
    ReplyService replyService;
    
    // ��� �Է�
    @RequestMapping("insert.do")
    public void insert(@ModelAttribute ReplyVO vo, HttpSession session)
    {
        String userId = (String) session.getAttribute("userId");
        vo.setReplyer(userId);
        replyService.create(vo);
    }
    
    // ��� ���(@Controller��� : veiw(ȭ��)�� ����)
    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam int bno, ModelAndView mav)
    {
        List<ReplyVO> list = replyService.list(bno);
        // ���̸� ����
        mav.setViewName("board/replyList");
        // �信 ������ ������ ����
        mav.addObject("list", list);
        // replyList.jsp�� ������
        return mav;
    }
    
    // ��� ���(@RestController Json������� ó�� : �����͸� ����)
    @RequestMapping("listJson.do")
    @ResponseBody // ���ϵ����͸� json���� ��ȯ(��������)
    public List<ReplyVO> listJson(@RequestParam int bno)
    {
        List<ReplyVO> list = replyService.list(bno);
        return list;
    }
}
