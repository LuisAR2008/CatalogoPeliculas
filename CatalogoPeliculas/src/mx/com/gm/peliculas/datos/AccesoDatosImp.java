package mx.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.EscrituraDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

public class AccesoDatosImp implements IAccesoDatos {

	@Override
	public boolean existe(String nombreRecurso) throws AccesoDatosEx {

		File archivo = new File(nombreRecurso);
		return archivo.exists();
	}

	@Override
	public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {

		File archivo = new File(nombreRecurso);
		List<Pelicula> peliculas = new ArrayList<>();

		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			String linea = null;
			linea = entrada.readLine();

			while (linea != null) {

				Pelicula pelicula = new Pelicula(linea);
				peliculas.add(pelicula);
				linea = entrada.readLine();
			}
			entrada.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
			throw new LecturaDatosEx("Exepción al listar peliculas: " + e.getMessage());

		} catch (IOException e) {

			e.printStackTrace();
			throw new LecturaDatosEx("Exepción al listar peliculas: " + e.getMessage());
		}

		return peliculas;
	}

	@Override
	public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {

		File archivo = new File(nombreRecurso);
		try {

			PrintWriter salida = new PrintWriter(new FileWriter(archivo, anexar));
			salida.println(pelicula.toString());
			salida.close();
			System.out.println("Se ha escrito informacion al archivo: " + pelicula);

		} catch (IOException e) {

			e.printStackTrace();
			throw new EscrituraDatosEx("Exepción al escribir peliculas: " + e.getMessage());
		}

	}

	@Override
	public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
		
		var archivo = new File(nombreRecurso);
		String resultado = null;
		
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			String linea = null;
			linea = entrada.readLine();
			int indice = 1;
			
			while (linea != null) {
				
				if(buscar != null && buscar.equalsIgnoreCase(linea)) {
					
					resultado = "Pelicula " +linea+ " encontrada en el indice: " +indice;
					break;
					
				}
				linea = entrada.readLine();
				indice++;
				
			}
			entrada.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			throw new LecturaDatosEx("Exepción al buscar pelicula: " + e.getMessage());
			
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new LecturaDatosEx("Exepción al buscar pelicula: " + e.getMessage());
		}

		return resultado;
	}
	

	@Override
	public void crear(String nombreRecurso) throws AccesoDatosEx {
		
		File archivo = new File(nombreRecurso);
		
		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo));
			salida.close();
			System.out.println("Se ha creado el archivo");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new AccesoDatosEx("Excepcion al crear archivo: " +e.getMessage());
		}
		
		
		

	}

	@Override
	public void borrar(String nombreRecurso) throws AccesoDatosEx {
		
		File archivo = new File(nombreRecurso);
		
		if (archivo.exists()) {
			archivo.delete();
			System.out.println("Se ha borrado el archivo");
		}

	}

}
