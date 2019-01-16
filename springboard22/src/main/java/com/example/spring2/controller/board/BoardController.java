package com.example.spring2.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring2.model.board.dto.BoardVO;
import com.example.spring2.service.board.BoardPager;
import com.example.spring2.service.board.BoardService;

@Controller    // ���� Ŭ������ ��Ʈ�ѷ� ��(bean)���� ���
@RequestMapping("/board/*")
public class BoardController 
{   
    // �������� ���� => BoardServiceImpl ����
    // IoC �������� ����
    @Inject
    BoardService boardService;  
    
    // 01. �Խñ� ���
    /*@RequestMapping("list.do")
    public ModelAndView list() throws Exception
    {
        List<BoardVO> list = boardService.listAll();
        // ModelAndView - �𵨰� ��
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", list); // �����͸� ����
        mav.setViewName("list"); // �並 list.jsp�� ����
        return mav; // list.jsp�� List�� ���޵ȴ�.
    }*/
  
    // 01. �Խñ� ��� + �˻��߰�
    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam(defaultValue="title") String searchOption,@RequestParam(defaultValue="") String keyword, @RequestParam(defaultValue="1")int curPage) throws Exception
    {
    		
    		// ���ڵ��� ����
    		int count = boardService.countArticle(searchOption, keyword);
    		
    		 // ������ ������ ���� ó��
    	    BoardPager boardPager = new BoardPager(count, curPage);
    	    int start = boardPager.getPageBegin();
    	    int end = boardPager.getPageEnd();
    	   /* List<BoardVO> list = boardService.listAll(searchOption, keyword); */    	    
    	    List<BoardVO> list = boardService.listAll(start, end, searchOption, keyword);
    		
    		/*mav.addObject("list", list); // �����͸� ����
			mav.addObject("count", count);
			mav.addObject("searchOption", searchOption);
			mav.addObject("keyword", keyword);*/
    			// �����͸� �ʿ� ����
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("list", list); // list
    			map.put("count", count); // ���ڵ��� ����
    			map.put("searchOption", searchOption); // �˻��ɼ�
    			map.put("keyword", keyword); // �˻�Ű����
    		    map.put("boardPager", boardPager);
    		    
    		    // ModelAndView - �𵨰� ��
        		ModelAndView mav = new ModelAndView();
        		
    			mav.addObject("map", map); // �ʿ� ����� �����͸� mav�� ����
    			mav.setViewName("list"); // �並 list.jsp�� ����
    			return mav; // list.jsp�� List�� ���޵ȴ�.
    }
    
    // 02_01. �Խñ� �ۼ�ȭ��
    // @RequestMapping("board/write.do")
    // value="", method="���۹��"
    @RequestMapping(value="write.do", method=RequestMethod.GET)
    public String write()
    {
        return "write"; // write.jsp�� �̵�
    }
    
    // 02_02. �Խñ� �ۼ�ó��
 /*   @RequestMapping(value="insert.do", method=RequestMethod.POST)
    public String insert(@ModelAttribute BoardVO vo) throws Exception
    {
    	System.out.println(vo.getRegdate());
        boardService.insert(vo);
        return "redirect:list.do";
    }*/
    @RequestMapping(value="insert.do", method=RequestMethod.POST)
    public String insert(@ModelAttribute BoardVO vo, HttpSession session) throws Exception
    {
        // session�� ����� userId�� writer�� ����
        String writer = (String) session.getAttribute("userId");
        // vo�� writer�� ����
        vo.setWriter(writer);
        boardService.insert(vo);
        return "redirect:list.do";
    }
    
    // 03. �Խñ� �󼼳��� ��ȸ, �Խñ� ��ȸ�� ���� ó��
    // @RequestParam : get/post������� ���޵� ���� 1��
    // HttpSession ���ǰ�ü
    @RequestMapping(value="view.do", method=RequestMethod.GET)
    public ModelAndView view(@RequestParam int bno, HttpSession session,@RequestParam int curPage) throws Exception
    {
    	BoardPager boardPager = new BoardPager(curPage);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("boardPager", boardPager);
        // ��ȸ�� ���� ó��
        boardService.increaseViewcnt(bno, session);
        // ��(������)+��(ȭ��)�� �Բ� �����ϴ� ��ü
        ModelAndView mav = new ModelAndView();
        // ���� �̸�
        mav.setViewName("view");
        // �信 ������ ������
        mav.addObject("dto", boardService.view(bno));
        mav.addObject("map", map); // �ʿ� ����� �����͸� mav�� ����
        return mav;
    }
   

    
    // 04. �Խñ� ����
    // ������ �Է��� ������� @ModelAttribute BoardVO vo�� ���޵�
    @RequestMapping(value="update.do", method=RequestMethod.POST)
    public String update(@ModelAttribute BoardVO vo, HttpSession session) throws Exception
    {
    	String _writer = (String) session.getAttribute("userId");
    	vo.setWriter(_writer);
        boardService.updateArticle(vo);
        return "redirect:list.do";
    }
    
    // 05. �Խñ� ����
    @RequestMapping("delete.do")
    public String delete(@RequestParam int bno) throws Exception
    {
        boardService.deleteArticle(bno);
        return "redirect:list.do";
    }
    
}