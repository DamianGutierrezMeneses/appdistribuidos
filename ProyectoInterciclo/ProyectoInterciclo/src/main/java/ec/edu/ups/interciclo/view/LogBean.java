package ec.edu.ups.interciclo.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.interciclo.business.LogBusiness;
import ec.edu.ups.interciclo.model.ListaLogs;
import ec.edu.ups.interciclo.model.Log;

@ManagedBean
public class LogBean {

	@Inject
	private LogBusiness lBusiness;

	private Log newlog;

	public void init() {
		newlog = new Log();
	}

	public Log getNewlog() {
		return newlog;
	}

	public void setNewlog(Log newlog) {
		this.newlog = newlog;
	}

	public void save() throws Exception {
		lBusiness.save(newlog);
	}

	public List<ListaLogs> getListaLogs() {
		return lBusiness.getLogs();
	}

	public String regresar() {
		return "menu-principal";
	}

}
