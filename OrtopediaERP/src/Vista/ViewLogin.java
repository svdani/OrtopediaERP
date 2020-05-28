package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Datos.SQLAdmin;
import Modelo.Admin;


import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class ViewLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField textUser;
	private JComboBox comboBox;
	SQLAdmin conector = new SQLAdmin();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewLogin dialog = new ViewLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el dialog.
	 */
	public ViewLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewLogin.class.getResource("/icon/ortopedias.png")));
		setBounds(100, 100, 339, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			
		txtPanel();
		titel();
		btnPanel();
			
	}
	
	//--------------------------------------------------------------------------------CAJAS TEXTO--------------------------------------------------------------------------------------	
	
	/*
	 * Crea las cajas de texto para insertar y modificar registros
	 */
	public void txtPanel() {
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 62, 90, 20);
		
		textUser = new JTextField();
		textUser.setBounds(98, 31, 90, 20);
		textUser.setColumns(10);
			
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Articulos", "Admin", "Clientes", "Comandas", "Movimientos Almacen", "Proveedores"}));
		comboBox.setBounds(208, 28, 105, 20);
		contentPanel.add(comboBox);
	}
	
	private void titel() {
		JLabel lblUsuario = new JLabel("usuario:");
		lblUsuario.setBounds(24, 31, 74, 14);
		contentPanel.setLayout(null);
		
		JLabel lblContrasea = new JLabel("contraseña:");
		lblContrasea.setBounds(24, 62, 74, 14);
		contentPanel.add(lblContrasea);
		contentPanel.add(lblUsuario);
		contentPanel.add(textUser);
		contentPanel.add(passwordField);
	}
	
	/*
	 * Crea el panel inferior de botones con el boton cancelar que cierra el dialog
	 */
	public void btnPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);


		JButton okButton = new JButton("Iniciar Sesión");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Admin adm = new Admin(textUser.getText().toString());

					adm = conector.buscaDniAdmins(adm);

					// COMPRUEBA EN LA BASE DE DATOS QUE EL USUARIO Y CONTRASEÑA INTRODUCIDOS COINCIDAN
					if(adm.getPassword().equals(passwordField.getText().toString())) {								

						JOptionPane.showMessageDialog(null, "BIENVENIDO");	

						// COMPRUEBA TU ELECCIÓN DE VENTANA A LA QUE DIRIGIRSE
						switch (comboBox.getSelectedItem().toString()) {

						case "Articulos":
							ViewArticulo windowArticulo = new ViewArticulo();
							windowArticulo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							windowArticulo.setVisible(true);
							dispose();
							break;

						case "Admin":
							ViewAdmin windowAdmin = new ViewAdmin();
							windowAdmin.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							windowAdmin.setVisible(true);
							dispose();
							break;

						case "Clientes":
							ViewCliente windowCliente = new ViewCliente();
							windowCliente.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							windowCliente.setVisible(true);
							dispose();
							break;

						case "Comandas":
							ViewPedido windowComanda = new ViewPedido();
							windowComanda.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							windowComanda.setVisible(true);
							dispose();
							break;

						case "Movimientos Almacen":
							ViewMovimientoAlmacen windowMovimientoAlmacen = new ViewMovimientoAlmacen();
							windowMovimientoAlmacen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							windowMovimientoAlmacen.setVisible(true);
							dispose();
							break;

						case "Proveedores":
							ViewProveedor windowProveedor = new ViewProveedor();
							windowProveedor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							windowProveedor.setVisible(true);
							dispose();
							break;

						default:
							break;
						}

					}else{
						//SI EL USUARIO Y LA CONTRASEÑA NO COICIDEN
						JOptionPane.showMessageDialog(null, "Ups... la contraseña es incorrecta");
					};

				} catch (Exception e2) { 
					//SI EL USUARIO NO EXISTE
					JOptionPane.showMessageDialog(null, "Ups... el usuario es incorrecto");
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);


		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}
	
	
}
