package com.jwt.hibernate.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jwt.hibernate.bean.CompanyEventData;
import com.jwt.hibernate.dao.CompanyEventDAO;

@Path("/CompanyEventController")
public class CompanyEventController {
	@GET
	@Produces("application/json")
	public void genMaximaJson(){
		CompanyEventDAO companyEvent = new CompanyEventDAO();
		List<CompanyEventData> list =  companyEvent.repMaxima();
		//company.genJson(list, request, response);
		/*JSONArray jsonArray = new JSONArray();
		
		
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
		
		return Response.status(200).entity(jsonArray.toString()).build();*/
	}

}
