package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class ViewComanda extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtIdComanda;
	private JTextField txtIdCliente;
	private JTextField txtPrecioTotal;
	private JTextField txtEstado;
	private JTextField txtFechaInico;
	private JTextField txtFechaLimite;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewComanda dialog = new ViewComanda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewComanda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\ortopedias.png"));
		setBounds(100, 100, 783, 537);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("sxadxas");
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 23, 578, 343);
		contentPanel.add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		
		buscar();
		menuBar();
		btn();
		btnFiltrar();
		btnGrup();
		calendarPanel();
		txtPanel();
		btnPanel();
	}

	
public void buscar() {
		
		JButton okButton = new JButton("");
		okButton.setIcon(new ImageIcon("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\detection.png"));
		okButton.setActionCommand("OK");
		okButton.setBounds(717, 48, 36, 23);
		contentPanel.add(okButton);
		
		txtBuscar = new JTextField();
		txtBuscar.setForeground(Color.LIGHT_GRAY);
		txtBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscar.setText("Buscar Cliente");
		txtBuscar.setBounds(630, 48, 88, 23);
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
		btnModificar.setActionCommand("OK");
		btnModificar.setBounds(630, 411, 119, 23);
		contentPanel.add(btnModificar);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setActionCommand("Insertar");
		btnInsertar.setBounds(630, 309, 119, 23);
		contentPanel.add(btnInsertar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setActionCommand("Nuevo");
		btnNuevo.setBounds(630, 343, 119, 23);
		contentPanel.add(btnNuevo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setActionCommand("Eliminar");
		btnEliminar.setBounds(630, 377, 119, 23);
		contentPanel.add(btnEliminar);
			
		JLabel lblBuscarPor = new JLabel("Filtrar por:");
		lblBuscarPor.setBounds(630, 86, 86, 14);
		contentPanel.add(lblBuscarPor);

		
		JButton btnMostrarTodos = new JButton("Mostrar Todos");
		btnMostrarTodos.setActionCommand("Mostrar Todos");
		btnMostrarTodos.setBounds(630, 11, 119, 23);
		contentPanel.add(btnMostrarTodos);
		
	}
	public void btnFiltrar() {
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setActionCommand("Filtrar");
		btnFiltrar.setBounds(630, 257, 119, 23);
		contentPanel.add(btnFiltrar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(707, 108, 50, 20);
		contentPanel.add(comboBox);
	}
	
	public void calendarPanel() {
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setToolTipText("Desde");
		dateChooser.setBounds(658, 198, 95, 20);
		contentPanel.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(658, 226, 95, 20);
		contentPanel.add(dateChooser_1);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(618, 198, 86, 14);
		contentPanel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(618, 229, 86, 14);
		contentPanel.add(lblHasta);
	}
	
	public void btnGrup() {
		
		JRadioButton rdbtnDni = new JRadioButton("Estado");
		rdbtnDni.setBounds(642, 107, 59, 23);
		contentPanel.add(rdbtnDni);
		
		JRadioButton rdbtnNombre = new JRadioButton("Fecha Inicio");
		rdbtnNombre.setBounds(640, 137, 109, 23);
		contentPanel.add(rdbtnNombre);
		
		JRadioButton rdbtnApellidos = new JRadioButton("Fecha Limite");
		rdbtnApellidos.setBounds(640, 168, 109, 23);
		contentPanel.add(rdbtnApellidos);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnDni);
		group.add(rdbtnNombre);
		group.add(rdbtnApellidos);
	}
	
	public void txtPanel() {

		txtIdComanda = new JTextField();
		txtIdComanda.setForeground(Color.GRAY);
		txtIdComanda.setText("ID Comanda");
		txtIdComanda.setColumns(10);
		txtIdComanda.setBounds(30, 377, 86, 23);
		contentPanel.add(txtIdComanda);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setText("ID Cliente");
		txtIdCliente.setForeground(Color.GRAY);
		txtIdCliente.setColumns(10);
		txtIdCliente.setBounds(30, 411, 86, 23);
		contentPanel.add(txtIdCliente);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setText("Precio Total");
		txtPrecioTotal.setForeground(Color.GRAY);
		txtPrecioTotal.setColumns(10);
		txtPrecioTotal.setBounds(148, 377, 86, 23);
		contentPanel.add(txtPrecioTotal);
		
		txtEstado = new JTextField();
		txtEstado.setText("Estado");
		txtEstado.setForeground(Color.GRAY);
		txtEstado.setColumns(10);
		txtEstado.setBounds(148, 411, 86, 23);
		contentPanel.add(txtEstado);
		
		txtFechaInico = new JTextField();
		txtFechaInico.setText("Fecha Inicio");
		txtFechaInico.setForeground(Color.GRAY);
		txtFechaInico.setColumns(10);
		txtFechaInico.setBounds(268, 377, 86, 23);
		contentPanel.add(txtFechaInico);
		
		txtFechaLimite = new JTextField();
		txtFechaLimite.setText("Fecha Limite");
		txtFechaLimite.setForeground(Color.GRAY);
		txtFechaLimite.setColumns(10);
		txtFechaLimite.setBounds(268, 411, 86, 23);
		contentPanel.add(txtFechaLimite);
		
		JEditorPane dtrpnNotas = new JEditorPane();
		dtrpnNotas.setText("Notas...");
		dtrpnNotas.setForeground(Color.GRAY);
		dtrpnNotas.setBounds(389, 377, 218, 54);
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
		
		JButton btnVerLiniaComanda = new JButton("Ver Lineas Comanda");
		btnVerLiniaComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonPane.add(btnVerLiniaComanda);
		btnVerLiniaComanda.setActionCommand("Ver Historial");
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setActionCommand("Cancelar");
			buttonPane.add(cancelButton);
		}
	}
}
