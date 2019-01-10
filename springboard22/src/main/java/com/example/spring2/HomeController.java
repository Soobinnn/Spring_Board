package com.example.spring2;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
// Controller �ֳ����̼�
@Controller
public class HomeController 
{
    
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    // ���������� mapping ����
    @RequestMapping("/")
    public String main(Model model)
    {
        // model : �����͸� ��� �׸� ����, map������ ����ȴ�.
        // model.addAttribute("������", ��);
        model.addAttribute("msg", "Ȩ������ �湮�� ȯ���մϴ�!");
        return "main"; // main.jsp�� ������
    }
    
    // url mapping
    // �⺻, ��Ʈ ������ => home�޼��� ȣ��
    @RequestMapping(value = "home.do", method = RequestMethod.GET)
    public String home(Locale locale, Model model) 
    {
        logger.info("Welcome home! The client locale is {}.", locale);
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        // ��(������ request ��ü�� ��ü�� ��)
        model.addAttribute("serverTime", formattedDate );
        
        // home.jsp�� ������
        // => servlet-context.xml
        // <beans:property name="prefix" value="/WEB-INF/views/" />
        // <beans:property name="suffix" value=".jsp" />
        // ���丮(���ξ�)�� jsp(���̾�)Ȯ���ڸ� �����ϰ� �̸��� �ۼ��ϵ��� ����
        return "home";
    }
}