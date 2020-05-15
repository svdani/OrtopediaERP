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

public class ViewLiniaComanda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtIdComanda;
	private JTextField txtIdLiniaComanda;
	private JTextField txtPrecioTotal;
	private JTextField txtEstado;
	private JTextField txtTipo;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewLiniaComanda dialog = new ViewLiniaComanda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewLiniaComanda() {
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
		
	}
	public void btnFiltrar() {
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setActionCommand("Filtrar");
		btnFiltrar.setBounds(630, 257, 119, 23);
		contentPanel.add(btnFiltrar);
		
		JComboBox boxEstado = new JComboBox();
		boxEstado.setBounds(707, 108, 50, 20);
		contentPanel.add(boxEstado);
	}
	
	public void calendarPanel() {
	}
	
	public void btnGrup() {
		
		JRadioButton rdbtnEstado = new JRadioButton("Estado");
		rdbtnEstado.setBounds(642, 107, 59, 23);
		contentPanel.add(rdbtnEstado);
		
		JRadioButton rdbtnTipo = new JRadioButton("Tipo");
		rdbtnTipo.setBounds(640, 137, 59, 23);
		contentPanel.add(rdbtnTipo);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEstado);
		group.add(rdbtnTipo);
	}
	
	public void txtPanel() {

		txtIdComanda = new JTextField();
		txtIdComanda.setForeground(Color.GRAY);
		txtIdComanda.setText("ID Comanda");
		txtIdComanda.setColumns(10);
		txtIdComanda.setBounds(30, 411, 86, 23);
		contentPanel.add(txtIdComanda);
		
		txtIdLiniaComanda = new JTextField();
		txtIdLiniaComanda.setText("ID Linia Comanda");
		txtIdLiniaComanda.setForeground(Color.GRAY);
		txtIdLiniaComanda.setColumns(10);
		txtIdLiniaComanda.setBounds(30, 377, 86, 23);
		contentPanel.add(txtIdLiniaComanda);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setText("ID Articulo");
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
		
		txtTipo = new JTextField();
		txtTipo.setText("Tipo");
		txtTipo.setForeground(Color.GRAY);
		txtTipo.setColumns(10);
		txtTipo.setBounds(268, 377, 86, 23);
		contentPanel.add(txtTipo);
		
		txtPrecio = new JTextField();
		txtPrecio.setText("Precio");
		txtPrecio.setForeground(Color.GRAY);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(268, 411, 86, 23);
		contentPanel.add(txtPrecio);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\w7\\Desktop\\ortopedias.png"));
		label.setBounds(-215, -292, 604, 616);
		contentPanel.add(label);
		
		txtCantidad = new JTextField();
		txtCantidad.setText("Cantidad");
		txtCantidad.setForeground(Color.GRAY);
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(389, 377, 86, 23);
		contentPanel.add(txtCantidad);
		
		JComboBox boxTipo = new JComboBox();
		boxTipo.setBounds(707, 138, 50, 20);
		contentPanel.add(boxTipo);
		
	}
	
	
	public void btnPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonPane.add(btnAtras);
		btnAtras.setActionCommand("Ver Historial");
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setActionCommand("Cancelar");
			buttonPane.add(cancelButton);
		}
	}
}
