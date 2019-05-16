package com.certiorem.microservices.constants;

public class DriverConstrants {

	public static final String DRIVER_SERVICE_CONTROLLER_HOST = "http://localhost:8091";

	public static final String DRIVER_CLASS_PARAM = "/driver";

	public static final String DRIVER_SEARCH_PARAM = "id";

	// DRIVER
	public static final String DRIVER_READ = DRIVER_CLASS_PARAM + "/read";
	public static final String DRIVER_READ_ALL = DRIVER_CLASS_PARAM + "/readAll";
	public static final String DRIVER_CREATE = DRIVER_CLASS_PARAM + "/create";
	public static final String DRIVER_UPDATE = DRIVER_CLASS_PARAM + "/update";
	public static final String DRIVER_DELETE = DRIVER_CLASS_PARAM + "/delete";

	// MICROSERVICE DRIVER
	public static final String DRIVER_MICROSERVICE_READ = "/readAllDrivers";
	public static final String DRIVER_MICROSERVICE_CREATE = "/createItem";
	public static final String DRIVER_MICROSERVICE_DELETE = "/deleteItem";
	public static final String DRIVER_MICROSERVICE_UPDATE = "/updateItem";

	public static final String DRIVER_SERVICE_GET_DRIVER = URLConstants.DDBB_SERVICE_CONTROLLER_HOST
			+ DRIVER_CLASS_PARAM + "?id={" + DRIVER_SEARCH_PARAM + "}";

	public static final String DRIVER_SERVICE_GET_DRIVER_GATEWAY = DRIVER_SERVICE_CONTROLLER_HOST + DRIVER_CLASS_PARAM
			+ "?id={" + DRIVER_SEARCH_PARAM + "}";

	// DDBB_MICROSERVICES_CONNECTOR_DRIVER
	public static final String DBB_CONNECTOR_CALL_DRIVER = URLConstants.DDBB_SERVICE_CONTROLLER_HOST
			+ DRIVER_CLASS_PARAM + DRIVER_MICROSERVICE_READ;

	public static final String DBB_CONNECTOR_CREATE_DRIVER = URLConstants.DDBB_SERVICE_CONTROLLER_HOST
			+ DRIVER_CLASS_PARAM + DRIVER_MICROSERVICE_CREATE;

	public static final String DBB_CONNECTOR_DELETE_DRIVER = URLConstants.DDBB_SERVICE_CONTROLLER_HOST
			+ DRIVER_CLASS_PARAM + DRIVER_MICROSERVICE_DELETE + "?id={" + DRIVER_SEARCH_PARAM + "}";

}
