package com.certiorem.microservices.constants;

public class TeamConstrants {

	public static final String TEAM_SERVICE_CONTROLLER_HOST = "http://localhost:8090";

	public static final String TEAM_CLASS_PARAM = "/team";

	public static final String TEAM_SEARCH_PARAM = "id";

	// TEAM
	public static final String TEAM_READ = TEAM_CLASS_PARAM + "/read";
	public static final String TEAM_READ_ALL = TEAM_CLASS_PARAM + "/readAll";
	public static final String TEAM_CREATE = TEAM_CLASS_PARAM + "/create";
	public static final String TEAM_UPDATE = TEAM_CLASS_PARAM + "/update";
	public static final String TEAM_DELETE = TEAM_CLASS_PARAM + "/delete";

	// MICROSERVICE TEAM
	public static final String TEAM_MICROSERVICE_READ = "/readAllTeams";
	public static final String TEAM_MICROSERVICE_CREATE = "/createItem";
	public static final String TEAM_MICROSERVICE_DELETE = "/deleteItem";
	public static final String TEAM_MICROSERVICE_UPDATE = "/updateItem";

	public static final String TEAM_SERVICE_GET_TEAM = TEAM_SERVICE_CONTROLLER_HOST + TEAM_CLASS_PARAM + "?id={"
			+ TEAM_SEARCH_PARAM + "}";

	public static final String TEAM_SERVICE_GET_TEAM_GATEWAY = TEAM_SERVICE_CONTROLLER_HOST + TEAM_CLASS_PARAM + "?id={"
			+ TEAM_SEARCH_PARAM + "}";

	// DDBB_MICROSERVICES_CONNECTOR_TEAM
	public static final String DBB_CONNECTOR_CALL_TEAM = URLConstants.DDBB_SERVICE_CONTROLLER_HOST + TEAM_CLASS_PARAM
			+ TEAM_MICROSERVICE_READ;

	public static final String DBB_CONNECTOR_CREATE_TEAM = URLConstants.DDBB_SERVICE_CONTROLLER_HOST + TEAM_CLASS_PARAM
			+ TEAM_MICROSERVICE_CREATE;

	public static final String DBB_CONNECTOR_DELETE_TEAM = URLConstants.DDBB_SERVICE_CONTROLLER_HOST + TEAM_CLASS_PARAM
			+ TEAM_MICROSERVICE_DELETE + "?id={" + TEAM_SEARCH_PARAM + "}";

}
