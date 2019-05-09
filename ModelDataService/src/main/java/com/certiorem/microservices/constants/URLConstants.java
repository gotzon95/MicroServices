package com.certiorem.microservices.constants;

public class URLConstants {

	private static final String TEAM_SERVICE_HOST = "http://localhost:8090";
	
	public static final String TEAM_SERVIVE_GET_TEAM = TEAM_SERVICE_HOST + "/team?name={name}";
	
	public static final String DRIVER_SERVIVE_GET_DRIVER = TEAM_SERVICE_HOST + "/driver?name={name}";
	
	public static final String CATEGORY_SERVIVE_GET_CATEGORY = TEAM_SERVICE_HOST + "/category?id={id}";
}
