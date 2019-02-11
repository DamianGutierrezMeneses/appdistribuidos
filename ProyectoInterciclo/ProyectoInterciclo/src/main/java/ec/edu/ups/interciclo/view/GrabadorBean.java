package ec.edu.ups.interciclo.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.interciclo.business.GrabadorBusiness;
import ec.edu.ups.interciclo.model.Camara;
import ec.edu.ups.interciclo.model.Grabador;
import ec.edu.ups.interciclo.model.ListaGrabador;
import ec.edu.ups.interciclo.model.Usuario;

@ManagedBean
public class GrabadorBean {

	@Inject
	private GrabadorBusiness gBusiness; /// se conecta a objeto de negocio

	@Inject
	private FacesContext facesContext;

	private Grabador g;

	private String cedulaUsu;
	private String id;
	private String cedula;
	private String ceduIn;

	public String getCeduIn() {
		return ceduIn;
	}

	public void setCeduIn(String ceduIn) {
		this.ceduIn = ceduIn;
	}

	public String getNomGra() {
		return nomGra;
	}

	public void setNomGra(String nomGra) {
		this.nomGra = nomGra;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	private String nomGra;
	private String serial;

	private List<Grabador> grabadores;

	private boolean editarp;

	private Usuario usuariosc;

	private Grabador grabadorc;

	// Permite listar grabadores
	public List<Grabador> getGrabadores() {
		return grabadores;
	}

	// Permite guardar grabadores
	public void setGrabadores(List<Grabador> grabadores) {
		this.grabadores = grabadores;
	}

	public Usuario getUsuariosc() {
		return usuariosc;
	}

	public void setUsuariosc(Usuario usuariosc) {
		this.usuariosc = usuariosc;
	}

	public Grabador getGrabadorc() {
		return grabadorc;
	}

	public void setGrabadorc(Grabador grabadorc) {
		this.grabadorc = grabadorc;
	}

	public Usuario getUsuarios() {
		return usuariosc;
	}

	public void setUsuarios(Usuario usuariosc) {
		this.usuariosc = usuariosc;
	}

	public String addCamara() {
		g.agregarCamaras(new Camara());
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// Carga datos de usuario
	public void loadData() {
		System.out.println("load data " + id);
		if (id == null) ////////////////////////////////////////////
			return;
		try {
			g = gBusiness.leer(id);
			editarp = true;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	@PostConstruct
	public void init() { // para que no encuente nulo
		// newGrabador = new Grabador();
		g = new Grabador();
		editarp = false;
		g.agregarCamaras(new Camara());

	}

	public Grabador getG() {
		return g;
	}

	public void setG(Grabador g) {
		this.g = g;
	}

	public String registro() {
		System.out.println("Ingreso a REGISTROr");
		try {
			if (editarp) {
				System.out.println("Ingreso a editar");
				System.out.println("ESTA ES LA CEDULA QUE RECUPERO" + cedulaUsu);
				Usuario u = new Usuario();
				u.setCedula(cedulaUsu);
				g.setNombre(nomGra);
				g.setSerie(serial);
				System.out.println("Serie>>>> " + serial);
				g.setUsuarios(u);
				gBusiness.actualizar(g);
				return "menu-principal";

			} else {

				Usuario u = new Usuario();
				// u = usuDAO.read(cedulaUsu);
				System.out.println("ESTA ES LA CEDULA QUE RECUPERO ¡¡¡¡¡¡¡ " + cedulaUsu);
				u.setCedula(cedulaUsu);
				g.setNombre(nomGra);
				g.setSerie(serial);
				System.out.println("Serie>>>> " + serial);
				g.setUsuarios(u);
				gBusiness.save(g);
				return "menu-principal";
			}

		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
			e.printStackTrace();
		}
		return null;
	}

	public String getCedulaUsu() {
		return cedulaUsu;
	}

	public void setCedulaUsu(String cedulaUsu) {
		this.cedulaUsu = cedulaUsu;
	}

	public boolean isEditarp() {
		return editarp;
	}

	public void setEditarp(boolean editarp) {
		this.editarp = editarp;
	}

	public String eliminar(String cod) {

		try {
			gBusiness.eliminar(cod);
			System.out.println("Camara eliminado");
			return "listar-camara?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}

		return null;
	}

	public String grabadoresxCedula() {
		List<ListaGrabador> grabadores = gBusiness.grabadoresxCedula(cedula);
		for (ListaGrabador listaGrabador : grabadores) {
			System.out.println("nombre grabador ----> " + listaGrabador.getSerie() + " " + listaGrabador.getNombre());
		}
		return null;
	}

	public String irGrabador() {
		return "crear-grabador"; // "create-grabador
	}

	public String regresarMp() {
		return "menu-principal"; // regresa menu principal
	}

	public List<ListaGrabador> grabadoresxCedula1() {
		return gBusiness.grabadoresxCedula(ceduIn);

	}

	public void ObtenerGrab() {

	}

}
