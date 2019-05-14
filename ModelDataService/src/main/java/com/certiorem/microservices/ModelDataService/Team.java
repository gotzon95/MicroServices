package com.certiorem.microservices.ModelDataService;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EQUIPO")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEAM_ID")
	private Integer id;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "FUNDADO", nullable = false)
	private Integer fundado;
	
	@Column(name = "VICTORIAS", nullable = false)
	private Integer victorias;
	
	@Column(name = "MOTORIZADOR", nullable = false)
	private String motorizador;
	
	@Column(name = "RESPONSABLE", nullable = false)
	private String responsable;
	
	@ManyToMany(mappedBy="equipos")
	private List<Driver> pilotos;
	
	@ManyToMany(mappedBy="equipos")
	private List<Category> categorias;
	
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


	public Integer getFundado() {
		return fundado;
	}


	public void setFundado(Integer fundado) {
		this.fundado = fundado;
	}


	public Integer getVictorias() {
		return victorias;
	}


	public void setVictorias(Integer victorias) {
		this.victorias = victorias;
	}


	public String getMotorizador() {
		return motorizador;
	}


	public void setMotorizador(String motorizador) {
		this.motorizador = motorizador;
	}


	public String getResponsable() {
		return responsable;
	}


	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public List<Driver> getPilotos() {
		return pilotos;
	}


	public void setPilotos(List<Driver> pilotos) {
		this.pilotos = pilotos;
	}


	public List<Category> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<Category> categorias) {
		this.categorias = categorias;
	}


	@Override
	public String toString() {
		return "Team [id=" + id + ", nombre=" + nombre + ", fundado=" + fundado + ", victorias=" + victorias
				+ ", motorizador=" + motorizador + ", responsable=" + responsable + ", pilotos=" + pilotos + ", categorias=" + categorias + "]";
	}


	
	
	
	
}
