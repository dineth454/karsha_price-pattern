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

	//return a list of maxima detail list
	public List<List<companydetails>> retMaxima(int permno){
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
            
            //query for get maxima 
            Query<?> query = session.createQuery("from CompanyEventData where EXTREMATYPE='max' and META_PERMNO='"+permno+"'");           
            List<CompanyEventData> result = (List<CompanyEventData>) query.list();
            
            //query for get company details according permno
            Query<?> queryToGetComanyList = session.createQuery("from companydetails where PERMNO='"+permno+"'");
    		List<companydetails> resultComanyList = (List<companydetails>) queryToGetComanyList.list();
            
    		//put maxima details to the return list
    		List<List<companydetails>> maximaDataCollection = new ArrayList<List<companydetails>>();
            int i;
    		for(i=0; i < result.size(); i++){
    			maximaDataCollection.add(getMaximaDetails(resultComanyList, result.get(i).getMETA_DATE()));
    		}
            
            transaction.commit();
            System.out.println("\n\n correct \n");
            return maximaDataCollection;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
	}
	
	
	
	//return minima list
	public List<List<companydetails>> retMinima(int permno){
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
            
            Query<?> query = session.createQuery("from CompanyEventData where EXTREMATYPE='min' and META_PERMNO='"+permno+"'");           
            List<CompanyEventData> result = (List<CompanyEventData>) query.list();
            
            Query<?> queryToGetComanyList = session.createQuery("from companydetails where PERMNO='"+permno+"'");
    		List<companydetails> resultComanyList = (List<companydetails>) queryToGetComanyList.list();
            
    		List<List<companydetails>> minimaDataCollection = new ArrayList<List<companydetails>>();
            int i;
    		for(i=0; i < result.size(); i++){
    			minimaDataCollection.add(getMinimaDetails(resultComanyList, result.get(i).getMETA_DATE()));
    		}
            
            transaction.commit();
            System.out.println("\n\n correct \n");
            return minimaDataCollection;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
	}
	
	
	
	//get maxima details (values of maxima date range)
	public List<companydetails> getMaximaDetails(List<companydetails> result, String maxDate){
		List<companydetails> maximaRange = new ArrayList<companydetails>();

		int i;
		for(i=0 ; i < result.size(); i++){
			//find index of maximaDate
			if(result.get(i).getDate().equals(maxDate)){
				double maxPRC = result.get(i).getPseudo_PRC();
				double lowerLevelPRC = maxPRC * (100-1)/100 ;
				
				//find down lowerLeverPrice of maxima
				int k;
				for(k=i; k > 0; k--){
					if((result.get(k).getPseudo_PRC() <= lowerLevelPRC)){
						System.out.println(result.get(k).getPseudo_PRC());
						System.out.println(result.get(k).getDate());
						maximaRange.add(result.get(k));
						break;
					}
				}
				
				maximaRange.add(result.get(i));
				
				//find up lowerLeverPrice of maxima
				int j;
				for(j=i; j < result.size(); j++){
					if((result.get(j).getPseudo_PRC() <= lowerLevelPRC)){
						System.out.println(result.get(j).getPseudo_PRC());
						System.out.println(result.get(j).getDate());
						maximaRange.add(result.get(j));
						break;
					}
				}

				//if maxima exists on first 9 days 
				int l;
				if((i-9)<0){
					for(l = 0; l <= i + 10; l++){
						maximaRange.add(result.get(l));
					}
				}
				
				//if maxima exists on last 10 days
				if((i+10)>result.size()){
					for(l = i - 9; l < result.size(); l++){
						maximaRange.add(result.get(l));
					}
				}
				
				//default maxima 
				if((i-9)>=0 && (i+10)<=result.size()){
					for(l = i - 9; l <= i + 10; l++){
						maximaRange.add(result.get(l));
					}
				}
								
			}
		}
		
		return maximaRange;
	}
	
	
	
	
	//get minima details (values of minima date range)
		public List<companydetails> getMinimaDetails(List<companydetails> result, String minDate){
			List<companydetails> minimaRange = new ArrayList<companydetails>();

			int i;
			for(i=0 ; i < result.size(); i++){
				if(result.get(i).getDate().equals(minDate)){
					double minPRC = result.get(i).getPseudo_PRC();
					double upperLevelPRC = minPRC * (100+1)/100 ;
					
					int j;
					for(j=i; j < result.size(); j++){
						if((result.get(j).getPseudo_PRC() >= upperLevelPRC)){
							System.out.println(result.get(j).getPseudo_PRC());
							System.out.println(result.get(j).getDate());
							break;
						}
					}
					
					
					int k;
					for(k=i; k > 0; k--){
						if((result.get(k).getPseudo_PRC() >= upperLevelPRC)){
							System.out.println(result.get(k).getPseudo_PRC());
							System.out.println(result.get(k).getDate());
							break;
						}
					}

					int l;
					if((i-9)<0){
						for(l = 0; l <= i + 10; l++){
							minimaRange.add(result.get(l));
						}
					}
					
					if((i+10)>result.size()){
						for(l = i - 9; l < result.size(); l++){
							minimaRange.add(result.get(l));
						}
					}
					
					if((i-9)>=0 && (i+10)<=result.size()){
						for(l = i - 9; l <= i + 10; l++){
							minimaRange.add(result.get(l));
						}
					}
									
				}
			}
			return minimaRange;
		}
}
