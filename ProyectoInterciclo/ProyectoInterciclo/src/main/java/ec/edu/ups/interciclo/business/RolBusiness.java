package ec.edu.ups.interciclo.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.interciclo.dao.RolDAO;
import ec.edu.ups.interciclo.model.Rol;

@Stateless
public class RolBusiness {

	@Inject
	private RolDAO dao;

	// Obtiene una lista de roles
	public List<Rol> getListaRoles() {
		return dao.getRoles();
	}

	// Se obtiene un rol a través de su codigo
	public Rol read(int codigo) {
		return dao.read(codigo);
	}
}
