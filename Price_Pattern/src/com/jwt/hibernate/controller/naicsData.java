package com.jwt.hibernate.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jwt.hibernate.bean.AmNeg;
import com.jwt.hibernate.bean.Company;
import com.jwt.hibernate.bean.companydetails;
import com.jwt.hibernate.bean.maxminDetails;
import com.jwt.hibernate.dao.CompanyDAO;
import com.jwt.hibernate.dao.amNegDAO;
import com.jwt.hibernate.dao.maxminDAO;




@Path("/naicsData")
public class naicsData {
	@Path("{naicsData}")
	@GET
	@Produces("application/json")
	public Response generateJson(@PathParam("naicsData") int naics) throws ParseException{
		
		CompanyDAO CD = new CompanyDAO();
		List<Company> permList = CD.getPermnoForNaics(naics); // unique permno list 
		
		
		JSONArray NaicsData = new JSONArray();
		
		for(int z=0;z<permList.size();z++){
			int permno=permList.get(z).getPERMNO();
			
			JSONArray jsonArrayobj = new JSONArray();
			JSONArray maxDates = new JSONArray();
			JSONArray minDates = new JSONArray();
			JSONArray amNegDates = new JSONArray();
			JSONArray jsonArray = new JSONArray();
			
			maxminDAO maxima = new maxminDAO();
			List<maxminDetails> maxset = maxima.getMaximaSet(permno); // get the maixma details 
			
			amNegDAO amNegObj = new amNegDAO();
			List<AmNeg> amNegList =amNegObj.getPatternDetails(permno);  // get the amNeg details 
			
			maxminDAO minima = new maxminDAO();
			List<maxminDetails> minset = minima.getMinimaSet(permno); // get the minima details 
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("PERMNO",permno);
			jsonObject.put("Type","Maxima");
			jsonObject.put("Dates",maxDates);
			
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put("PERMNO",permno);
			jsonObject1.put("Type","AmNEG");
			jsonObject1.put("Dates",amNegDates);
			
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("PERMNO",permno);
			jsonObject2.put("Type","Minima");
			jsonObject2.put("Dates",minDates);
			
			
			//OuterLOOP
			for(int k=0;k<maxset.size();k++){
				maxDates.put(maxset.get(k).getMETA_DATE());
				}	
			
			//OuterLOOP
					for(int k=0;k<amNegList.size();k++){
						amNegDates.put(middleDate(amNegList.get(k).getDateMax(),amNegList.get(k).getDateMin()));
						}	
			
			//OuterLOOP
			for(int k=0;k<minset.size();k++){
						minDates.put(minset.get(k).getMETA_DATE());
						}	
			jsonArray.put(jsonObject);
			jsonArray.put(jsonObject1);
			jsonArray.put(jsonObject2);
		
			NaicsData.put(jsonArray);
		/*end of bog loop */
			}
		
	
		return Response.status(200).entity(NaicsData.toString()).build();
	
	}
	
public static String  middleDate(String sD,String eD) throws ParseException{
		
		String startDateString = sD;
		String endDateString = eD;
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date startDate;
	    Date endDate;
	   
	        startDate = df.parse(startDateString);
	        endDate = df.parse(endDateString);
	        
	        long sum = startDate.getTime() + (endDate.getTime() - startDate.getTime())/2;
	           
	     Date sumDate = new Date(sum);
	   
	     
	     String middleDate = df.format(sumDate);
	       
		
		return middleDate;
		
		
		
	}
	


	
}
	
