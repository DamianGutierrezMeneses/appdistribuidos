package ec.edu.ups.interciclo.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.interciclo.business.RostroBusiness;
import ec.edu.ups.interciclo.dao.CamaraDAO;
import ec.edu.ups.interciclo.dao.UsuarioDAO;
import ec.edu.ups.interciclo.model.Camara;
import ec.edu.ups.interciclo.model.Rostro;
import ec.edu.ups.interciclo.model.Usuario;

@ManagedBean
public class RostroBean {

	@Inject
	private RostroBusiness aeBusiness; /// se conecta a objeto de negocio

	@Inject
	private FacesContext facesContext;

	@Inject
	UsuarioDAO usuDAO;

	@Inject
	CamaraDAO camDAO;

	private String cedulaUsu;
	private int idRostro;
	Rostro r;
	private String ruta;

	public String getCedulaUsu() {
		return cedulaUsu;
	}

	public void setCedulaUsu(String cedulaUsu) {
		this.cedulaUsu = cedulaUsu;
	}

	public int getIdRostro() {
		return idRostro;
	}

	public void setIdRostro(int idRostro) {
		this.idRostro = idRostro;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	private List<Usuario> usuariosl;
	private Rostro newRostro;

	private List<Rostro> rostros;

	private boolean editarp;

	private Usuario usuarios;

	public Usuario getUsuarios() {
		return usuarios;
	}

	public String registro() {
		System.out.println("Ingreso a REGISTROr");
		try {
			if (editarp) {
				System.out.println("Ingreso a editar");
				System.out.println("ESTA ES LA CEDULA QUE RECUPERO" + cedulaUsu);
				Usuario u = new Usuario();
				u.setCedula(cedulaUsu);
				// u = usuDAO.read(cedulaUsu);
				r.setId(idRostro);
				r.setRuta(ruta);
				r.setUsuarios(u);
				aeBusiness.actualizar(r);

			} else {

				Usuario u = new Usuario();
				// u = usuDAO.read(cedulaUsu);
				System.out.println("ESTA ES LA CEDULA QUE RECUPERO ¡¡¡¡¡¡¡ " + cedulaUsu);
				u.setCedula(cedulaUsu);
				r.setId(idRostro);
				r.setRuta(ruta);
				r.setUsuarios(u);
				System.out.println("usuario a guardar+*****" + u.getApellidos() + " " + u.getNombres());
				aeBusiness.save(r);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void init() { // para que no encuente nulo
		r = new Rostro();
		newRostro = new Rostro();
		// newUsuario.addTelefono(new Telefono());
		// ***********************************************************
		editarp = false;
		rostros = aeBusiness.getListadoRostro();
	}

	public Rostro getNewRostro() {
		return newRostro;
	}

	public void setNewRostro(Rostro newRostro) {
		this.newRostro = newRostro;
	}

	public boolean isEditarp() {
		return editarp;
	}

	public void setEditarp(boolean editarp) {
		this.editarp = editarp;
	}

	public List<Rostro> getRostros() {
		return rostros;
	}

	public void setEventos(List<Camara> camaras) {
		this.rostros = rostros;
	}

	public String guardar() {
		System.out.println("editando" + editarp);
		try {
			if (editarp)
				aeBusiness.actualizar(newRostro);
			else
				aeBusiness.save(newRostro);
			System.out.println("Guardado exitoso");
			return "listar-rostro?faces-redirect=true";// "lista-usuario?faces-redirect=true"
														// ******************************************************
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al guardar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}

		return null;
	}

	public String eliminar(int cod) {

		try {
			aeBusiness.eliminar(cod);
			System.out.println("Camara eliminado");
			return "listar-rostro?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}

		return null;
	}

	public String editar(Rostro aevento) {
		editarp = true;
		newRostro = aevento;
		return "crear-rostro"; //
	}

//	public String regresar() {
//		return "crear-rostro"; // "create-persona"
//	}

	public String regresarMp() {
		return "menu-principal"; // "create-persona"
	}
}
