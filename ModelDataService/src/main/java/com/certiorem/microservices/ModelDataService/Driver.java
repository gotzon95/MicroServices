package com.certiorem.microservices.ModelDataService;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PILOTO")
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "EDAD", nullable = false)
	private Integer edad;
	
	@Column(name = "VICTORIAS", nullable = false)
	private Integer victorias;
	
	@Column(name = "POLES", nullable = false)
	private Integer pole;
	
	@Column(name = "CAMPEONATO", nullable = false)
	private String campeonato;
	
	@ManyToMany
	@JoinColumn(name = "equipos")
	private List<Team> equipos;
	
	
	public Driver() {

	}


	public Driver(Integer id, String nombre, Integer edad, Integer victorias, Integer pole, String campeonato,
			List<Team> equipos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.victorias = victorias;
		this.pole = pole;
		this.campeonato = campeonato;
		this.equipos = equipos;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public Integer getVictorias() {
		return victorias;
	}


	public void setVictorias(Integer victorias) {
		this.victorias = victorias;
	}


	public Integer getPole() {
		return pole;
	}


	public void setPole(Integer pole) {
		this.pole = pole;
	}


	public String getCampeonato() {
		return campeonato;
	}


	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}


	public List<Team> getEquipos() {
		return equipos;
	}


	public void setEquipos(List<Team> equipos) {
		this.equipos = equipos;
	}


	@Override
	public String toString() {
		return "Driver [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", victorias=" + victorias + ", pole="
				+ pole + ", campeonato=" + campeonato + ", equipos=" + equipos + "]";
	}


	
	
	
}
