package ec.edu.ups.interciclo.view;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import ec.edu.ups.interciclo.business.LogBusiness;
import ec.edu.ups.interciclo.business.UsuarioBusiness;
import ec.edu.ups.interciclo.model.Log;
import ec.edu.ups.interciclo.model.Rol;
import ec.edu.ups.interciclo.model.TempUsuLogin;
import ec.edu.ups.interciclo.model.Usuario;
import ec.edu.ups.interciclo.util.SessionUtils;

@ManagedBean
public class ControladorVista {

	private String email;
	private String cedula;
	private String contrasenia;
	private Log logb;

	@Inject
	private LogBusiness lBusiness;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	private Usuario newUsuario;
	private TempUsuLogin tempUsuLog;
	private Usuario usu;

	@Inject
	private UsuarioBusiness uBusiness;

	@Inject
	private FacesContext facesContext;

	@PostConstruct
	public void init() {
		newUsuario = new Usuario();
		tempUsuLog = new TempUsuLogin();
		logb = new Log();
		usu = new Usuario();

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}

	public TempUsuLogin getTempUsuLog() {
		return tempUsuLog;
	}

	public void setTempUsuLog(TempUsuLogin tempUsuLog) {
		this.tempUsuLog = tempUsuLog;
	}

	// Metodo que carga los datos en un objeto usuario segun la cedula

	public void loadDataUsuario() {

		if (cedula == null)
			return;
		try {

			usu = uBusiness.read(cedula);
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	// Metodo que carga los datos en un objeto usuario segun la cedula
	public Usuario loadDataUsu(String ced) {

		if (cedula == null)
			return null;
		try {

			usu = uBusiness.read(ced);
			return usu;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
		return usu;
	}

	// Permite loguearse a un usuario
	// Realiza la consulta a atrves de su email y contraseña retorna el usuario
	// Para esto debemos obtener el rol tambipen
	public String loginU() {
		Rol ro1 = new Rol();
		System.out.println("INGRESAR: " + email + ", " + contrasenia);
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("username", email);
		try {
			System.out.println("INGRESAR: " + email + ", " + contrasenia);
			if (uBusiness.login(email, contrasenia) != null) {
				System.out.println("INGRESAR: " + email + ", " + contrasenia);
				newUsuario = uBusiness.login(email, contrasenia);
				tempUsuLog.setCedula(newUsuario.getCedula());
				tempUsuLog.setNombres(newUsuario.getNombres());
				tempUsuLog.setApellidos(newUsuario.getApellidos());
				tempUsuLog.setEmail(newUsuario.getEmail());
				cedula = newUsuario.getCedula();

				ro1 = newUsuario.getRoles();
				if (ro1.getCodigoRol() != 1)
					return "ErrorAdmi";
				else
					System.out.println("Se logeo al sistema>>>>>>>>>>>>" + cedula);
				return "menu-principal?faces-redirect=true&cedula=" + cedula;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Error";
	}

	public String regresarCU() throws Exception {

		return "crear-usuario?faces-redirect=true&cedula=" + cedula;
	}

	public String regresar() throws Exception {

		return "crear-rostro?faces-redirect=true&cedula=" + cedula;
	}

	public String regresarLogin() throws Exception {
		return "login";
	}

	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public String logout1() {
		return "login";
	}

	public String irListarLog() {
		return "listarLog";
	}

//	public String regresarMP() {
//		return "menu-principal";
//	}

}
