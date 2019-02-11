package ec.edu.ups.interciclo.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario {

	@Id
	@NotNull
	@NotEmpty
	@Size(max = 13)
	private String cedula;

	@Size(max = 50)
	@Pattern(regexp = "[^0-9]*", message = "Solo debe ingresar letras")
	@NotNull
	@NotEmpty
	private String nombres;

	@Size(max = 50)
	@Pattern(regexp = "[^0-9]*", message = "Solo debe ingresar letras")
	@NotNull
	@NotEmpty
	private String apellidos;

	@NotNull
	@NotEmpty
	private String contrasenia;

	@Size(max = 50)
	@NotNull
	@Email
	@NotEmpty
	private String email;

	@Size(max = 10)
	@NotNull
	@NotEmpty
	private String celular;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
	private Collection<Grabador> grabadorCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
	private Collection<Evento> eventoCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
	private Collection<Log> eventoLog;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
	private Collection<Rostro> rostroCollection;

	@JoinColumn(name = "rolc", referencedColumnName = "codigoRol")
	@ManyToOne(optional = false)
	private Rol roles;

	public Collection<Grabador> getGrabadorCollection() {
		return grabadorCollection;
	}

	public void setGrabadorCollection(Collection<Grabador> grabadorCollection) {
		this.grabadorCollection = grabadorCollection;
	}

	public Collection<Evento> getEventoCollection() {
		return eventoCollection;
	}

	public void setEventoCollection(Collection<Evento> eventoCollection) {
		this.eventoCollection = eventoCollection;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Collection<Log> getEventoLog() {
		return eventoLog;
	}

	public void setEventoLog(Collection<Log> eventoLog) {
		this.eventoLog = eventoLog;
	}

	public Collection<Rostro> getRostroCollection() {
		return rostroCollection;
	}

	public void setRostroCollection(Collection<Rostro> rostroCollection) {
		this.rostroCollection = rostroCollection;
	}

	public Rol getRoles() {
		return roles;
	}

	public void setRoles(Rol roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cedula != null ? cedula.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.cedula == null && other.cedula != null)
				|| (this.cedula != null && !this.cedula.equals(other.cedula))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ec.edu.ups.interciclo.model.Usuario[ usuario=" + cedula + " ]";
	}

}
