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
import javax.swing.JComboBox;
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

public class ViewMovimientoAlmacen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtIdMovimiento;
	private JTextField txtIdArticulo;
	private JTextField txtTipo;
	private JTextField txtUbicacion;
	private JTextField txtFecha;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewMovimientoAlmacen dialog = new ViewMovimientoAlmacen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewMovimientoAlmacen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\ortopedias.png"));
		setBounds(100, 100, 783, 498);
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
		txtBuscar.setText("Buscar Articulo");
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
		btnModificar.setBounds(630, 380, 119, 23);
		contentPanel.add(btnModificar);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setActionCommand("Insertar");
		btnInsertar.setBounds(630, 278, 119, 23);
		contentPanel.add(btnInsertar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setActionCommand("Nuevo");
		btnNuevo.setBounds(630, 312, 119, 23);
		contentPanel.add(btnNuevo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setActionCommand("Eliminar");
		btnEliminar.setBounds(630, 346, 119, 23);
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
		btnFiltrar.setBounds(630, 226, 119, 23);
		contentPanel.add(btnFiltrar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(707, 108, 50, 20);
		contentPanel.add(comboBox);
	}
	
	public void calendarPanel() {
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setToolTipText("Desde");
		dateChooser.setBounds(658, 167, 95, 20);
		contentPanel.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(658, 195, 95, 20);
		contentPanel.add(dateChooser_1);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(618, 167, 86, 14);
		contentPanel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(618, 198, 86, 14);
		contentPanel.add(lblHasta);
	}
	
	public void btnGrup() {
		
		JRadioButton rdbtnTipo = new JRadioButton("Tipo");
		rdbtnTipo.setBounds(642, 107, 59, 23);
		contentPanel.add(rdbtnTipo);
		
		JRadioButton rdbtnNombre = new JRadioButton("Fecha");
		rdbtnNombre.setBounds(640, 137, 109, 23);
		contentPanel.add(rdbtnNombre);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnTipo);
		group.add(rdbtnNombre);
	}
	
	public void txtPanel() {

		txtIdMovimiento = new JTextField();
		txtIdMovimiento.setForeground(Color.GRAY);
		txtIdMovimiento.setText("ID Movimeinto");
		txtIdMovimiento.setColumns(10);
		txtIdMovimiento.setBounds(30, 377, 86, 23);
		contentPanel.add(txtIdMovimiento);
		
		txtIdArticulo = new JTextField();
		txtIdArticulo.setText("ID Articulo");
		txtIdArticulo.setForeground(Color.GRAY);
		txtIdArticulo.setColumns(10);
		txtIdArticulo.setBounds(154, 377, 86, 23);
		contentPanel.add(txtIdArticulo);
		
		txtTipo = new JTextField();
		txtTipo.setText("Tipo Movimiento");
		txtTipo.setForeground(Color.GRAY);
		txtTipo.setColumns(10);
		txtTipo.setBounds(278, 377, 86, 23);
		contentPanel.add(txtTipo);
		
		txtUbicacion = new JTextField();
		txtUbicacion.setText("Ubicacion");
		txtUbicacion.setForeground(Color.GRAY);
		txtUbicacion.setColumns(10);
		txtUbicacion.setBounds(398, 377, 86, 23);
		contentPanel.add(txtUbicacion);
		
		txtFecha = new JTextField();
		txtFecha.setText("Fecha");
		txtFecha.setForeground(Color.GRAY);
		txtFecha.setColumns(10);
		txtFecha.setBounds(522, 377, 86, 23);
		contentPanel.add(txtFecha);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\w7\\Desktop\\ortopedias.png"));
		label.setBounds(-215, -292, 604, 616);
		contentPanel.add(label);
		
	}
	
	
	public void btnPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnModificar_4 = new JButton("Ver Lineas Comanda");
		btnModificar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonPane.add(btnModificar_4);
		btnModificar_4.setActionCommand("Ver Historial");
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setActionCommand("Cancelar");
			buttonPane.add(cancelButton);
		}
	}
}
