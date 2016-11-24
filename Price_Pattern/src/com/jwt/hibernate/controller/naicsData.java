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
		
		JSONArray maxDates = new JSONArray();
		maxDates.put("Dates_Maxima");
		
		JSONArray maxValues = new JSONArray();
		maxValues.put("Maxima");
		
		JSONArray minDates = new JSONArray();
		minDates.put("Dates_Minima");
		
		JSONArray minValues = new JSONArray();
		minValues.put("Minima");
		
		JSONArray amNegDates = new JSONArray();
		amNegDates.put("Dates_AmNeg");
		
		JSONArray amNegValues = new JSONArray();
		amNegValues.put("AmNeg");
		
		JSONArray lines = new JSONArray();
		
		
		
		
		int minv=1;
		int amv=2;
		int mv=3;
		for(int z=0;z<permList.size();z++){
			int permno=permList.get(z).getPERMNO();
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put("value",(minv*(z+1)+2*z)-0.5);
			jsonObject1.put("text","Minima/"+permno);
			lines.put(jsonObject1);
			
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("value",(amv*(z+1)+z)-0.5);
			jsonObject2.put("text","AmNeg");
			lines.put(jsonObject2);
			
			JSONObject jsonObject3 = new JSONObject();
			jsonObject3.put("value",mv*(z+1)-0.5);
			jsonObject3.put("text","Maxima/"+permno);
			lines.put(jsonObject3);
			/*
			JSONArray jsonArrayobj = new JSONArray();
			JSONArray maxDates = new JSONArray();
			JSONArray minDates = new JSONArray();
			JSONArray amNegDates = new JSONArray();
			JSONArray jsonArray = new JSONArray();*/
			
			maxminDAO maxima = new maxminDAO();
			List<maxminDetails> maxset = maxima.getMaximaSet(permno); // get the maixma details 
			
			amNegDAO amNegObj = new amNegDAO();
			List<AmNeg> amNegList =amNegObj.getPatternDetails(permno);  // get the amNeg details 
			
			maxminDAO minima = new maxminDAO();
			List<maxminDetails> minset = minima.getMinimaSet(permno); // get the minima details 
			
			/*JSONObject jsonObject = new JSONObject();
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
			
			*/
			//OuterLOOP
			
			for(int k=0;k<maxset.size();k++){
				maxDates.put(maxset.get(k).getMETA_DATE());
				maxValues.put(mv*(z+1));
				
				}	
			

			//OuterLOOP
				
			for(int k=0;k<minset.size();k++){
						minDates.put(minset.get(k).getMETA_DATE());
						minValues.put(minv*(z+1)+2*z);
						}	
			
			for(int k=0;k<amNegList.size();k++){
				amNegDates.put(middleDate(amNegList.get(k).getDateMax(),amNegList.get(k).getDateMin()));
				amNegValues.put(amv*(z+1)+z);
				}	
			
		/*	//OuterLOOP
			amNegDates.put("Dates_AmNeg");
					for(int k=0;k<amNegList.size();k++){
						amNegDates.put(middleDate(amNegList.get(k).getDateMax(),amNegList.get(k).getDateMin()));
						}	
			
			jsonArray.put(jsonObject);
			
			jsonArray.put(jsonObject1);
			jsonArray.put(jsonObject2);
		*/
			
		/*end of bog loop */
			}
		
		NaicsData.put(maxDates);
		NaicsData.put(amNegDates);
		NaicsData.put(minDates);
		NaicsData.put(maxValues);
		NaicsData.put(amNegValues);
		NaicsData.put(minValues);
		NaicsData.put(lines);
		
		
		
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
	
