package com.jwt.hibernate.controller;

import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jwt.hibernate.bean.maxminDetails;

import com.jwt.hibernate.dao.maxminDAO;




@Path("/testNaics38703")
public class testNaics38703 {
	@Path("{naicsData}")
	@GET
	@Produces("application/json")
	public Response generateJson(@PathParam("naicsData") int permno){
		
		JSONArray jsonArrayobj = new JSONArray();
		JSONArray maxDates = new JSONArray();
		JSONArray minDates = new JSONArray();
		JSONArray jsonArray = new JSONArray();
		
		maxminDAO maxima = new maxminDAO();
		List<maxminDetails> maxset = maxima.getMaximaSet(permno); // get the maixma details 
		
		maxminDAO minima = new maxminDAO();
		List<maxminDetails> minset = minima.getMinimaSet(permno); // get the minima details 
		
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("PERMNO",permno);
		jsonObject.put("Type","Maxima");
		jsonObject.put("Dates",maxDates);
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("PERMNO",permno);
		jsonObject2.put("Type","Minima");
		jsonObject2.put("Dates",minDates);
		
		
		
		
		//OuterLOOP
		for(int k=0;k<maxset.size();k++){
			maxDates.put(maxset.get(k).getMETA_DATE());
			}	
		
		//OuterLOOP
		for(int k=0;k<minset.size();k++){
					minDates.put(minset.get(k).getMETA_DATE());
					}	
		jsonArray.put(jsonObject);
		jsonArray.put(jsonObject2);
		
		return Response.status(200).entity(jsonArray.toString()).build();
	
	}
	


	
}
	
