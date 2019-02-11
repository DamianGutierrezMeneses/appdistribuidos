package ec.edu.ups.interciclo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.interciclo.model.ListaLogs;
import ec.edu.ups.interciclo.model.Log;

@Stateless
public class LogDAO {

	@Inject
	private EntityManager em;

	public void insert(Log log) {
		em.persist(log);
	}

	public Log read(int cod) {
		Log aux = em.find(Log.class, cod);
		return aux;
	}

	public List<ListaLogs> getLogs() {
		String jpql = "SELECT l FROM Log l ";
		Query query = em.createQuery(jpql, Log.class);
		List<Log> lista = query.getResultList();
		List<ListaLogs> list = new ArrayList<>();
		for (Log log : lista) {
			ListaLogs ll = new ListaLogs();
			ll.setCodigoLog(log.getCodigoLog());
			ll.setAccion(log.getAccion());
			ll.setFechaLog(log.getFechaLog());
			ll.setUsuario(log.getUsuarios().getCedula());
			list.add(ll);
		}
		return list;
	}
}
