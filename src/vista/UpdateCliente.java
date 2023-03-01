package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.GestorBBDD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.SystemColor;

public class UpdateCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textDniM;
	private JTextField textDireccionM;
	private JTextField textNombreM;
	private JTextField textApellidosM;
	private JTextField textLocalidadM;
	private String dni;
	JButton okButton = new JButton("Modificar");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UpdateCliente dialog = new UpdateCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UpdateCliente() {
		setTitle("Modificar usuario");
		setBounds(100, 100, 472, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDni = new JLabel("DNI");
			lblDni.setBounds(10, 39, 46, 14);
			contentPanel.add(lblDni);
		}
		{
			textDniM = new JTextField();
			textDniM.setColumns(10);
			textDniM.setBounds(41, 36, 86, 20);
			contentPanel.add(textDniM);
		}
		{
			JLabel lblDireccion = new JLabel("Dirección");
			lblDireccion.setBounds(62, 83, 65, 14);
			contentPanel.add(lblDireccion);
		}
		{
			textDireccionM = new JTextField();
			textDireccionM.setColumns(10);
			textDireccionM.setBounds(118, 80, 86, 20);
			contentPanel.add(textDireccionM);
		}
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(137, 39, 46, 14);
			contentPanel.add(lblNombre);
		}
		{
			textNombreM = new JTextField();
			textNombreM.setColumns(10);
			textNombreM.setBounds(182, 36, 86, 20);
			contentPanel.add(textNombreM);
		}
		{
			JLabel lblApellidos = new JLabel("Apellidos");
			lblApellidos.setBounds(277, 39, 69, 14);
			contentPanel.add(lblApellidos);
			
		}
		{
			textApellidosM = new JTextField();
			textApellidosM.setColumns(10);
			textApellidosM.setBounds(338, 36, 86, 20);
			contentPanel.add(textApellidosM);
		}
		{
			JLabel lblLocalidad = new JLabel("Localidad");
			lblLocalidad.setBounds(221, 83, 69, 14);
			contentPanel.add(lblLocalidad);
		}
		{
			textLocalidadM = new JTextField();
			textLocalidadM.setColumns(10);
			textLocalidadM.setBounds(277, 80, 86, 20);
			contentPanel.add(textLocalidadM);
		}
		{
			JButton btnCargarDatos = new JButton("Cargar Datos");
			btnCargarDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				dni = textDniM.getText();
				GestorBBDD modificar = new GestorBBDD();
				Cliente cliente = modificar.obtenerClientePorDNI(dni);
				
				textNombreM.setText(cliente.getNombre());
				textApellidosM.setText(cliente.getApellido());
				textDireccionM.setText(cliente.getDireccion());
				textLocalidadM.setText(cliente.getLocalidad());
				okButton.setEnabled(true);
				okButton.setBackground(new Color(0, 255, 0));
				}
			});
			btnCargarDatos.setBounds(62, 128, 125, 23);
			contentPanel.add(btnCargarDatos);
		}
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dni = textDniM.getText();
				GestorBBDD eliminar = new GestorBBDD();
				eliminar.eliminarCliente(dni);
			}
		});
		btnNewButton.setBounds(274, 128, 89, 23);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.setForeground(new Color(0, 0, 0));
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Cliente cliente = new Cliente();
						cliente.setDni(textDniM.getText());
						cliente.setNombre(textNombreM.getText());
						cliente.setApellido(textApellidosM.getText());
						cliente.setDireccion(textDireccionM.getText());
						cliente.setLocalidad(textLocalidadM.getText());
						
						GestorBBDD modificarC = new GestorBBDD();
						modificarC.modificarCliente(dni, cliente);
						textDniM.setText(null);
						textNombreM.setText(null);
						textApellidosM.setText(null);
						textDireccionM.setText(null);
						textLocalidadM.setText(null);
						JOptionPane.showMessageDialog(null, "Usuario modificado");
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
