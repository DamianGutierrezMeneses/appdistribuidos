package ec.edu.ups.interciclo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Rostro implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	@NotEmpty
	private String ruta;

	@JoinColumn(name = "target_cedula", referencedColumnName = "cedula")
	@ManyToOne(optional = false)
	private Usuario usuarios;

	public Usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/*
	 * public List<Usuario> getUsuarios() { return usuarios; } public void
	 * setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
	 */

}
