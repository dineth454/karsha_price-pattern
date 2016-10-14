package com.jwt.hibernate.dao;

import java.util.ArrayList;
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

	public List<List<companydetails>> repMaxima(int permno){
		List<List<companydetails>> list = null;
		
    	try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();
 
            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            Query<?> query = session.createQuery("from CompanyEventData where EXTREMATYPE='max' and META_PERMNO='"+permno+"'");           
            List<CompanyEventData> result = (List<CompanyEventData>) query.list();
            
            Query<?> queryToGetComanyList = session.createQuery("from companydetails where PERMNO='"+permno+"'");
    		List<companydetails> resultComanyList = (List<companydetails>) queryToGetComanyList.list();
            
    		List<List<companydetails>> maximaDataCollection = new ArrayList<List<companydetails>>();
            int i;
    		for(i=0; i < result.size()-1; i++){
    			maximaDataCollection.add(getMaximaDetails(resultComanyList, result.get(i).getMETA_DATE()));
    		}
            /*List<companydetails> maximaDataList = getMaximaDetails(resultComanyList, "2009-01-02");
            
            for(companydetails maxa : maximaDataList)
            {
            	System.out.println("PermNo: " + maxa.getDate()+", Price: " + maxa.getPRC());
            }*/
            
            transaction.commit();
            System.out.println("\n\n correct \n");
            return maximaDataCollection;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
	}
	
	
	public List<companydetails> getMaximaDetails(List<companydetails> result, String maxDate){
		List<companydetails> maximaRange = new ArrayList<companydetails>();
		
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
				
				int l;
				for(l = i - 10; l < i + 10; l++){
					maximaRange.add(result.get(l));
					//System.out.println(result.get(l).getDate());
				}				
			}
		}
		return maximaRange;
	}
}
