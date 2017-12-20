package accesoDatos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;

import logicaRefrescos.Deposito;
import logicaRefrescos.Dispensador;
import logicaRefrescos.Deposito;
import logicaRefrescos.Dispensador;
import auxiliares.HibernateUtil;

public class AccesoHibernate implements I_Acceso_Datos {

	Session session;

	public AccesoHibernate() {

		HibernateUtil util = new HibernateUtil();

		session = util.getSessionFactory().openSession();

	}

	@Override
	public HashMap<Integer, Deposito> obtenerDepositos() {
		// TODO Auto-generated method stub
		int clave;
		HashMap<Integer, Deposito> depositosCreados = new HashMap<Integer, Deposito>();
		Query q = session.createQuery("select j from Deposito j");
		List results = q.list();

		Iterator equiposIterator = results.iterator();

		while (equiposIterator.hasNext()) {
			Deposito deposito = (Deposito) equiposIterator.next();

			// deposito= new
			// Deposito(deposito.getNombreMoneda(),deposito.getValor(),deposito.getCantidad());
			// clave=deposito.getValor();
			depositosCreados.put(deposito.getValor(), deposito);

		}

		return depositosCreados;
	}

	@Override
	public HashMap<String, Dispensador> obtenerDispensadores() {
		String clave;
		HashMap<String, Dispensador> dispensadoresCreado = new HashMap<String, Dispensador>();
		Query q = session.createQuery("select j from Dispensador j");
		List results = q.list();

		Iterator equiposIterator = results.iterator();

		while (equiposIterator.hasNext()) {
			Dispensador dispensador = (Dispensador) equiposIterator.next();

			// dispensador= new
			// Dispensador(dispensador.getClave(),dispensador.getNombreProducto(),dispensador.getPrecio(),
			// dispensador.getCantidad());
			// clave=dispensador.getClave();
			dispensadoresCreado.put(dispensador.getClave(), dispensador);

		}

		return dispensadoresCreado;
	}

	@Override
	public boolean guardarDepositos(HashMap<Integer, Deposito> depositos) {
		// TODO Auto-generated method stub

		for (Integer key : depositos.keySet()) {
			Deposito deposito = (Deposito) depositos.get(key);

			session.save(deposito);
		}
		return true;
	}

	@Override
	public boolean guardarDispensadores(HashMap<String, Dispensador> dispensadores) {
		// TODO Auto-generated method stub

		for (String key : dispensadores.keySet()) {
			Dispensador dispensador = (Dispensador) dispensadores.get(key);

			session.save(dispensador);
		}
		return true;
	}

}
