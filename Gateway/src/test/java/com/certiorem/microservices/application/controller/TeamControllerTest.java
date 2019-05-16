package com.certiorem.microservices.application.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.certiorem.microservices.ModelDataService.Team;
import com.certiorem.microservices.application.services.TeamService;
import com.certiorem.microservices.constants.TeamConstrants;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TeamController.class, secure = false)
public class TeamControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TeamService teamService;

	@Spy
	private List<Team> teams;

	private Team team;

	@Before
	public void beforeTest() throws URISyntaxException {
		team = new Team();
		team.setId(1);
		team.setNombre("Repsol Honda");
		team.setFundado(1998);
		team.setMotorizador("Honda");
		team.setResponsable("Nakamoto");
		team.setVictorias(1324);
	}

	@Test
	public void testReadTeam() throws Exception {
		Mockito.when(teamService.read(Mockito.anyInt())).thenReturn(team);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/team/read?id=1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\":1,\"fundado\":1998,\"motorizador\":\"Honda\",\"nombre\":\"Repsol Honda\",\"responsable\":\"Nakamoto\",\"victorias\":1324,\"pilotos\":null,\"categorias\":null}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testReadTeams() throws Exception {
		teams = new ArrayList<>();
		teams.add(team);
		Mockito.when(teamService.readAll()).thenReturn(teams);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TeamConstrants.TEAM_READ_ALL)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"id\":1,\"fundado\":1998,\"motorizador\":\"Honda\",\"nombre\":\"Repsol Honda\",\"responsable\":\"Nakamoto\",\"victorias\":1324,\"pilotos\":null,\"categorias\":null}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testCreateTeam() throws Exception {
		String expected = "{\"id\":1,\"nombre\":\"Repsol Honda\",\"fundado\":1998,\"victorias\":1324,\"motorizador\":\"Honda\",\"responsable\":\"Nakamoto\",\"pilotos\":null,\"categorias\":null}";
		Mockito.when(teamService.create(Mockito.any(Team.class))).thenReturn(team);
		ObjectMapper objectMapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post(TeamConstrants.TEAM_CREATE).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(team)))
				.andExpect(MockMvcResultMatchers.content().string(expected));
	}

	@Test
	public void testDeleteTeam() throws Exception {
		Mockito.doNothing().when(teamService).delete(Mockito.anyInt());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/team/delete?id=1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
