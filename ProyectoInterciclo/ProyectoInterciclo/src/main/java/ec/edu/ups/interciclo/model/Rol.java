package ec.edu.ups.interciclo.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Rol {

	@Id
	@GeneratedValue
	private int codigoRol;
	@NotNull
	@NotEmpty
	private String nombreRol;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
	private Collection<Usuario> usuarioCollection;

	public int getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(int codigoRol) {
		this.codigoRol = codigoRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public Collection<Usuario> getUsuarioCollection() {
		return usuarioCollection;
	}

	public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
		this.usuarioCollection = usuarioCollection;
	}

}
