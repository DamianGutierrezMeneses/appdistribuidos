package ec.edu.ups.interciclo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import javax.persistence.Query;

import ec.edu.ups.interciclo.model.Rostro;


@Stateless
public class RostroDAO {

	//@PersistenceContext
	@Inject
	private EntityManager em;

	//mant, lista, procedi
	public void insert  (Rostro aevento) {
		em.persist(aevento);//para insert
		
	}
	
	public void update (Rostro aevento) {
		em.merge(aevento);
	}
	
	public void remove (int cod) {
		em.remove(read(cod));
	}
	
	public Rostro read(int cod) {
		Rostro aux = em.find(Rostro.class, cod);///devuelve registro de la db que tieiene el id pero la entidad
		return aux;
	}
	
	public List<Rostro> getRostros(){//String param
		String jpql = "SELECT ae FROM Rostro ae ";
		Query query = em.createQuery(jpql, Rostro.class);
		List<Rostro> lista = query.getResultList();
		return lista;
	}
}


