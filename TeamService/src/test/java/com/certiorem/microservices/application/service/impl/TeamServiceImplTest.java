package com.certiorem.microservices.application.service.impl;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.certiorem.microservices.ModelDataService.Marca;
import com.certiorem.microservices.ModelDataService.Team;
import com.certiorem.microservices.application.service.TeamService;
import com.certiorem.microservices.constants.TeamConstrants;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceImplTest {

	@InjectMocks
	private TeamService teamService = new TeamServiceImpl();

	@Mock
	private RestTemplate restTemplate;

	private Team team;
	
	private Marca marca;

	@Before
	public void beforeTest() throws URISyntaxException {
		marca = new Marca();
		marca.setNombre("Honda");
		marca.setFundado(1977);
		marca.setResponsable("Nakayima");
		marca.setVictorias(58);
		
		team = new Team();
		team.setId(1);
		team.setNombre("Repsol Honda");
		team.setFundado(1998);
		team.setMotorizador(marca);
		team.setResponsable("Nakamoto");
		team.setVictorias(1324);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testReadTeam() {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", team.getId());
		Mockito.when(
				restTemplate.getForEntity(Mockito.any(String.class), Mockito.any(Class.class), Mockito.any(Map.class)))
				.thenReturn(new ResponseEntity<Team>(team, HttpStatus.ACCEPTED));
		Team entityTeam = teamService.readTeam(1);
		assertEquals(entityTeam, team);
	}

	@Test
	public void testReadTeams() {
		List<Team> teams = new ArrayList<Team>();
		teams.add(team);

		Mockito.when(restTemplate.exchange(TeamConstrants.DBB_CONNECTOR_CALL_TEAM, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Team>>() {
				})).thenReturn(new ResponseEntity<List<Team>>(teams, HttpStatus.ACCEPTED));

		List<Team> allTeams = teamService.readTeams();
		assertEquals(teams, allTeams);
	}

	@SuppressWarnings({ "unchecked" })
	@Test
	public void testCreateTeam() throws URISyntaxException {

		Mockito.when(restTemplate.postForEntity(Mockito.any(URI.class), Mockito.any(Object.class),
				Mockito.any(Class.class))).thenReturn(new ResponseEntity<Team>(team, HttpStatus.ACCEPTED));
		System.out.println(team);

		Team entityTeam = teamService.create(team);
		System.out.println(entityTeam);
		assertEquals(entityTeam, team);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDeleteTeam() {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		uriVariables.put("id", team.getId());
		Mockito.doNothing().when(restTemplate).delete(Mockito.any(String.class), Mockito.any(Map.class));
		teamService.delete(1);
	}

}
