package com.certiorem.microservices.ModelDataService;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MARCA")
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MARCA_ID")
	private Integer id;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "FUNDADO", nullable = false)
	private Integer fundado;
	
	@Column(name = "VICTORIAS", nullable = false)
	private Integer victorias;
	
	@Column(name = "RESPONSABLE", nullable = false)
	private String responsable;
	
	@OneToMany(mappedBy="motorizador", cascade = CascadeType.ALL)
	private List<Team> equipos;

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

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public List<Team> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Team> equipos) {
		this.equipos = equipos;
	}

	@Override
	public String toString() {
		return "Marca [id=" + id + ", nombre=" + nombre + ", fundado=" + fundado + ", victorias=" + victorias
				+ ", responsable=" + responsable + ", equipos=" + equipos + "]";
	}
}
