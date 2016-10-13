package com.jwt.hibernate.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

//import com.jwt.hibernate.bean.companydetails;
import com.jwt.hibernate.bean.CompanyEventData;
import com.jwt.hibernate.bean.companydetails;

public class CompanyEventDAO {
	
	public List<CompanyEventData> repMaxima(){
		List<CompanyEventData> list = null;

    	try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();
 
            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            Query<?> query = session.createQuery("from CompanyEventData where EXTREMATYPE='max'");
            
            //1st way....
            List<CompanyEventData> result = (List<CompanyEventData>) query.list();
            
            maximaDetails(session, 38703, "2009-01-02");
            
            /*for(CompanyEventData companyeventdata : result)
            {
            	System.out.println("PermNo: "+companyeventdata.getEXTREMATYPE()+", Price: "+companyeventdata.getMETA_PRC());
            }*/
            
            transaction.commit();
            System.out.println("\n\n Retrieved joined data \n");
            return result;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
	}
	
	public void maximaDetails(Session session, int permno, String maxDate){
		List<companydetails> beforePeek = null;
		List<companydetails> afterPeek = null;
		
		Query<?> queryToGetComanyList = session.createQuery("from companydetails where PERMNO='"+permno+"'");
		List<companydetails> result = (List<companydetails>) queryToGetComanyList.list();
		//System.out.println(result.get(0).getDate());
		
		int i;
		for(i=0 ; i < result.size(); i++){
			if(result.get(i).getDate().equals(maxDate)){
				double maxPRC = result.get(i).getPseudo_PRC();
				double lowerLevelPRC = maxPRC * 95/100 ;
				//System.out.println(i);
				
				int j;
				for(j=i; j < result.size(); j++){
					if((result.get(j).getPseudo_PRC() < lowerLevelPRC || result.get(j).getPseudo_PRC() == lowerLevelPRC) && (result.get(j).getPseudo_PRC() < result.get(i).getPseudo_PRC())){
						System.out.println(result.get(j).getPseudo_PRC());
						System.out.println(result.get(j).getDate());
						break;
					}
				}
				
				int k;
				for(k=i; k > 0; k--){
					if((result.get(k).getPseudo_PRC() < lowerLevelPRC || result.get(k).getPseudo_PRC() == lowerLevelPRC) && (result.get(k).getPseudo_PRC() < result.get(i).getPseudo_PRC())){
						System.out.println(result.get(k).getPseudo_PRC());
						System.out.println(result.get(k).getDate());
						break;
					}
				}
			}
		}	
	}
}
