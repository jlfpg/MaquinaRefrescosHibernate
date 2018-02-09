package accesoDatos;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import auxiliares.ApiRequests;
import logicaRefrescos.Deposito;
import logicaRefrescos.Dispensador;

public class AccesoJSON implements I_Acceso_Datos {
	ApiRequests encargadoPeticiones;
	private String SERVER_PATH, GET_DEPO, GET_DISPEN, SET_DISPENSADOR, SET_DEPOSITO;

	public AccesoJSON() {

		encargadoPeticiones = new ApiRequests();

		SERVER_PATH = "http://localhost/jorge/refrescos/";
		GET_DEPO = "leerDepositos.php";
		GET_DISPEN = "leerDispensadores.php";
	}

	@Override
	public HashMap<Integer, Deposito> obtenerDepositos() {

		HashMap<Integer, Deposito> depositosCreados = new HashMap<Integer, Deposito>();

		try {

			String url = SERVER_PATH + GET_DEPO; // Sacadas de configuracion

			// System.out.println("La url a la que lanzamos la peticiï¿½n es " + url); //
			// Traza para pruebas

			String response = encargadoPeticiones.getRequest(url);

			// System.out.println(response); // Traza para pruebas

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algï¿½n error de parseo (json
										// incorrecto porque hay algï¿½n caracter
										// raro, etc.) la respuesta serï¿½ null
				System.out.println("El json recibido no es correcto. Finaliza la ejecuciï¿½n");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay algï¿½n problema
				String estado = (String) respuesta.get("estado");
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) {
					JSONArray array = (JSONArray) respuesta.get("instalaciones");

					if (array.size() > 0) {

						// Declaramos variables
						Deposito nuevoDepo;
						int id, valor, cantidad;
						String nombreMoneda;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);
							nombreMoneda = row.get("nombre").toString();
							valor = Integer.parseInt(row.get("valor").toString());
							cantidad = Integer.parseInt(row.get("cantidad").toString());

							nuevoDepo = new Deposito(nombreMoneda, valor, cantidad);

							depositosCreados.put(valor, nuevoDepo);
						}

						// System.out.println("Acceso JSON Remoto - Leidos datos correctamente y
						// generado hashmap");
						// System.out.println();

					} else { // El array de jugadores estï¿½ vacï¿½o
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algï¿½n error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}

		return depositosCreados;
	}

	@Override
	public HashMap<String, Dispensador> obtenerDispensadores() {
		HashMap<String, Dispensador> dispensadoresCreado = new HashMap<String, Dispensador>();
		try {

			String url = SERVER_PATH + GET_DISPEN; // Sacadas de configuracion

			//System.out.println("La url a la que lanzamos la peticiï¿½n es " + url); //
			// Traza para pruebas

			String response = encargadoPeticiones.getRequest(url);

			// System.out.println(response); // Traza para pruebas

			// Parseamos la respuesta y la convertimos en un JSONObject
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());

			if (respuesta == null) { // Si hay algï¿½n error de parseo (json
										// incorrecto porque hay algï¿½n caracter
										// raro, etc.) la respuesta serï¿½ null
				System.out.println("El json recibido no es correcto. Finaliza la ejecuciï¿½n");
				System.exit(-1);
			} else { // El JSON recibido es correcto
				// Sera "ok" si todo ha ido bien o "error" si hay algï¿½n problema
				String estado = (String) respuesta.get("estado");
				// Si ok, obtenemos array de jugadores para recorrer y generar hashmap
				if (estado.equals("ok")) {
					JSONArray array = (JSONArray) respuesta.get("instalaciones");

					if (array.size() > 0) {

						// Declaramos variables
						Dispensador nuevoDis;
						int cantidad, precio;
						String clave, nombreProducto;

						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);

							clave = row.get("clave").toString();
							nombreProducto = row.get("nombre").toString();
							precio = Integer.parseInt(row.get("precio").toString());
							cantidad = Integer.parseInt(row.get("cantidad").toString());

							nuevoDis = new Dispensador(clave, nombreProducto, precio, cantidad);

							dispensadoresCreado.put(clave, nuevoDis);
						}

						// System.out.println("Acceso JSON Remoto - Leidos datos correctamente y
						// generado hashmap");
						// System.out.println();

					} else { // El array de jugadores estï¿½ vacï¿½o
						System.out.println("Acceso JSON Remoto - No hay datos que tratar");
						System.out.println();
					}

				} else { // Hemos recibido el json pero en el estado se nos
							// indica que ha habido algï¿½n error

					System.out.println("Ha ocurrido un error en la busqueda de datos");
					System.out.println("Error: " + (String) respuesta.get("error"));
					System.out.println("Consulta: " + (String) respuesta.get("query"));

					System.exit(-1);

				}
			}

		} catch (Exception e) {
			System.out.println("Ha ocurrido un error en la busqueda de datos");

			e.printStackTrace();

			System.exit(-1);
		}

		return dispensadoresCreado;
	}

	@Override
	public boolean guardarDepositos(HashMap<Integer, Deposito> depositos) {
		SET_DEPOSITO = "escribirDepositos.php";
		
		return true;

	}

	@Override
	public boolean guardarDispensadores(HashMap<String, Dispensador> dispensadores) {

		SET_DISPENSADOR = "escribirDispensadores.php";

		return true;
	}

}