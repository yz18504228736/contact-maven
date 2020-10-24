package com.mobiletrain.web;

import com.mobiletrain.dao.ContactDAO;
import com.mobiletrain.service.ContactService;
import com.mobiletrain.service.impl.ContactServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    private ContactService service = new ContactServiceImpl();
    public void init(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        this.service = (ContactService) context.getBean("contactDAO", ContactDAO.class);
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
