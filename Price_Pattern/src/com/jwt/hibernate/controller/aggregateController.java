package com.jwt.hibernate.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jwt.hibernate.bean.Aggregate;
import com.jwt.hibernate.dao.aggregateDAO;

@Path("/aggregateController")
public class aggregateController {
	@Path("")
	@GET
	@Produces("application/json")
	public Response generateJson(){
		aggregateDAO data = new aggregateDAO();
		List<Aggregate>  list =  data.getDetails();
		//company.genJson(list, request, response);
		JSONArray jsonArray = new JSONArray();
		
		
		for(Aggregate Aggregate : list)
        {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("date",Aggregate.getDATE());
			jsonObject.put("peak_gain",Aggregate.getGAIN()/1000000);
			jsonObject.put("peak_loss",Aggregate.getLOSS()/1000000);
			jsonArray.put(jsonObject);
			//jsonObject.put(companydetails.getPRC());
        }	
		
		return Response.status(200).entity(jsonArray.toString()).build();	
	}
}
