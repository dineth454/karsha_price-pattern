package com.jwt.hibernate.controller;
 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jwt.hibernate.bean.Company;
import com.jwt.hibernate.dao.CompanyDAO;
 
public class CompanyControllerSrvlt extends HttpServlet {
     
    private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        //String userName = request.getParameter("userName");
        //int userName1 = Integer.parseInt(userName);
        //String password = request.getParameter("password1");
 
        HttpSession session = request.getSession(true);
        try {
            CompanyDAO companyDAO = new CompanyDAO();
            //userDAO.addUserDetails(userName1, password);
            List<Company> companylist = companyDAO.getDetails();
            companyDAO.genJson(companylist, request, response);
            //response.sendRedirect("Success");
            //response.sendRedirect("/Price_Pattern/json/graph.jsp");
        } catch (Exception e) {
 
            e.printStackTrace();
        }
 
    }
}

