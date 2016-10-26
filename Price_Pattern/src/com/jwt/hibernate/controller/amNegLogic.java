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
import com.jwt.hibernate.dao.amNegDAO;



@Path("/amNegLogic")
public class amNegLogic {
	@Path("{graphData}")
	@GET
	@Produces("application/json")
	public Response generateAmnegJson(@PathParam("graphData") int permno){
		amNegDAO amNegObj = new amNegDAO();
		List<List<companydetails>> list = amNegObj.returnAmnegList(10078);
		
		JSONArray jsonArray = new JSONArray();
		
		int i;
		for(i=0; i<list.size(); i++){
			JSONArray inerJsonArray = new JSONArray();
		
			for(companydetails companydetails : list.get(i))
	        {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("PERMNO",companydetails.getPERMNO());
				jsonObject.put("Date",companydetails.getDate());
				jsonObject.put("PRC",companydetails.getPRC());
				jsonObject.put("Pseudo_PRC",companydetails.getPseudo_PRC());
				inerJsonArray.put(jsonObject);
				//jsonObject.put(companydetails.getPRC());	
	        }
			
			jsonArray.put(inerJsonArray);
		}
		
		return Response.status(200).entity(jsonArray.toString()).build();
	}
}
