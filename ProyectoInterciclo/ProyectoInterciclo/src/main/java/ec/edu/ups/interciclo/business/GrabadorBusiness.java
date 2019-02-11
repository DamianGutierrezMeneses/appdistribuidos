package ec.edu.ups.interciclo.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.interciclo.dao.GrabadorDAO;
import ec.edu.ups.interciclo.model.Grabador;
import ec.edu.ups.interciclo.model.ListaGrabador;
import ec.edu.ups.interciclo.model.ListarGrabador;

@Stateless
public class GrabadorBusiness {

	@Inject
	private GrabadorDAO dao;

//permite insertar un nuevo grabador
	public void save(Grabador grabador) throws Exception {

		Grabador aux = dao.read(grabador.getSerie());// devuelva a persona a partir del id)
		if (aux != null) {
			throw new Exception("Grabador ya registrado");
		} else
			dao.insert(grabador);
	}

	// permite listar grabadores
	public List<ListarGrabador> getListadoGrabador() {
		return dao.getGrabadores();
	}

	public Grabador leer(String serie) throws Exception {
		Grabador aux = dao.read(serie);
		if (aux == null) {
			throw new Exception("Grabador NO existe");
		} else
			return aux;
	}

	// metodo para eliminar
	public void eliminar(String serie) throws Exception {
		Grabador aux = dao.read(serie);
		if (aux == null) {
			throw new Exception("Grabador NO registrado");
		} else
			dao.remove(serie);
	}

	// METODO ACTUALIZAR
	public void actualizar(Grabador grabador) throws Exception {
		Grabador aux = dao.read(grabador.getSerie());
		if (aux == null) {
			throw new Exception("Grabador NO existe");
		} else
			dao.update(grabador);
	}

	// permite insertar un nuevo grabador por la cedula del cliente
	public List<ListaGrabador> grabadoresxCedula(String cedula) {// String param
		return dao.grabadoresCedula(cedula);
	}
}
