package ec.edu.ups.interciclo.model;

import java.util.ArrayList;
import java.util.List;

public class ListaCamara {

	private String serieGra;
	private String nombreGra;
	private String camaras;
	private List<String> eventos;

	public String getSerieGra() {
		return serieGra;
	}

	public void setSerieGra(String serieGra) {
		this.serieGra = serieGra;
	}

	public String getNombreGra() {
		return nombreGra;
	}

	public void setNombreGra(String nombreGra) {
		this.nombreGra = nombreGra;
	}

	public List<String> getEventos() {
		return eventos;
	}

	public String getCamaras() {
		return camaras;
	}

	public void setCamaras(String camaras) {
		this.camaras = camaras;
	}

	public void setEventos(List<String> eventos) {
		this.eventos = eventos;
	}

	public void addEvento(String evento) {
		if (this.eventos == null)
			this.eventos = new ArrayList<>();
		this.eventos.add(evento);
	}

}
