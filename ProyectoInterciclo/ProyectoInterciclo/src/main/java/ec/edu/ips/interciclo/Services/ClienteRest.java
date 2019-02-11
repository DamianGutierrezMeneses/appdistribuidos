package ec.edu.ips.interciclo.Services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ec.edu.ups.interciclo.business.CamaraBusiness;
import ec.edu.ups.interciclo.business.GrabadorBusiness;
import ec.edu.ups.interciclo.business.LogBusiness;
import ec.edu.ups.interciclo.business.RolBusiness;
import ec.edu.ups.interciclo.business.UsuarioBusiness;
import ec.edu.ups.interciclo.model.ListaCamara;
import ec.edu.ups.interciclo.model.ListaGrabador;
import ec.edu.ups.interciclo.model.ListaLogs;
import ec.edu.ups.interciclo.model.ListaUsuario;
import ec.edu.ups.interciclo.model.ListarGrabador;
import ec.edu.ups.interciclo.model.Log;
import ec.edu.ups.interciclo.model.Rol;
import ec.edu.ups.interciclo.model.TempUsuLogin;
import ec.edu.ups.interciclo.model.Usuario;
import ec.edu.ups.interciclo.model.UsuarioTempInsert;

@Path("/servicios")
public class ClienteRest {
	private Log logb;
	Rol ro;
	@Inject
	private LogBusiness lBusiness;

	@Inject
	private GrabadorBusiness gb;

	@Inject
	private CamaraBusiness cb;

	@Inject
	private UsuarioBusiness ub;

	@Inject
	private RolBusiness rb;

	// Servicio que permite el listado de los grabadores por el cliente

	@GET
	@Path("/buscarGrabadores")
	@Produces("application/json")
	public List<ListaGrabador> gatGrabadores(@QueryParam("cedula") String cedula) throws Exception {
		logb = new Log();
		logb.setAccion("Ingreso a ver cámaras");
		logb.setUsuarios(ub.read(cedula));
		Date fecha = new Date();
		System.out.println("A GUARDAR>>>>>>>>>: " + fecha);
		logb.setFechaLog(fecha + "");
		lBusiness.save(logb);
		return gb.grabadoresxCedula(cedula);
	}
// Servicio que permite la inserción de un nuevo usuario

	@POST
	@Path("/insertarUsuario")
	@Produces("application/json")
	@Consumes("application/json")
	public Response insertar(UsuarioTempInsert u) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		Usuario nueUsu = new Usuario();
		try {
			nueUsu.setApellidos(u.getApellidos());
			nueUsu.setCedula(u.getCedula());
			nueUsu.setCelular(u.getCelular());
			nueUsu.setContrasenia(u.getContrasenia());
			nueUsu.setEmail(u.getEmail());
			nueUsu.setNombres(u.getNombres());
			ro = new Rol();
			ro = rb.read(2);
			nueUsu.setRoles(ro);
			ub.save(nueUsu);
			data.put("codigo", "1");
			data.put("mensaje", "Ok");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {

			e.printStackTrace();
			data.put("codigo", "99");
			data.put("mensaje", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);
		}
		return builder.build();
	}

	// Servicio para mostrar los eventos por usuario, grabador y camaras
	@GET
	@Path("/buscarCamaraEventos")
	@Produces("application/json")
	public List<ListaCamara> gatGrabadoresCam(@QueryParam("serie") String serie, @QueryParam("cedula") String cedula)
			throws Exception {
		logb = new Log();
		logb.setAccion("Ingreso a ver Eventos");
		logb.setUsuarios(ub.read(cedula));
		Date fecha = new Date();
		logb.setFechaLog(fecha + "");
		lBusiness.save(logb);
		return cb.camarasxSerial(serie);
	}

	// Permite buscar la lista de cámara según el grabador al que pertenece
	@GET
	@Path("/buscarCamara")
	@Produces("application/json")
	public List<ListaCamara> gatCam(@QueryParam("serie") String serie) {
		return cb.buscarCamara(serie);
	}

	// Permite listar usuarios
	@GET
	@Path("/listarUsuario")
	@Produces("application/json")
	public List<ListaUsuario> getUsu(@QueryParam("cedula") String cedula) throws Exception {
		logb = new Log();
		logb.setAccion("Listado usuarios del sistema");
		logb.setUsuarios(ub.read(cedula));
		Date fecha = new Date();
		logb.setFechaLog(fecha + "");
		lBusiness.save(logb);

		return ub.getListadoUsuario();
	}

	// Obtiene una lista de grabadores
	@GET
	@Path("/listarGrabadores")
	@Produces("application/json")
	public List<ListarGrabador> getGrabador() {
		return gb.getListadoGrabador();
	}

	// Obtiene el listado de los reportes
	@GET
	@Path("/listadoLogsReportes")
	@Produces("application/json")
	public List<ListaLogs> getListaLogs() {
		return lBusiness.getLogs();
	}

	// Obtiene un usuario mediante su cédula
	@GET
	@Path("/leerUsuario")
	@Produces("application/json")
	public TempUsuLogin leer(@QueryParam("email") String email, @QueryParam("contrasenia") String contrasenia)
			throws Exception {
		TempUsuLogin p = null;
		try {
			p = ub.loginapp(email, contrasenia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logb = new Log();
		logb.setAccion("Se logueó a la APP");
		logb.setUsuarios(ub.read(p.getCedula()));
		Date fecha = new Date();
		logb.setFechaLog(fecha + "");
		lBusiness.save(logb);

		System.out.println("EL USUARIO ENCONTRADO ES!!" + p.getApellidos() + " ,,,, " + p.getNombres() + "");
		return p;
	}

}
