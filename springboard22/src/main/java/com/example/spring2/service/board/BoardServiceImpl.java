package com.example.spring2.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
 
import com.example.spring2.model.board.dao.BoardDAO;
import com.example.spring2.model.board.dto.BoardVO;
 
@Service
public class BoardServiceImpl implements BoardService 
{    
    @Inject
    BoardDAO boardDao;
   
    // 01. �Խñ� ��ü ���
    /*    @Override
    public List<BoardVO> listAll() throws Exception 
    {
        return boardDao.listAll();
    }*/
    
    // 01. �Խñ� ��ü ��� boardDAO.listAll�޼��� ȣ��
    @Override
    public List<BoardVO> listAll(String searchOption, String keyword) throws Exception 
    {
        return boardDao.listAll(searchOption, keyword);
    }
    
    // 02. �Խñ۾���
    @Override
    public void insert(BoardVO vo) throws Exception 
    {
        String title = vo.getTitle();
        String content = vo.getContent();
        String writer = vo.getWriter();
        // *�±׹��� ó�� (< ==> &lt; > ==> &gt;)
        // replace(A, B) A�� B�� ����
        title = title.replace("<", "&lt;");
        title = title.replace("<", "&gt;");
        writer = writer.replace("<", "&lt;");
        writer = writer.replace("<", "&gt;");
        // *���鹮�� ó��
        title = title.replace("  ",    "&nbsp;&nbsp;");
        writer = writer.replace("  ",    "&nbsp;&nbsp;");
        // *�ٹٲ� ����ó��
        content = content.replace("\n", "<br>");
        vo.setTitle(title);
        vo.setContent(content);
        vo.setWriter(writer);
        boardDao.insert(vo);
    }
    
    // 04. �Խñ� ��ȸ�� ����
    @Override
    public void increaseViewcnt(int bno, HttpSession session) throws Exception 
    {
        long update_time = 0;
        // ���ǿ� ����� ��ȸ�ð� �˻�
        // ���ʷ� ��ȸ�� ��� ���ǿ� ����� ���� ���� ������ if���� ����X
        if(session.getAttribute("update_time_"+bno) != null)
        {
                                // ���ǿ��� �о����
         /*  update_time = (long)session.getAttribute("update_time_"+bno);*/
        }
        // �ý����� ����ð��� current_time�� ����
        long current_time = System.currentTimeMillis();
        // �����ð��� ��� �� ��ȸ�� ���� ó�� 24*60*60*1000(24�ð�)
        // �ý�������ð� - �����ð� > �����ð�(��ȸ�� ������ �����ϵ��� ������ �ð�)
        if(current_time - update_time > 5*1000)
        {
            boardDao.increaseViewcnt(bno);
            // ���ǿ� �ð��� ���� : "update_time_"+bno�� �ٸ������� �ߺ����� �ʰ� ����� ��
            session.setAttribute("update_time_"+bno, current_time);
            
        }
    }
    
    // 03. �Խñ� �󼼺���
    @Override
    public BoardVO view(int bno) throws Exception 
    {
        return boardDao.view(bno);
    }
    
    // 05. �Խñ� ����
    @Override
    public void updateArticle(BoardVO vo) throws Exception 
    {
        boardDao.updateArticle(vo);
    }
    
    // 06. �Խñ� ����
    @Override
    public void deleteArticle(int bno) throws Exception 
    {
        boardDao.deleteArticle(bno);
    }

    // 07. �Խñ� ���ڵ� ���� boardDao.countArticle�޼��� 
    @Override
    public int countArticle(String searchOption, String keyword) throws Exception 
    {
        return boardDao.countArticle(searchOption, keyword);
    }
    
 
}