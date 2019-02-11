package ec.edu.ups.interciclo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Evento {

	@Id
	@GeneratedValue
	private int codigoEvento;

	@Size(max = 100)
	@NotNull
	@NotEmpty
	private String nombre;

	@NotNull
	@NotEmpty
	private Date fecha;

	@JoinColumn(name = "usuarioc", referencedColumnName = "cedula")
	@ManyToOne(optional = false)
	private Usuario usuarios;

	@JoinColumn(name = "camarac", referencedColumnName = "idCam")
	@ManyToOne(optional = false)
	private Camara camaras;

	public int getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(int codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

	public Camara getCamaras() {
		return camaras;
	}

	public void setCamaras(Camara camaras) {
		this.camaras = camaras;
	}

}
