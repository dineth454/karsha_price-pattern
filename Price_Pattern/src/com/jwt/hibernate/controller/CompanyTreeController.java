package com.jwt.hibernate.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jwt.hibernate.bean.Company;
import com.jwt.hibernate.bean.Naics;

@Path("/CompanyTreeController")
public class CompanyTreeController {

	@GET
	@Produces("application/json")
	public Response genTreeTwo(){
		JSONObject nullJsonObject = new JSONObject();
		try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();
 
            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            
            Query<?> query1 = session.createQuery("from Naics");
            List<Naics> naicsList = (List<Naics>) query1.list();
            

            JSONArray jsonArray1 = new JSONArray();
    		for(Naics naics : naicsList)
            {
    			JSONObject jsonObject = new JSONObject();
    			jsonObject.put("name",naics.getIndustryName());
    			jsonObject.put("sector","sec : "+naics.getCode());
    			jsonObject.put("children",companyOnNaicsTwo(session, naics.getCode()));
    			jsonArray1.put(jsonObject);
            }
    		
    		
    		//json for root of tree 
    		JSONObject jsonObject1 = new JSONObject();
    		jsonObject1.put("name","All Companies");
    		jsonObject1.put("sector","All Companies");
    		jsonObject1.put("children",jsonArray1);
    		
    		
    		transaction.commit();
            System.out.println("\n\n Retrieved \n");
    		return Response.status(200).entity(jsonObject1.toString()).build();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
		
		return Response.status(200).entity(nullJsonObject.toString()).build();
	}
	
	
	public JSONArray companyOnNaicsTwo(Session session, int code){
		Query<?> query2 = session.createQuery("from Company where NAICS like '"+code+"%'");
		List<Company> CompanyList = (List<Company>) query2.list();
		
		JSONArray jsonArray2 = new JSONArray();
		for(Company company : CompanyList)
        {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name","NAICS_NO : " + company.getNAICS());
			jsonObject.put("sector",company.getCOMNAM()+"   |   "+company.getPERMNO() + "   |   " + company.getTSYMBOL());
			jsonObject.put("children","");
			jsonArray2.put(jsonObject);
        }
		return jsonArray2;
	}
}

