package ec.edu.ups.interciclo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.interciclo.model.Camara;
import ec.edu.ups.interciclo.model.Grabador;
import ec.edu.ups.interciclo.model.ListaGrabador;
import ec.edu.ups.interciclo.model.ListarGrabador;

@Stateless
public class GrabadorDAO {

	@Inject
	private EntityManager em;

	// mant, lista, procedi
	public void insert(Grabador grabador) {
		em.persist(grabador);// para insert

	}

//Actualiza un grabador
	public void update(Grabador grabador) {
		em.merge(grabador);
	}

//Elimina un grabador
	public void remove(String serie) {
		em.remove(read(serie));
	}

//Leee un grabador
	public Grabador read(String serie) {
		Grabador aux = em.find(Grabador.class, serie);
		return aux;
	}

	// Permite obtener una lista de grabadores que pertenecen a un usuario
	// La consulta lo hace a través de su cvedula

	public List<ListaGrabador> grabadoresCedula(String cedula) {
		System.out.println("ENTRO A LISTAR GRABADOR X USUARI !!!e" + cedula);
		String jpql = "SELECT g FROM Grabador g WHERE g.usuarios.cedula LIKE ?1";

		Query query = em.createQuery(jpql, Grabador.class);
		query.setParameter(1, cedula);
		System.out.println("E***LA CONSULTA ES>>>" + query);
		List<Grabador> lista = query.getResultList();
		List<ListaGrabador> list = new ArrayList<>();
		for (Grabador grabador : lista) {
			ListaGrabador lg = new ListaGrabador();
			System.out.println("esto saleeeeeeeeeeeeeeeeeeeeeeeeeeee" + grabador.getListarCamara().get(0).getIdCam()
					+ ", " + grabador.getUsuarios().getCedula());
			// grabador.getListarCamara().get(0).getIdCam();
			lg.setSerie(grabador.getSerie());
			lg.setNombre(grabador.getNombre());
			lg.setNombres(grabador.getUsuarios().getNombres());
			lg.setApellidos(grabador.getUsuarios().getApellidos());
			for (Camara c : grabador.getListarCamara()) {
				lg.addCamara(c.getNombre());
			}
			list.add(lg);
		}
		return list;
	}

	// Permite obtener una lista de grabadores que pertenecen a un usuario
	// La consulta lo hace a través de su cvedula

	public List<ListarGrabador> getGrabadores() {// String param
		String jpql = "SELECT g FROM Grabador g ";
		Query query = em.createQuery(jpql, Grabador.class);
		List<Grabador> lista = query.getResultList();
		List<ListarGrabador> list = new ArrayList<>();
		for (Grabador grabador : lista) {
			ListarGrabador lg = new ListarGrabador();
			lg.setSerie(grabador.getSerie());
			lg.setNombre(grabador.getNombre());
			list.add(lg);
		}
		return list;
	}
}
