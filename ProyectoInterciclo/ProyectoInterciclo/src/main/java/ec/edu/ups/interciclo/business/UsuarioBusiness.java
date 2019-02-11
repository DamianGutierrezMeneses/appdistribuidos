package ec.edu.ups.interciclo.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.interciclo.dao.UsuarioDAO;
import ec.edu.ups.interciclo.model.ListaUsuario;
import ec.edu.ups.interciclo.model.ListaUsuarioRol;
import ec.edu.ups.interciclo.model.TempUsuLogin;
import ec.edu.ups.interciclo.model.Usuario;

@Stateless
public class UsuarioBusiness {

	// objeto de negocio//metods//enCamaras-Eventos//validaciones
	// una logica para todos
	// debe conectarse a acceso a datos --- > PersonaDAO

	@Inject
	private UsuarioDAO dao;

	// metodo para eliminar
	public void save(Usuario usuario) throws Exception {

		Usuario aux = dao.read(usuario.getCedula());// devuelva a persona a partir de la cedula)
		if (aux != null) {
			throw new Exception("Usuario ya registrado");
		} else
			dao.insert(usuario);
	}

	// Obtienen una lista de usuario
	public List<ListaUsuario> getListadoUsuario() {
		return dao.getUsuarios();
	}

	// Obtienen una lista de usuario y su rol
	public List<ListaUsuarioRol> getUsuariosRol() {// String param
		return dao.getUsuariosRol();
	}

	// metodo para eliminar
	public void eliminar(String cedula) throws Exception {
		Usuario aux = dao.read(cedula);
		if (aux == null) {
			throw new Exception("Usuario NO registrado");
		} else
			dao.remove(cedula);
	}

	// METODO ACTUALIZAR
	public void actualizar(Usuario usuario) throws Exception {
		Usuario aux = dao.read(usuario.getCedula());
		if (aux == null) {
			throw new Exception("Usuario NO existe");
		} else
			dao.update(usuario);
	}

	public Usuario read(String cedula) throws Exception {
		Usuario aux = dao.read(cedula);
		if (aux == null)
			throw new Exception("Usuario no existe");
		else
			return aux;
	}

	public Usuario login(String email, String contrasenia) {
		System.out.println("INGRESAR___>>>>>" + email + ", " + contrasenia);
		return dao.login(email, contrasenia);
	}

	public TempUsuLogin loginapp(String email, String contrasenia) {
		System.out.println("INGRESAR___>>>>>" + email + ", " + contrasenia);
		return dao.loginapp(email, contrasenia);
	}
}

// managed bean i