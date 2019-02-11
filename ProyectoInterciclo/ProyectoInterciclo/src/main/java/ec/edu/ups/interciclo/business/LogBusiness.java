package ec.edu.ups.interciclo.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.interciclo.dao.LogDAO;
import ec.edu.ups.interciclo.model.ListaLogs;
import ec.edu.ups.interciclo.model.Log;

@Stateless
public class LogBusiness {
	@Inject
	private LogDAO dao;

	// permite guardar un Log
	public void save(Log log) throws Exception {
		dao.insert(log);
	}

	// permite leer un Log
	public Log read(int cod) {
		return dao.read(cod);
	}

	// Obtiene una lista de logs
	public List<ListaLogs> getLogs() {
		return dao.getLogs();
	}

}
