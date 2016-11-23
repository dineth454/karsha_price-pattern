package com.jwt.hibernate.bean;

public class Aggregate {
	
	private String DATE;
	private double GAIN;
	private double LOSS;
	
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public double getGAIN() {
		return GAIN;
	}
	public void setGAIN(double gAIN) {
		GAIN = gAIN;
	}
	public double getLOSS() {
		return LOSS;
	}
	public void setLOSS(double lOSS) {
		LOSS = lOSS;
	}

}
