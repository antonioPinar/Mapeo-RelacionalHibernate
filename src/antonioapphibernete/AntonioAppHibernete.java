/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antonioapphibernete;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author anton
 */
public class AntonioAppHibernete {

    static SessionFactory sesion;

    public void insertarVuelo(String codVuelo, String horaSalida, String destino, String procedencia, Integer plazasFumador, Integer plazasNoFumador, Integer plazasTurista, Integer plazasPrimera) {
        Session session = sesion.openSession();
        //Indicamos el comienzo de una transaccion
        Transaction transaccion = session.beginTransaction();

        //crea una instancia con valores para todos los atributos.
        Vuelos vuelo1 = new Vuelos(codVuelo, horaSalida, destino, procedencia, plazasFumador, plazasNoFumador, plazasTurista, plazasPrimera);
        session.save(vuelo1); //almacena el objeto vuelo
        transaccion.commit(); //valida la transacción
        session.close(); //debemos cerrar la sesión una vez completada la transacción.

    }

    public void cambiarDestinoVuelo(String codVuelo, String destino) {
        Session session = sesion.openSession();
        //Indicamos el comienzo de una transaccion
        Transaction transaccion = session.beginTransaction();

        System.out.println("DATOS DE UN VUELO");
        //carga los datos de un vuelo determinado por su codigo de vuelo
        Vuelos vuelo = (Vuelos) session.load(Vuelos.class, (String) codVuelo);
        System.out.println("DESTINO ANTERIOR: " + vuelo.getDestino());

        //introducimos el destino nuevo
        vuelo.setDestino(destino);
        session.update(vuelo);
        System.out.println("DESTINO ACTUAL: " + vuelo.getDestino());

        //guardamos los cambios
        session.save(vuelo);
        transaccion.commit();
        session.close();
    }

    public void eliminarVuelo(String codVuelo) {
        Session session = sesion.openSession();
        //Indicamos el comienzo de una transaccion
        Transaction transaccion = session.beginTransaction();

        System.out.println("BORRAR UN VUELO");
        Vuelos vuelo = (Vuelos) session.load(Vuelos.class, (String) codVuelo);
        session.delete(vuelo);
        System.out.println("Vuelo borrado ");
        transaccion.commit();
        session.close();
    }

    public String mostrarVuelosMdBcn() {
        Session session = sesion.openSession();

        System.out.println("DATOS DE LOS VUELOS MD-BCN");
        Vuelos vuelo = new Vuelos();
        Query q = session.createQuery("from Vuelos where destino = 'BARCELONA' and procedencia = 'MADRID'");

        //utilizamos un iterador para recorrer los vuelos que hemos volcado a una lista
        List<Vuelos> listaVuelo = q.list();
        Iterator<Vuelos> iter = listaVuelo.iterator();
        String texto = "";
        while (iter.hasNext()) {
            vuelo = (Vuelos) iter.next();
            texto += vuelo.toString();
//            System.out.println("CODIGO VUELO: " + vuelo.getCodVuelo());
//            System.out.println("HORA DE SALIDA: " + vuelo.getHoraSalida());
//            System.out.println("DESTINO: " + vuelo.getDestino());
//            System.out.println("PROCEDENCIA: " + vuelo.getProcedencia());
//            System.out.println("PLAZAS FUMADOR: " + vuelo.getPlazasFumador());
//            System.out.println("PLAZAS NO FUMADOR: " + vuelo.getPlazasNoFumador());
//            System.out.println("PLAZAS TURISTA: " + vuelo.getPlazasTurista());
//            System.out.println("PLAZAS PRIMERA: " + vuelo.getPlazasPrimera());
        }
        session.close();
        return texto;
    }

//    public static void main(String[] args) {
//
//        sesion = SessionFactoryUtil.getSessionFactory();
//
//        Session session = sesion.openSession();
//
//        //Indicamos el comienzo de una transaccion
//        Transaction transaccion = session.beginTransaction();
//
//    }
}
