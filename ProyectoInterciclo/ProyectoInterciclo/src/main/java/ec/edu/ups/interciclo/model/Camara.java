package ec.edu.ups.interciclo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Camara implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@NotNull
	private int idCam;

	@Size(max = 50)
	@NotNull
	@NotEmpty
	private String nombre;
//	@JoinColumn(name = "grabadorc", referencedColumnName = "serie")
//	@ManyToOne(optional = false)
//	private Grabador grabadores;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "camaras")
	private List<Evento> ListaEventos;

//	public Grabador getGrabadores() {
//		return grabadores;
//	}
//
//	public void setGrabadores(Grabador grabadores) {
//		this.grabadores = grabadores;
//	}

	public String getNombre() {
		return nombre;
	}

	public List<Evento> getListaEventos() {
		return ListaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		ListaEventos = listaEventos;
	}

	public int getIdCam() {
		return idCam;
	}

	public void setIdCam(int idCam) {
		this.idCam = idCam;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// @JoinColumn(name="usuarioc")
	// private List<Usuario> usuarios;
	/*
	 * public List<Usuario> getUsuarios() { return usuarios; } public void
	 * setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
	 */

}
