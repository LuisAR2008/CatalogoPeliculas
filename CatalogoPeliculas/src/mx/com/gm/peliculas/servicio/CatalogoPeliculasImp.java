package mx.com.gm.peliculas.servicio;

import mx.com.gm.peliculas.datos.AccesoDatosImp;
import mx.com.gm.peliculas.datos.IAccesoDatos;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

public class CatalogoPeliculasImp implements ICatalogoPeliculas{

	private final IAccesoDatos datos;
	
	
	
	public CatalogoPeliculasImp(IAccesoDatos datos) {
		
		this.datos = new AccesoDatosImp();
	}

	@Override
	public void agregarPelicula(String nombrePelicula) {
		
		Pelicula pelicula = new Pelicula(nombrePelicula);
		boolean anexar = false;
		try {
			anexar = datos.existe(NOMBRE_RECURSO);
			datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
			
		} catch (AccesoDatosEx e) {
			
			System.out.println("Error de Acceso de datos");
			e.printStackTrace(System.out);
		}
		
		
	}

	@Override
	public void listarPelicula() {
		
		try {
			var peliculas = this.datos.listar(NOMBRE_RECURSO);
			for (Pelicula pelicula : peliculas) {
				System.out.println("Pelicula = " +pelicula);
				
			}
		
		} catch (AccesoDatosEx e) {
			System.out.println("Error de acceso datos");
			e.printStackTrace(System.out);
		}
		
		
	}

	@Override
	public void buscarPelicula(String buscar) {
		
		String resultado = null;
		
		try {
			resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
		} catch (LecturaDatosEx e) {
			
			System.out.println("Error de Acceso datos");
			e.printStackTrace(System.out);
		}
		
		System.out.println("resultado = " + resultado);
		
	}

	@Override
	public void iniciarCatalogoPeliculas() {
		
		try {
			if(this.datos.existe(NOMBRE_RECURSO)) {
				
				datos.borrar(NOMBRE_RECURSO);
				datos.crear(NOMBRE_RECURSO);
			
			}
			else {
				datos.crear(NOMBRE_RECURSO);
			}
		} catch (AccesoDatosEx e) {
			System.out.println("Error al iniciar catalogo peliculas");
			e.printStackTrace(System.out);
		}
	}
	
	
	
	
	

}
