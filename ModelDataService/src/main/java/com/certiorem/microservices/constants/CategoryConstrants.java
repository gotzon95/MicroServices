package com.certiorem.microservices.constants;

public class CategoryConstrants {

	public static final String CATEGORY_SERVICE_CONTROLLER_HOST = "http://localhost:8092";

	public static final String CATEGORY_CLASS_PARAM = "/category";

	public static final String CATEGORY_SEARCH_PARAM = "id";

	// CATEGORY
	public static final String CATEGORY_READ = CATEGORY_CLASS_PARAM + "/read";
	public static final String CATEGORY_READ_ALL = CATEGORY_CLASS_PARAM + "/readAll";
	public static final String CATEGORY_CREATE = CATEGORY_CLASS_PARAM + "/create";
	public static final String CATEGORY_UPDATE = CATEGORY_CLASS_PARAM + "/update";
	public static final String CATEGORY_DELETE = CATEGORY_CLASS_PARAM + "/delete";

	// MICROSERVICE CATEGORY
	public static final String CATEGORY_MICROSERVICE_READ = "/readAllCategories";
	public static final String CATEGORY_MICROSERVICE_CREATE = "/createItem";
	public static final String CATEGORY_MICROSERVICE_DELETE = "/deleteItem";
	public static final String CATEGORY_MICROSERVICE_UPDATE = "/updateItem";

	public static final String CATEGORY_SERVICE_GET_CATEGORY = URLConstants.DDBB_SERVICE_CONTROLLER_HOST + CATEGORY_CLASS_PARAM
			+ "?id={" + CATEGORY_SEARCH_PARAM + "}";

	public static final String CATEGORY_SERVICE_GET_CATEGORY_GATEWAY = CATEGORY_SERVICE_CONTROLLER_HOST
			+ CATEGORY_CLASS_PARAM + "?id={" + CATEGORY_SEARCH_PARAM + "}";

	// DBB_MICROSERVICES_CONNECTOR_CATEGORY
	public static final String DBB_CONNECTOR_CALL_CATEGORY = URLConstants.DDBB_SERVICE_CONTROLLER_HOST + CATEGORY_CLASS_PARAM
			+ CATEGORY_MICROSERVICE_READ;

	public static final String DBB_CONNECTOR_CREATE_CATEGORY = URLConstants.DDBB_SERVICE_CONTROLLER_HOST + CATEGORY_CLASS_PARAM
			+ CATEGORY_MICROSERVICE_CREATE;

	public static final String DBB_CONNECTOR_DELETE_CATEGORY = URLConstants.DDBB_SERVICE_CONTROLLER_HOST + CATEGORY_CLASS_PARAM
			+ CATEGORY_MICROSERVICE_DELETE + "?id={" + CATEGORY_SEARCH_PARAM + "}";

}
