package ec.edu.ups.interciclo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.interciclo.model.ListaUsuario;
import ec.edu.ups.interciclo.model.ListaUsuarioRol;
import ec.edu.ups.interciclo.model.Rol;
import ec.edu.ups.interciclo.model.TempUsuLogin;
import ec.edu.ups.interciclo.model.Usuario;

@Stateless
public class UsuarioDAO {

//	@PersistenceContext (unitName = "usuapersi")
	@Inject
	private EntityManager em;

	// mant, lista, procedi
	public void insert(Usuario usuario) {
		em.persist(usuario);// para insert

	}

	public void update(Usuario usuario) {
		em.merge(usuario);
	}

	public void remove(String cedula) {
		em.remove(read(cedula));
	}

	public Usuario read(String cedula) {
		Usuario aux = em.find(Usuario.class, cedula);/// devuelve registro de la db que tieiene el id pero la entidad
		return aux;
	}

	public List<ListaUsuario> getUsuarios() {// String param
		String jpql = "SELECT u FROM Usuario u ";
		Query query = em.createQuery(jpql, Usuario.class);
		List<Usuario> lista = query.getResultList();
		List<ListaUsuario> list = new ArrayList<>();
		for (Usuario usuario : lista) {
			ListaUsuario lu = new ListaUsuario();
			lu.setCedula(usuario.getCedula());
			lu.setNombres(usuario.getNombres());
			lu.setApellidos(usuario.getApellidos());
			lu.setEmail(usuario.getEmail());
			lu.setCelular(usuario.getCelular());
			list.add(lu);
		}
		return list;
	}

	public List<ListaUsuarioRol> getUsuariosRol() {// String param
		String jpql = "SELECT u FROM Usuario u ";
		Query query = em.createQuery(jpql, Usuario.class);
		List<Usuario> lista = query.getResultList();
		List<Rol> listRol = new ArrayList<>();
		List<ListaUsuarioRol> list = new ArrayList<>();
		for (Usuario usuario : lista) {
			ListaUsuarioRol lu = new ListaUsuarioRol();
			lu.setCedula(usuario.getCedula());
			lu.setNombres(usuario.getNombres());
			lu.setApellidos(usuario.getApellidos());
			lu.setEmail(usuario.getEmail());
			lu.setCelular(usuario.getCelular());
			lu.setRol(usuario.getRoles());
			list.add(lu);
		}
		return list;
	}

	public Usuario login(String email, String contrasenia) {
		System.out.println("INGRESAR>>>>>" + email + ", " + contrasenia);
		String jpql = "SELECT u FROM Usuario u WHERE email LIKE ?1 AND contrasenia LIKE ?2";
		Query query = em.createQuery(jpql, Usuario.class);

		System.out.println(query);
		query.setParameter(1, email);
		query.setParameter(2, contrasenia);
		Usuario u = (Usuario) query.getSingleResult();
		System.out.println("usuario es  " + query.setParameter(1, email));
		// System.out.println("contrasenia es " + query.setParameter(1, contrasenia));
		return u;
	}

	public TempUsuLogin loginapp(String email, String contrasenia) {
		String jpql = "SELECT u FROM Usuario u WHERE email LIKE ?1 AND contrasenia LIKE ?2";
		Query query = em.createQuery(jpql, Usuario.class);
		query.setParameter(1, email);
		query.setParameter(2, contrasenia);
		Usuario newUsuario = (Usuario) query.getSingleResult();
		TempUsuLogin tempUsuLog = new TempUsuLogin();
		if (newUsuario != null) {
			tempUsuLog.setCedula(newUsuario.getCedula());
			tempUsuLog.setNombres(newUsuario.getNombres());
			tempUsuLog.setApellidos(newUsuario.getApellidos());
			tempUsuLog.setEmail(newUsuario.getEmail());
		}
		return tempUsuLog;
	}
}
