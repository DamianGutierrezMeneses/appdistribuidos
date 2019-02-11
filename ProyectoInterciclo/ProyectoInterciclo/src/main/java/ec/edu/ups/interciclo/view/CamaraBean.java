package ec.edu.ups.interciclo.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.interciclo.business.CamaraBusiness;
import ec.edu.ups.interciclo.model.ListaCamara;

@ManagedBean
public class CamaraBean {

	@Inject
	private CamaraBusiness cBusiness;

	@Inject
	private FacesContext facesContext;

	private String serie;

//Obtiene el listado de camara por el serial de su grabador
	public String camarasxSerial() {
		List<ListaCamara> grabadores = cBusiness.camarasxSerial(serie);
		for (ListaCamara listaCamara : grabadores) {
		}
		return null;
	}

}
