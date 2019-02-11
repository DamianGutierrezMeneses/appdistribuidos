package ec.edu.ups.interciclo.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.interciclo.dao.CamaraDAO;
import ec.edu.ups.interciclo.model.ListaCamara;

//Clase Busssines Cámara
@Stateless
public class CamaraBusiness {

	@Inject
	private CamaraDAO dao;

	public List<ListaCamara> camarasxSerial(String serie) {// String param
		return dao.camarasSerial(serie);
	}

	public List<ListaCamara> buscarCamara(String serie) {// String param
		return dao.buscarCamara(serie);
	}

}
