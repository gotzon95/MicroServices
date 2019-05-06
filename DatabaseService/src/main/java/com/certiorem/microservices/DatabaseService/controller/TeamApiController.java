package com.websystique.springboot.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springboot.model.Company;
import com.websystique.springboot.service.CompanyService;
import com.websystique.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class CompanyApiController {

	public static final Logger logger = LoggerFactory.getLogger(CompanyApiController.class);

	@Autowired
	CompanyService companyService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve Single Company In Login------------------------------------------

	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<?> getCompanyLogin(@RequestBody Company company, UriComponentsBuilder ucBuilder) {
		logger.info("Fetching Company with name {}", company.getName());
		Company companyReaded = companyService.findByName(company.getName());
		if (companyReaded == null || !company.getPassword().equals(companyReaded.getPassword())) {
			logger.error("Company with name {} not found.", company.getName());
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Company with name " + company.getName() 
					+ " not found"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Company>(companyReaded, HttpStatus.OK);
		}
	}
		
	// -------------------Create a Company-------------------------------------------

	@RequestMapping(value = "/register/", method = RequestMethod.POST)
				public ResponseEntity<?> createCompany(@RequestBody Company company, UriComponentsBuilder ucBuilder) {
					logger.info("Creating Company : {}", company);

					if (companyService.isCompanyExist(company)) {
						logger.error("Unable to create. A Company with name {} already exist", company.getName());
						return new ResponseEntity(new CustomErrorType("Unable to create. A Category with name " + 
								company.getName() + " already exist."),HttpStatus.CONFLICT);
					}
					companyService.saveCompany(company);

					HttpHeaders headers = new HttpHeaders();
					headers.setLocation(ucBuilder.path("/api/company/{id}").buildAndExpand(company.getId()).toUri());
					return new ResponseEntity<String>(headers, HttpStatus.CREATED);
				}	

	
	// ------------------- Update a Company ------------------------------------------------

		@RequestMapping(value = "/register/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateCompany(@PathVariable("id") long id, @RequestBody Company company) {
			logger.info("Updating Company with id {}", id);

			Company currentCompany = companyService.findById(id);

			if (currentCompany == null) {
				logger.error("Unable to update. Company with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("Unable to upate. Company with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}else {
				companyService.updateCompany(company);
				return new ResponseEntity<Company>(company, HttpStatus.OK);
			}

		}
	
	// ------------------- Delete a Company-----------------------------------------

		@RequestMapping(value = "/register/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteCompany(@PathVariable("id") long id) {
			logger.info("Fetching & Deleting Company with id {}", id);

			Company company = companyService.findById(id);
			if (company == null) {
				logger.error("Unable to delete. Company with id {} not found.", id);
				return new ResponseEntity(new CustomErrorType("Unable to delete. Company with id " + id + " not found."),
						HttpStatus.NOT_FOUND);
			}
			companyService.deleteCompanyById(id);
			return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
		}		
	// ------------------- Delete All Companies-----------------------------

			@RequestMapping(value = "/register/", method = RequestMethod.DELETE)
			public ResponseEntity<Company> deleteAllCompanies() {
				logger.info("Deleting All Companies");

				companyService.deleteAllCompanies();
				return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
			}		

}