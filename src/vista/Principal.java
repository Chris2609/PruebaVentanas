package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.GestorBBDD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField textDni;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textDireccion;
	private JTextField textLocalidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Insertar Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 26, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(137, 26, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(277, 26, 69, 14);
		contentPane.add(lblApellidos);
		
		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setBounds(62, 70, 65, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(221, 70, 69, 14);
		contentPane.add(lblLocalidad);
		
		textDni = new JTextField();
		textDni.setBounds(41, 23, 86, 20);
		contentPane.add(textDni);
		textDni.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(182, 23, 86, 20);
		contentPane.add(textNombre);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(338, 23, 86, 20);
		contentPane.add(textApellidos);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(118, 67, 86, 20);
		contentPane.add(textDireccion);
		
		textLocalidad = new JTextField();
		textLocalidad.setColumns(10);
		textLocalidad.setBounds(277, 67, 86, 20);
		contentPane.add(textLocalidad);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.setDni(textDni.getText());
				cliente.setNombre(textNombre.getText());
				cliente.setApellido(textApellidos.getText());
				cliente.setDireccion(textDireccion.getText());
				cliente.setLocalidad(textLocalidad.getText());
				
				GestorBBDD insertarCliente = new GestorBBDD();
				insertarCliente.insertarCliente(cliente);
				textDni.setText(null);
				textNombre.setText(null);
				textApellidos.setText(null);
				textDireccion.setText(null);
				textLocalidad.setText(null);
				JOptionPane.showMessageDialog(null, "Usuario insertado");
			}
		});
		btnGuardar.setBounds(166, 124, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateCliente modificarC = new UpdateCliente();
					modificarC.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					modificarC.setModal(true); /*Hace que no se peuda acceder a la ventana anterior*/
					modificarC.setVisible(true);
					
				} catch (Exception ea) {
					ea.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(311, 124, 89, 23);
		contentPane.add(btnModificar);
		
		
		
	}
}
