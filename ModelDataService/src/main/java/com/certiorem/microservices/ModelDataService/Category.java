
package com.certiorem.microservices.ModelDataService;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "FUNDADO", nullable = false)
	private Integer fundado;

	@Column(name = "TIPO", nullable = false)
	private CategoryType tipo;
	
	@Column(name = "MAX_TEAMS", nullable = false)
	private Integer maxTeams;

	@ManyToMany
	private List<Team> equipos;
	
	@OneToOne(mappedBy = "category")
    private Competition competition;
	
	
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

	public CategoryType getTipo() {
		return tipo;
	}

	public void setTipo(CategoryType tipo) {
		this.tipo = tipo;
	}

	public List<Team> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Team> equipos) {
		this.equipos = equipos;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", nombre=" + nombre + ", fundado=" + fundado + ", tipo=" + tipo + ", equipos="
				+ equipos + "]";
	}

}
