package mx.com.gm.peliculas.presentacion;

import java.util.Scanner;

import mx.com.gm.peliculas.servicio.CatalogoPeliculasImp;
import mx.com.gm.peliculas.servicio.ICatalogoPeliculas;

public class CatalogoPeliculasPresentacion {

	public static void main(String[] args) {

		var opcion = -1;
		try (Scanner entrada = new Scanner(System.in)) {
			ICatalogoPeliculas catalogo = new CatalogoPeliculasImp(null);

			while (opcion != 0) {

				System.out.println("Elige una opcion: \n"
						+ "1. Iniciar catalogo Peliculas \n" 
						+ "2. Agregar Pelicula \n"
						+ "3. Listar Peliculas \n" 
						+ "4. Buscar Peliculas\n" 
						+ "0. Salir");
				opcion = Integer.parseInt(entrada.nextLine());

				switch (opcion) {
				case 1:
					catalogo.iniciarCatalogoPeliculas();
					break;
				case 2:
					System.out.println("Ingresa el nombre de la pelicula");
					var nombrePelicula = entrada.nextLine();
					catalogo.agregarPelicula(nombrePelicula);
					break;
				case 3:
					catalogo.listarPelicula();
					break;
				case 4:
					System.out.println("Ingresa el nombre de la pelicula a buscar");
					var buscar = entrada.nextLine();
					catalogo.buscarPelicula(buscar);
					break;
				case 0:
					System.out.println("Hasta Pronto!!");
					break;

				default:
					System.out.println("Opcion no reconocida, intente de nuevo");
					break;
				}

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

}
