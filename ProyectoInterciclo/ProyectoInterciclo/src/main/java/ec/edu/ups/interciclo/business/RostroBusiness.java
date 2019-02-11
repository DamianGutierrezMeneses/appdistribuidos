package ec.edu.ups.interciclo.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.interciclo.dao.RostroDAO;
import ec.edu.ups.interciclo.model.Rostro;

@Stateless
public class RostroBusiness {

	// objeto de negocio//calculos//enFacturas//validaciones
	// una logica para todos
	// debe conectarse a acceso a datos --- > PersonaDAO

	@Inject
	private RostroDAO dao;

	// metodo para insertar
	public void save(Rostro aevento) throws Exception {

		Rostro aux = dao.read(aevento.getId());// devuelva a persona a partir del id)
		if (aux != null) {
			throw new Exception("Rostro ya registrado");
		} else
			dao.insert(aevento);
	}

	public List<Rostro> getListadoRostro() {
		return dao.getRostros();
	}

	// metodo para eliminar
	public void eliminar(int cod) throws Exception {
		Rostro aux = dao.read(cod);
		if (aux == null) {
			throw new Exception("Rostro NO registrado");
		} else
			dao.remove(cod);
	}

	// METODO ACTUALIZAR
	public void actualizar(Rostro aevento) throws Exception {
		Rostro aux = dao.read(aevento.getId());
		if (aux == null) {
			throw new Exception("Rostro NO existe");
		} else
			dao.update(aevento);
	}
}

//managed bean i 