package ec.edu.ups.interciclo.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.interciclo.business.UsuarioBusiness;
import ec.edu.ups.interciclo.model.ListaUsuario;
import ec.edu.ups.interciclo.model.ListaUsuarioRol;
import ec.edu.ups.interciclo.model.Rol;
import ec.edu.ups.interciclo.model.TempUsuLogin;
import ec.edu.ups.interciclo.model.Usuario;

@RequestScoped
@ManagedBean
public class UsuarioBean {

	@Inject
	private UsuarioBusiness uBusiness; /// se conecta a objeto de negocio

	@Inject
	private TempUsuLogin temUslog;

	@Inject
	private FacesContext facesContext;

	private Usuario newUsuario;
	private List<ListaUsuario> usuarios;
	private boolean editarp;
	private String selecRol;
	private Rol ro;
	private int rolIn;

	@PostConstruct
	public void init() { // para que no encuente nulo
		newUsuario = new Usuario();
		// newUsuario.addCamara(new Camara());
		// newUsuario.addRostro(new Rostro());
		editarp = false;
		usuarios = uBusiness.getListadoUsuario();
	}

	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}

//	public List<Usuario> getUsuarios() {
//		System.out.println(
//				"**********************************************************************************************************"
//						+ usuarios);
//		return usuarios;
//	}

	public Rol getRo() {
		return ro;
	}

	public void setRo(Rol ro) {
		this.ro = ro;
	}

//	public void setUsuarios(List<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}

	public List<ListaUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<ListaUsuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isEditarp() {
		return editarp;
	}

	public void setEditarp(boolean editarp) {
		this.editarp = editarp;
	}

	public String getSelecRol() {
		return selecRol;
	}

	public void setSelecRol(String selecRol) {
		this.selecRol = selecRol;
	}

	public String guardar() {
		System.out.println("editando" + editarp);
		try {
			if (editarp) {
				ro = new Rol();
				rolIn = Integer.parseInt(selecRol);
				ro.setCodigoRol(rolIn);
				newUsuario.setRoles(ro);
				uBusiness.actualizar(newUsuario);
			} else
				ro = new Rol();
			rolIn = Integer.parseInt(selecRol);
			ro.setCodigoRol(rolIn);
			newUsuario.setRoles(ro);
			uBusiness.save(newUsuario);

			System.out.println("Guardado exitoso");
			return "login";
		} catch (Exception e) {
			System.out.println("Error al guardar");
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}

		return null;
	}

	public String guardarLogin() {
		System.out.println("editando>>>>>>>>>>>>>>>");
		try {

			ro = new Rol();
			rolIn = 2;
			ro.setCodigoRol(rolIn);
			newUsuario.setRoles(ro);
			uBusiness.save(newUsuario);
			System.out.println("Guardado exitoso");
			// return "login" + newUsuario.getCedula();
			return "login";

		} catch (Exception e) {
			System.out.println("Error al guardar");
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}

		return null;
	}

	public String eliminar(String cedula) {

		try {
			uBusiness.eliminar(cedula);
			System.out.println("Usuario eliminado");
			return "listar-usuario?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}

		return null;
	}

	public List<ListaUsuario> getListadoUsuario() {
		return uBusiness.getListadoUsuario();
	}

	public List<ListaUsuarioRol> getUsuariosRol() {// String param
		return uBusiness.getUsuariosRol();
	}

	public String CastigUsuario(ListaUsuarioRol lur) {
		Usuario uc = new Usuario();
		uc.setCedula(lur.getCedula());
		uc.setNombres(lur.getNombres());
		uc.setApellidos(lur.getApellidos());
		uc.setEmail(lur.getEmail());
		uc.setCelular(lur.getCelular());
		uc.setRoles(lur.getRol());
		return editar(uc);
	}

	public String editar(Usuario usuario) {
		editarp = true;
		newUsuario = usuario;
		return "crear-usuario";
	}

	public String regresarPM() {
		return "crear-usuario-login";
	}

//	public String regresarCU() {
//		System.out.println("este es el usuario en sesion ---------- ++++++++++ " + temUslog.getApellidos());
//		return "crear-usuario";
//	}

	public String regresarMp() {
		return "menu-principal";
	}

//	public String regresarLogin() {
//		return "login";
//	}

}
