package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.JInternalFrame;
import java.awt.Label;

public class ViewCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JTextField txtTelf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewCliente dialog = new ViewCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\ortopedias.png"));
		setBounds(100, 100, 783, 486);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("sxadxas");
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 23, 578, 299);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		buscar();
		menuBar();
		btn();
		btnGrup();
		txtPanel();
		btnPanel();
	}
	
	public void buscar() {
		
		JButton okButton = new JButton("");
		okButton.setIcon(new ImageIcon("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\detection.png"));
		okButton.setActionCommand("OK");
		okButton.setBounds(717, 57, 36, 23);
		contentPanel.add(okButton);
		
		txtBuscar = new JTextField();
		txtBuscar.setForeground(Color.LIGHT_GRAY);
		txtBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscar.setText("Buscar");
		txtBuscar.setBounds(630, 57, 88, 23);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
	}
	
	public void menuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Articulo");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmProveedor = new JMenuItem("Proveedor");
		mnNewMenu.add(mntmProveedor);
	}
	
	public void btn() {
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setActionCommand("OK");
		btnModificar.setBounds(630, 351, 119, 23);
		contentPanel.add(btnModificar);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInsertar.setActionCommand("Insertar");
		btnInsertar.setBounds(630, 249, 119, 23);
		contentPanel.add(btnInsertar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setActionCommand("Nuevo");
		btnNuevo.setBounds(630, 283, 119, 23);
		contentPanel.add(btnNuevo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setActionCommand("Eliminar");
		btnEliminar.setBounds(630, 317, 119, 23);
		contentPanel.add(btnEliminar);
			
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setBounds(640, 94, 86, 14);
		contentPanel.add(lblBuscarPor);

		
		JButton btnMostrarTodos = new JButton("Mostrar Todos");
		btnMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMostrarTodos.setActionCommand("Mostrar Todos");
		btnMostrarTodos.setBounds(630, 11, 119, 23);
		contentPanel.add(btnMostrarTodos);
		
	}
	
	public void btnGrup() {
		
		JRadioButton rdbtnDni = new JRadioButton("Dni");
		rdbtnDni.setBounds(663, 115, 109, 23);
		contentPanel.add(rdbtnDni);
		
		JRadioButton rdbtnNombre = new JRadioButton("Nombre");
		rdbtnNombre.setBounds(661, 145, 109, 23);
		contentPanel.add(rdbtnNombre);
		
		JRadioButton rdbtnApellidos = new JRadioButton("Apellidos");
		rdbtnApellidos.setBounds(661, 176, 109, 23);
		contentPanel.add(rdbtnApellidos);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnDni);
		group.add(rdbtnNombre);
		group.add(rdbtnApellidos);
	}
	
	public void txtPanel() {

		txtDni = new JTextField();
		txtDni.setForeground(Color.GRAY);
		txtDni.setText("DNI");
		txtDni.setColumns(10);
		txtDni.setBounds(31, 329, 86, 23);
		contentPanel.add(txtDni);
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre");
		txtNombre.setForeground(Color.GRAY);
		txtNombre.setColumns(10);
		txtNombre.setBounds(31, 363, 86, 23);
		contentPanel.add(txtNombre);
		
		txtApellidos = new JTextField();
		txtApellidos.setText("Apellidos");
		txtApellidos.setForeground(Color.GRAY);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(149, 329, 86, 23);
		contentPanel.add(txtApellidos);
		
		txtDireccion = new JTextField();
		txtDireccion.setText("Direcci\u00F3n");
		txtDireccion.setForeground(Color.GRAY);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(149, 363, 86, 23);
		contentPanel.add(txtDireccion);
		
		txtEmail = new JTextField();
		txtEmail.setText("Email");
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setColumns(10);
		txtEmail.setBounds(269, 329, 86, 23);
		contentPanel.add(txtEmail);
		
		txtTelf = new JTextField();
		txtTelf.setText("Telf");
		txtTelf.setForeground(Color.GRAY);
		txtTelf.setColumns(10);
		txtTelf.setBounds(269, 363, 86, 23);
		contentPanel.add(txtTelf);
		
		JEditorPane dtrpnNotas = new JEditorPane();
		dtrpnNotas.setText("Notas...");
		dtrpnNotas.setForeground(Color.GRAY);
		dtrpnNotas.setBounds(390, 329, 218, 54);
		contentPanel.add(dtrpnNotas);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\w7\\Desktop\\ortopedias.png"));
		label.setBounds(-215, -292, 604, 616);
		contentPanel.add(label);
	}
	
	
	public void btnPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnModificar_4 = new JButton("Ver Historial");
		buttonPane.add(btnModificar_4);
		btnModificar_4.setActionCommand("Ver Historial");
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setActionCommand("Cancelar");
			buttonPane.add(cancelButton);
		}
	}
}
