package ec.edu.ups.interciclo.model;

import java.util.ArrayList;
import java.util.List;

public class ListaGrabador {

	private String nombres;
	private String apellidos;
	private String serie;
	private String nombre;
	private List<String> camaras;

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

	public List<String> getCamaras() {
		return camaras;
	}

	public void setCamaras(List<String> camaras) {
		this.camaras = camaras;
	}

	public void addCamara(String camara) {
		if (this.camaras == null)
			this.camaras = new ArrayList<>();
		this.camaras.add(camara);
	}
}
