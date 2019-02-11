package ec.edu.ups.interciclo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Log {

	@Id
	@GeneratedValue
	private int codigoLog;

	@NotNull
	@NotEmpty
	private String accion;

	@NotNull
	@NotEmpty
	private String fechaLog;

	@JoinColumn(name = "usuarioc", referencedColumnName = "cedula")
	@ManyToOne(optional = false)
	private Usuario usuarios;

	public int getCodigoLog() {
		return codigoLog;
	}

	public void setCodigoLog(int codigoLog) {
		this.codigoLog = codigoLog;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

	public String getFechaLog() {
		return fechaLog;
	}

	public void setFechaLog(String fechaLog) {
		this.fechaLog = fechaLog;
	}

}
