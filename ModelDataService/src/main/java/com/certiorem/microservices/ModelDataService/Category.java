
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
	private String tipo;

	@ManyToMany
	private List<Team> equipos;

	public Category() {

	}

	public Category(Integer id, String nombre, Integer fundado, String tipo, List<Team> equipos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fundado = fundado;
		this.tipo = tipo;
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

	public Integer getFundado() {
		return fundado;
	}

	public void setFundado(Integer fundado) {
		this.fundado = fundado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Team> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Team> equipos) {
		this.equipos = equipos;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", nombre=" + nombre + ", fundado=" + fundado + ", tipo=" + tipo + ", equipos="
				+ equipos + "]";
	}

}
