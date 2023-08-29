package mx.com.gm.peliculas.servicio;

public interface ICatalogoPeliculas {
	
	String NOMBRE_RECURSO = "Pelicula.txt";
	
	void agregarPelicula(String nombrePelicula);
	
	void listarPelicula();
	
	void buscarPelicula(String buscar);
	
	void iniciarCatalogoPeliculas();



}
