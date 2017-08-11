package com.vihari.egenchallenge;

public class Alerts 
{
	private String timeStamp;
	private String value;
	public static final String COLLECTION_NAME = Alerts.class.getSimpleName();
	
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
