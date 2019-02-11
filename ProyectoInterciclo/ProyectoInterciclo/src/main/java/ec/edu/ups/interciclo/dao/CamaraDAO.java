package ec.edu.ups.interciclo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.interciclo.model.Camara;
import ec.edu.ups.interciclo.model.Evento;
import ec.edu.ups.interciclo.model.Grabador;
import ec.edu.ups.interciclo.model.ListaCamara;

@Stateless
public class CamaraDAO {

	// @PersistenceContext
	@Inject
	private EntityManager em;

	// mant, lista, procedi
	public void insert(Camara camara) {
		em.persist(camara);// para insert

	}

	public void update(Camara camara) {
		em.merge(camara);
	}

	public void remove(int cod) {
		em.remove(read(cod));
	}

	public Camara read(int cod) {
		Camara aux = em.find(Camara.class, cod);/// devuelve registro de la db que tieiene el id pero la entidad
		return aux;
	}

	// Realiza una consulta que permite sacar las camaras pertenecientes a un
	// grabador
	public List<ListaCamara> camarasSerial(String serie) {
		String jpql = "SELECT c FROM Camara c WHERE grabador LIKE ?1";
		Query query = em.createQuery(jpql, Camara.class);
		query.setParameter(1, serie);
		List<Camara> lista = query.getResultList();

		// Para sacar los datos del grabador

		String jpql1 = "SELECT g FROM Grabador g WHERE serie LIKE ?1";
		Query query1 = em.createQuery(jpql1, Grabador.class);
		query1.setParameter(1, serie);
		List<Grabador> listag = query1.getResultList();

		// Agrega a la clase temporal los atributos necesrios de la consulta
		List<ListaCamara> list = new ArrayList<>();
		for (Camara camara : lista) {
			ListaCamara lc = new ListaCamara();
			lc.setNombreGra(listag.get(0).getNombre());
			lc.setCamaras(camara.getNombre());

			lc.setSerieGra(serie);
			for (Evento e : camara.getListaEventos()) {
				lc.addEvento(e.getNombre() + ", " + "Usuario: " + e.getUsuarios().getNombres() + " "
						+ e.getUsuarios().getApellidos() + ", " + e.getFecha());
			}
			list.add(lc);

		}
		return list;
	}

//Metodo para buscar una camara segun su serie
	public List<ListaCamara> buscarCamara(String serie) {
		System.out.println("CON ESTE SERIAL BUSCa>>>>>>>>" + serie);
		String jpql = "SELECT c FROM Camara c WHERE grabador LIKE ?1";
		Query query = em.createQuery(jpql, Camara.class);
		query.setParameter(1, serie);
		List<Camara> lista = query.getResultList();

		// Agrega a la clase temporal los atributos necesrios de la consulta
		List<ListaCamara> list = new ArrayList<>();
		for (Camara camara : lista) {
			ListaCamara lc = new ListaCamara();
			System.out.println("esto sale LISTAR CAMRA POR GRABADOR" + camara.getNombre());
			lc.setCamaras(camara.getNombre());
			lc.setSerieGra(serie);
			list.add(lc);
		}
		return list;
	}

//Obtiene una lista de todas las camaras
	public List<Camara> getCamaras() {// String param
		String jpql = "SELECT e FROM Camara e ";
		Query query = em.createQuery(jpql, Camara.class);
		List<Camara> lista = query.getResultList();
		return lista;
	}
}
