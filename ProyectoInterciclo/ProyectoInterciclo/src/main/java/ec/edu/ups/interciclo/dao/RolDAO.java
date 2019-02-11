package ec.edu.ups.interciclo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.interciclo.model.Rol;

@Stateless
public class RolDAO {

	@Inject
	private EntityManager em;

	public List<Rol> getRoles() {
		String jpql = "SELECT r FROM Rol r ";
		Query query = em.createQuery(jpql, Rol.class);
		List<Rol> lista = query.getResultList();
		return lista;
	}

	public Rol read(int codigo) {
		Rol aux = em.find(Rol.class, codigo);
		return aux;
	}

}
