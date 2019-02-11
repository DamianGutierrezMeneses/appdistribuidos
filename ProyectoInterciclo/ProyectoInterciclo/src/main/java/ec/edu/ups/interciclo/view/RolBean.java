package ec.edu.ups.interciclo.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ec.edu.ups.interciclo.business.RolBusiness;
import ec.edu.ups.interciclo.model.Rol;

@ManagedBean
public class RolBean {

	@Inject
	private RolBusiness rBusiness;

	private List<Rol> roles;

	@PostConstruct
	public void init() {
		roles = rBusiness.getListaRoles();
	}

	public RolBusiness getrBusiness() {
		return rBusiness;
	}

	public void setrBusiness(RolBusiness rBusiness) {
		this.rBusiness = rBusiness;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

}
