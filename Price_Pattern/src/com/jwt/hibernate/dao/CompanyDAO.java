package com.jwt.hibernate.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.jwt.hibernate.bean.Company;


public class CompanyDAO {
	public List<Company> getDetails(){
		List<Company> list = null;
		
    	try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();
 
            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            Query<?> query = session.createQuery("from Company");
            
            //1st way....
            List<Company> result = (List<Company>) query.list();
            
            //Gson gson = new Gson();
            //String jsonString = gson.toJson(result);
            
            for(Company company : result)
            {
            	System.out.println("PERMNO: "+company.getPERMNO()+", PRC: "+company.getPRC());
            }
            
            // 2nd way......
            //List users = (List)query.list();
            //for (Iterator iterator = users.iterator(); iterator.hasNext();)
            //{
            //    User user = (User) iterator.next();
            //    System.out.println("Roll Number: "+user.getUserName()+", Student Name: "+user.getPassword1());
            //}
            
            transaction.commit();
            System.out.println("\n\n Retrieved \n");
            return result;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
    	
    }
	
	public void genJson(List<Company> list, HttpServletRequest request, HttpServletResponse response){
		String theList = "";
		Gson gson = new Gson();
		theList = gson.toJson(list);
		//System.out.print(theList);
		
		String userPath = request.getServletPath();
		try {
			PrintWriter pwr = response.getWriter();
			//pwr.print(theList);
			if (userPath.equals("/json")) {
				pwr.print(theList);
			}else{}
			System.out.print("success inside genJson method..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
