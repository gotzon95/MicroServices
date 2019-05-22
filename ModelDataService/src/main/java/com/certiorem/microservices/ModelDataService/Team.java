package com.certiorem.microservices.ModelDataService;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "motorizador")
	private Marca motorizador;

	@Column(name = "RESPONSABLE", nullable = false)
	private String responsable;

	@ManyToMany(mappedBy = "equipos", cascade = CascadeType.ALL)
	private List<Driver> pilotos;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria")
	private Category categoria;

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

	public Marca getMotorizador() {
		return motorizador;
	}

	public void setMotorizador(Marca motorizador) {
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

	public Category getCategoria() {
		return categoria;
	}

	public void setCategoria(Category categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", nombre=" + nombre + ", fundado=" + fundado + ", victorias=" + victorias
				+ ", motorizador=" + motorizador + ", responsable=" + responsable + "]";
	}
}
