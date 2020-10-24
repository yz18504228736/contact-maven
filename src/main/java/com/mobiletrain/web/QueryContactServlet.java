package com.mobiletrain.web;

import com.mobiletrain.dao.ContactDAO;
import com.mobiletrain.domain.Contact;
import com.mobiletrain.service.ContactService;
import com.mobiletrain.service.impl.ContactServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/query_contact")
public class QueryContactServlet extends HttpServlet {
    private ContactService service = new ContactServiceImpl();
    public void init(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        this.service = (ContactService) context.getBean("contactDAO", ContactDAO.class);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 接收数据
        // 2. 处理数据
        List<Contact> contacts = service.queryAll();

        // 3. 响应数据
        request.setAttribute("contacts", contacts);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
