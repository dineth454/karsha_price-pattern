package com.jwt.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jwt.hibernate.bean.Company;
import com.jwt.hibernate.bean.Naics;

public class CompanyTree {
	
	public List<Company> getCompanyList(int code){
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
            Query<?> query = session.createQuery("from Company where NAICS like '"+code+"%'");
            
            List<Company> result = (List<Company>) query.list();
   
            for(Company companydetails : result)
            {
            	System.out.println("name: "+companydetails.getCOMNAM()+", naics: "+companydetails.getNAICS());
            }
            
            transaction.commit();
            System.out.println("\n\n Retrieved \n");
            return result;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
	return list;
	}
	
	
	public List<Naics> getNaicsList(){
		List<Naics> list = null;
		try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();
 
            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            Query<?> query = session.createQuery("from Naics");
            
            List<Naics> result = (List<Naics>) query.list();
   
            for(Naics companydetails : result)
            {
            	System.out.println("code: "+companydetails.getCode()+", cName: "+companydetails.getIndustryName());
            }
            
            transaction.commit();
            System.out.println("\n\n Retrieved \n");
            return result;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
	return list;
	}
}
