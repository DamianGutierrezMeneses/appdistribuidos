package ec.edu.ups.interciclo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Grabador {

	@Id
	@NotNull
	@Size(max = 15)
	private String serie;;

	@NotNull
	@NotEmpty
	@Size(max = 50)
	private String nombre;

	@JoinColumn(name = "usuarioc", referencedColumnName = "cedula")
	@ManyToOne(optional = false)
	private Usuario usuarios;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "grabador")
	private List<Camara> listarCamara;

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

	public List<Camara> getListarCamara() {
		return listarCamara;
	}

	public void setListarCamara(List<Camara> listarCamara) {
		this.listarCamara = listarCamara;
	}

	public void agregarCamaras(Camara camara) {
		if (this.listarCamara == null)
			this.listarCamara = new ArrayList<>();
		this.listarCamara.add(camara);
	}

}
