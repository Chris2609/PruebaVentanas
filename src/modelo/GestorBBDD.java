package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class GestorBBDD {

	Conector con = new Conector();

	public void insertarCliente(Cliente cliente) {

		try {
			con.conectar();
			PreparedStatement insertarC = con.getConexion().prepareStatement(
					"INSERT INTO clientes (dni, nombre, apellidos, direccion, localidad) VALUES (?,?,?,?,?)");
			insertarC.setString(1, cliente.getDni());
			insertarC.setString(2, cliente.getNombre());
			insertarC.setString(3, cliente.getApellido());
			insertarC.setString(4, cliente.getDireccion());
			insertarC.setString(5, cliente.getLocalidad());

			insertarC.execute();

			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void eliminarCliente(String dni) {

		try {
			con.conectar();
			PreparedStatement eliminarC;
			eliminarC = con.getConexion().prepareStatement("DELETE FROM clientes WHERE dni = ?");
			eliminarC.setString(1, dni);

			eliminarC.execute();

			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modificarCliente(String dni, Cliente cliente) {

		try {
			con.conectar();
			PreparedStatement modificarC = con.getConexion().prepareStatement(
					"UPDATE clientes SET nombre= ?, apellidos= ?, direccion= ?, localidad= ? WHERE dni = ?");
			modificarC.setString(1, cliente.getNombre());
			modificarC.setString(2, cliente.getApellido());
			modificarC.setString(3, cliente.getDireccion());
			modificarC.setString(4, cliente.getLocalidad());
			modificarC.setString(5, dni);

			modificarC.execute();

			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertarHotel(Hotel hotel) {

		try {
			con.conectar();

			PreparedStatement insertarH = con.getConexion().prepareStatement(
					"INSERT INTO hoteles (cif, nombre, gerente, estrellas, compania) VALUES (?,?,?,?,?)");
			insertarH.setString(1, hotel.getCif());
			insertarH.setString(2, hotel.getNombre());
			insertarH.setString(3, hotel.getGerente());
			insertarH.setInt(4, hotel.getEstrellas());
			insertarH.setString(5, hotel.getCompañia());

			insertarH.execute();

			con.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertarHabitacion(Habitacion habitacion) {

		try {
			con.conectar();

			PreparedStatement insertarHabi = con.getConexion()
					.prepareStatement("INSERT INTO habitaciones VALUES (?,?,?,?,?)");
			insertarHabi.setInt(1, habitacion.getId());
			insertarHabi.setInt(2, habitacion.getId_hotel());
			insertarHabi.setString(3, habitacion.getNumero());
			insertarHabi.setString(4, habitacion.getDescripcion());
			insertarHabi.setDouble(5, habitacion.getPrecio());

			insertarHabi.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<String> comprobarDNI() {

		con.conectar();
		ArrayList<String> dnis = new ArrayList<String>();
		try {
			PreparedStatement comprobarDNI = con.getConexion().prepareStatement("SELECT dni FROM clientes");
			comprobarDNI.execute();
			ResultSet dnibbdd = comprobarDNI.executeQuery();

			while (dnibbdd.next()) {
				dnis.add(dnibbdd.getString("dni"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dnis;
	}
	
	public ArrayList<Hotel> hoteles(){
		con.conectar();
		ArrayList<Hotel> hoteles = new ArrayList<Hotel>();
		
		try {
			PreparedStatement listahoteles = con.getConexion().prepareStatement("SELECT * FROM hoteles");
			listahoteles.execute();
			
			ResultSet resultado = listahoteles.executeQuery();
			
			while (resultado.next()) {
				Hotel hotel = new Hotel();
				
				hotel.setId(resultado.getInt("id"));
				hotel.setCif(resultado.getString("cif"));
				hotel.setNombre(resultado.getString("nombre"));
				hotel.setGerente(resultado.getString("gerente"));
				hotel.setEstrellas(resultado.getInt("estrellas"));
				hotel.setCompañia(resultado.getString("compania"));
				
				hoteles.add(hotel);
			}
			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hoteles;
	}

	public ArrayList<Habitacion> habitacionesHotel() {
		con.conectar();
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

		try {
			PreparedStatement habitacionesHotel = con.getConexion()
					.prepareStatement("SELECT * FROM habitaciones");
			
			habitacionesHotel.execute();

			ResultSet resultado = habitacionesHotel.executeQuery();

			while (resultado.next()) {
				Habitacion habitacion = new Habitacion();
				habitacion.setId(resultado.getInt("id"));
				habitacion.setId_hotel(resultado.getInt("id_hotel"));
				habitacion.setNumero(resultado.getString("numero"));
				habitacion.setDescripcion(resultado.getString("descripcion"));
				habitacion.setPrecio(resultado.getDouble("precio"));

				habitaciones.add(habitacion);
			}

			con.cerrarConexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return habitaciones;
	}

	public void insertarReserva(Reserva reserva) {

		con.conectar();

		try {
			PreparedStatement insReserv = con.getConexion()
					.prepareStatement("INSERT INTO reservas (id_habitacion, dni, desde, hasta) VALUES (?, ?, ?, ?)");
			insReserv.setInt(1, reserva.getId_habitacion());
			insReserv.setString(2, reserva.getDni());
			insReserv.setDate(3, (Date) reserva.getDesde());
			insReserv.setDate(4, (Date) reserva.getHasta());

			insReserv.execute();
			con.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminarReserva(int id) {

		con.conectar();

		try {
			PreparedStatement elimReserv = con.getConexion().prepareStatement("DELETE FROM reservas WHERE id = ?");
			elimReserv.setInt(1, id);
			elimReserv.execute();
			con.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Cliente> clientes() {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		con.conectar();
		try {
			PreparedStatement verC = con.getConexion().prepareStatement("SELECT * FROM clientes");
			verC.execute();
			ResultSet resultado = verC.executeQuery();

			while (resultado.next()) {

				Cliente cliente = new Cliente();
				cliente.setDni(resultado.getString("dni"));
				cliente.setNombre(resultado.getString("nombre"));
				cliente.setApellido(resultado.getString("apellidos"));
				cliente.setDireccion(resultado.getString("direccion"));
				cliente.setLocalidad(resultado.getString("localidad"));

				clientes.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public Cliente obtenerClientePorDNI(String dni) {

		Cliente cliente = new Cliente();
		con.conectar();
		try {
			PreparedStatement verC = con.getConexion().prepareStatement("SELECT * FROM clientes WHERE dni = ?");
			verC.setString(1, dni);
			verC.execute();
			ResultSet resultado = verC.executeQuery();

			while (resultado.next()) {

				cliente.setDni(resultado.getString("dni"));
				cliente.setNombre(resultado.getString("nombre"));
				cliente.setApellido(resultado.getString("apellidos"));
				cliente.setDireccion(resultado.getString("direccion"));
				cliente.setLocalidad(resultado.getString("localidad"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}
	
	public ArrayList<Reserva> reservas() {
		con.conectar();
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
		try {
			PreparedStatement verR = con.getConexion().prepareStatement("SELECT * FROM reservas");
			verR.execute();

			ResultSet resultado = verR.executeQuery();

			while (resultado.next()) {
				Reserva reserva = new Reserva();
				reserva.setId(resultado.getInt("dni"));
				reserva.setId_habitacion(resultado.getInt("id_habitacion"));
				reserva.setDni(resultado.getString("dni"));
				reserva.setDesde(resultado.getDate("desde"));
				reserva.setHasta(resultado.getDate("hasta"));

				reservas.add(reserva);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservas;
	}

}
