package com.jwt.hibernate.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jwt.hibernate.bean.companydetails;
import com.jwt.hibernate.dao.CompanyDAO;

@Path("/CompanyPriceController")
public class CompanyPriceController {
	@GET
	@Produces("application/json")
	public Response generateJson(){
		CompanyDAO company = new CompanyDAO();
		List<companydetails>  list =  company.getDetails(36469);
		//company.genJson(list, request, response);
		JSONArray jsonArray = new JSONArray();
		
		
		for(companydetails companydetails : list)
        {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("PERMNO",companydetails.getPERMNO());
			jsonObject.put("Date",companydetails.getDate());
			jsonObject.put("PRC",companydetails.getPRC());
			jsonObject.put("Pseudo_PRC",companydetails.getPseudo_PRC());
			jsonArray.put(jsonObject);
			//jsonObject.put(companydetails.getPRC());
			
			
			
        }	
		
		return Response.status(200).entity(jsonArray.toString()).build();
	}
	
	@Path("{c}")
	@GET
	@Produces("application/json")
	public Response ctof_input(@PathParam("c") Double c){
		JSONObject jsonObject = new JSONObject();
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;
		jsonObject.put("F Value", fahrenheit); 
		jsonObject.put("C Value", celsius);
 
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(jsonObject.toString()).build();
		//to check this result please refer the following URL
		//http://localhost:8080/PricePattern/WStest/ctof
	}
	
}
