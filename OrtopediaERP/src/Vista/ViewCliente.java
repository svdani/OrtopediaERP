package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Datos.SQLAdmin;
import Datos.SQLCliente;
import Modelo.Admin;
import Modelo.Cliente;
import Modelo.Pedido;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Canvas;
import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.JInternalFrame;
import java.awt.Label;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;

public class ViewCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JTextField txtTelf;
	private JEditorPane txtNotas;
	
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnInsertar;
	private JButton btnModificar;
	private JButton btnVerNota;
	
	private JRadioButton rdbtnDni;
	private JRadioButton rdbtnNombre;
	private JRadioButton rdbtnApellidos;
	
	
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
	 * Crea el dialog.
	 */
	public ViewCliente() {
		setTitle("ERP Ortopedias - Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewCliente.class.getResource("/icon/ortopedias.png")));
		setBounds(100, 100, 783, 486);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 23, 578, 299);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row,int column) {
				//Todas las celdas en false
				return false;
			}
		};

		//----INTRODUCE NOMBRE COLUMNAS TABLA DESDE SQL
		model.addColumn("Dni");
		model.addColumn("Nombre");
		model.addColumn("Apellidos");
		model.addColumn("Dirección");
		model.addColumn("Email");
		model.addColumn("Telf");
		model.addColumn("Notas");

		table.setModel(model);

		//---FUNCIONES TABLA 
		updateTable();
		selectRow();
		scrollPane.setViewportView(table);
		
		btnMostrarTodo();
		btnBuscar();
		btnNuevo();
		btnModifica();
		btnInserta();
		btnElimina();
		
		menuBar();
		btnGrup();
		txtPanel();
		btnPanel();
	}
	
	//--------------------------------------------------------------------------------FUNCIONES TABLA----------------------------------------------------------------------------------	

	/*
	 * muestra todos los registros de la base de datos
	 */
	private void updateTable() {
		//---Actualiza valores que se muestran en la tabla
		SQLCliente conCli = new SQLCliente();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Cliente cli:conCli.consultaClientes()) {
				model.addRow(new Object[] {
						cli.getDni(),
						cli.getNombre(),
						cli.getApellidos(),
						cli.getDireccion(),
						cli.getEmail(),
						cli.getTelf(),
						cli.getNotas()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla CLIENTE");
		}		
	}	
	
	/*
	 * muetra los valores del registro seleccionado en sus respectivas cajas de texto y bloquea el boton insertar
	 */
	public void selectRow() {
		//----FUNCION AL SELECCIONAR CAMPO

			table.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {

					btnModificar.setEnabled(true);// DESBLOQUEA BTN EDITAR
					btnEliminar.setEnabled(true);// DESBLOQUEA BTN ELIMINAR
					btnInsertar.setEnabled(false);// BLOQUEA BTN INSERTAR
					btnNuevo.setEnabled(true);// DESBLOQUEA BTN NUEVO

					txtDni.setEnabled(false);// BLOQUEA CAJA DE TEXTO CIF

					//------CAMBIA VALOR CAJAS TEXTO SEGUN EL REGISTRO SELECCIONADO
					txtDni.setText(table.getValueAt(table.getSelectedRow(), 0).toString());				
					txtNombre.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtApellidos.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					txtDireccion.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					txtEmail.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					txtTelf.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
					txtNotas.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					

				}
			});
		}
			
	private void updateTableBuscar(String registro, String filtro) {
		//---Actualiza valores que se muestran en la tabla
		
		SQLCliente conCli = new SQLCliente();
		
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Cliente cli:conCli.buscaClientes(registro, filtro)) {
				model.addRow(new Object[] {
						cli.getDni(),
						cli.getNombre(),
						cli.getApellidos(),
						cli.getDireccion(),
						cli.getEmail(),
						cli.getTelf(),
						cli.getNotas()
				});	
			}
		} catch (Exception e) {
			System.out.println("Error al buscar y actualizar la tabla ADMIN");
		}		
	}

	//--------------------------------------------------------------------------------MENU---------------------------------------------------------------------------------------------	
	
	/*
	 * Crea el Menu y sus difernetes items que actuan como boton de reconduccion a otro dialog
	 */
	public void menuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Men\u00FA");
		menuBar.add(mnNewMenu);
		
		//-------------------------------------------------------ITEMS MENU
		JMenuItem mntmAdmin = new JMenuItem("Admin");
		mntmAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewAdmin windowAdmin = new ViewAdmin();
				windowAdmin.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				windowAdmin.setVisible(true);
				dispose();
		
			}
		});
		mnNewMenu.add(mntmAdmin);
		
		JMenuItem mntmArticulo = new JMenuItem("Art\u00EDculo");
		mntmArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewArticulo windowArticulo = new ViewArticulo();
				windowArticulo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				windowArticulo.setVisible(true);
				dispose();
		
			}
		});
		mnNewMenu.add(mntmArticulo);
		
		JMenuItem mntmPedido = new JMenuItem("Pedido");
		mntmPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewPedido windowPedido = new ViewPedido();
				windowPedido.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				windowPedido.setVisible(true);
				dispose();
		
			}
		});
		mnNewMenu.add(mntmPedido);
		
		JMenuItem mntmMovimientosAlmacen = new JMenuItem("Movimientos Almacen");
		mntmMovimientosAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewMovimientoAlmacen windowMovimientoAlmacen = new ViewMovimientoAlmacen();
				windowMovimientoAlmacen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				windowMovimientoAlmacen.setVisible(true);
				dispose();
		
			}
		});
		mnNewMenu.add(mntmMovimientosAlmacen);
		
		JMenuItem mntmProveedor = new JMenuItem("Proveedor");
		mntmProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewProveedor windowProveedor = new ViewProveedor();
				windowProveedor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				windowProveedor.setVisible(true);
				dispose();
		
			}
		});
		mnNewMenu.add(mntmProveedor);
	
	}

	//--------------------------------------------------------------------------------BOTONES------------------------------------------------------------------------------------------	
	
	/*
	 * Crea el boton Nuevo que resetea las cajas de texto y bloquea los botones eliminar y modificar para no causar errores
	 */
	public void btnNuevo() {

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModificar.setEnabled(false);// BLOQUEA BTN EDITAR
				btnEliminar.setEnabled(false);// BLOQUEA BTN ELIMINAR
				btnInsertar.setEnabled(true);// DESBLOQUEA BTN INSERTAR
				btnNuevo.setEnabled(false);// BLOQUEA BTN NUEVO

				txtDni.setEnabled(true);// DESBLOQUEA CAJA DE TEXTO DNI

				txtDni.setText("");				
				txtNombre.setText("");
				txtApellidos.setText("");
				txtDireccion.setText("");
				txtEmail.setText("");
				txtTelf.setText("");
				txtNotas.setText("");
			}
		});

		btnNuevo.setActionCommand("Nuevo");
		btnNuevo.setBounds(630, 254, 119, 23);
		contentPanel.add(btnNuevo);
	}

	/*
	 * Crea el boton Insertar que llamando al archivo SQL inserta un nuevo registro y luego limpia las cajas para no causar errores
	 */
	public void btnInserta() {
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLCliente conCli = new SQLCliente();

				try {

					conCli.insertaClientes(new Cliente(
							txtDni.getText().toString(),
							txtNombre.getText().toString(),
							txtApellidos.getText().toString(),
							txtDireccion.getText().toString(),	
							txtEmail.getText().toString(),	
							txtTelf.getText().toString(),
							txtNotas.getText().toString()
							));
					//Actualiza la tabla para ver el nuevo registro
					updateTable();
					
					//Limpia el contenido de las cajas
					txtDni.setText("");				
					txtNombre.setText("");
					txtApellidos.setText("");
					txtDireccion.setText("");
					txtEmail.setText("");
					txtTelf.setText("");
					txtNotas.setText("");

				} catch (SQLException e1) {
					System.out.println("Falla al insertar Admin");
					JOptionPane.showMessageDialog(null, "Ha habido un error al insertar Admin revise los campos");	
					e1.printStackTrace();
				}
			}
		});
		btnInsertar.setActionCommand("Insertar");
		btnInsertar.setBounds(630, 220, 119, 23);
		contentPanel.add(btnInsertar);
	}

	public void btnModifica() {
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLCliente conCli = new SQLCliente();

				// Obtenemos el primer dato del registro seleccionado
				if (table.getSelectedRow() != -1) {
					Cliente cli = cojerValores();

					cli.setNombre(txtNombre.getText().toString());
					cli.setApellidos(txtApellidos.getText().toString());
					cli.setDireccion(txtDireccion.getText().toString());
					cli.setEmail(txtEmail.getText().toString());
					cli.setTelf(txtTelf.getText().toString());
					cli.setNotas(txtNotas.getText().toString());
					
					//Confirmacion de editado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres editar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						if(txtDni.getText().toString().equals(cli.getDni())) {
							try {
								conCli.modificaClientes(cli);						
								updateTable();

							} catch (SQLException e1) {
								e1.printStackTrace();
							}						
						} else {
							JOptionPane.showMessageDialog(null, "EL DNI NO SE PUEDE CAMBIAR");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero EDITAR");
				}
			}
		});
		btnModificar.setActionCommand("OK");
		btnModificar.setBounds(630, 322, 119, 23);
		contentPanel.add(btnModificar);
	}

	public void btnElimina() {
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SQLCliente conCli = new SQLCliente();

				//Controla que tengas un registro seleccionado
				if (table.getSelectedRow() != -1) {

					//Confirmacion de borrado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres eliminar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						// Obtenemos el primer dato del registro seleccionado (El dni)
						Cliente cli = new Cliente((String) model.getValueAt(table.getSelectedRow(), 0));

						try {//---BORRA DE LA TABLA SQL
							model.removeRow(table.getSelectedRow());	
							conCli.deleteClientes(cli);

						} catch (SQLException e1) {
							System.out.println("No se ha podido eliminar el registro");
							e1.printStackTrace();
						}
					} 
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero");
				}
			}
		});
		btnEliminar.setActionCommand("Eliminar");
		btnEliminar.setBounds(630, 288, 119, 23);
		contentPanel.add(btnEliminar);
	}
	
	public void btnMostrarTodo() {
	

		JButton btnMostrarTodos = new JButton("Mostrar Todos");
		btnMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		btnMostrarTodos.setActionCommand("Mostrar Todos");
		btnMostrarTodos.setBounds(630, 11, 119, 23);
		contentPanel.add(btnMostrarTodos);
		
	}

	public void btnBuscar() {

		//CAJA TEXTO BUSCAR
		txtBuscar = new JTextField();
		txtBuscar.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtBuscar.setText("");
			}
		});
		txtBuscar.setForeground(Color.LIGHT_GRAY);
		txtBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuscar.setText("Buscar");
		txtBuscar.setBounds(630, 57, 88, 23);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		//BOTON BUSCAR
		JButton okButton = new JButton("");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnDni.isSelected()) {
					updateTableBuscar(txtBuscar.getText().toString(),"dni");
				} else if (rdbtnNombre.isSelected()) {
					updateTableBuscar(txtBuscar.getText().toString(),"nombre");
				} else if(rdbtnApellidos.isSelected()) {
					updateTableBuscar(txtBuscar.getText().toString(),"apellidos");
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona por que buscas");	
				}
			}
		});
		okButton.setIcon(new ImageIcon(ViewProveedor.class.getResource("/icon/detection.png")));
		okButton.setActionCommand("OK");
		okButton.setBounds(717, 57, 36, 23);
		contentPanel.add(okButton);
	}
		
	public void btnGrup() {
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setBounds(640, 94, 86, 14);
		contentPanel.add(lblBuscarPor);
		
		rdbtnDni = new JRadioButton("Dni");
		rdbtnDni.setBounds(663, 115, 109, 23);
		contentPanel.add(rdbtnDni);
		
		rdbtnNombre = new JRadioButton("Nombre");
		rdbtnNombre.setBounds(661, 145, 109, 23);
		contentPanel.add(rdbtnNombre);
		
		rdbtnApellidos = new JRadioButton("Apellidos");
		rdbtnApellidos.setBounds(661, 176, 109, 23);
		contentPanel.add(rdbtnApellidos);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnDni);
		group.add(rdbtnNombre);
		group.add(rdbtnApellidos);
	}
	
	//--------------------------------------------------------------------------------CAJAS TEXTO--------------------------------------------------------------------------------------	
	
	/*
	 * Crea las cajas de texto para insertar y modificar registros
	 */
	public void txtPanel() {

		txtDni = new JTextField();
		txtDni.setToolTipText("DNI");
		txtDni.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDni.setText("");
			}
		});
		txtDni.setForeground(Color.GRAY);
		txtDni.setText("DNI");
		txtDni.setColumns(10);
		txtDni.setBounds(31, 329, 86, 23);
		contentPanel.add(txtDni);
		
		txtNombre = new JTextField();
		txtNombre.setToolTipText("Nombre");
		txtNombre.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtNombre.setText("");
			}
		});
		txtNombre.setText("Nombre");
		txtNombre.setForeground(Color.GRAY);
		txtNombre.setColumns(10);
		txtNombre.setBounds(31, 363, 86, 23);
		contentPanel.add(txtNombre);
		
		txtApellidos = new JTextField();
		txtApellidos.setToolTipText("Apellidos");
		txtApellidos.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtApellidos.setText("");
			}
		});
		txtApellidos.setText("Apellidos");
		txtApellidos.setForeground(Color.GRAY);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(149, 329, 86, 23);
		contentPanel.add(txtApellidos);
		
		txtDireccion = new JTextField();
		txtDireccion.setToolTipText("Dirección");
		txtDireccion.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDireccion.setText("");
			}
		});
		txtDireccion.setText("Dirección");
		txtDireccion.setForeground(Color.GRAY);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(149, 363, 86, 23);
		contentPanel.add(txtDireccion);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("Email");
		txtEmail.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtEmail.setText("");
			}
		});
		txtEmail.setText("Email");
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setColumns(10);
		txtEmail.setBounds(269, 329, 86, 23);
		contentPanel.add(txtEmail);
		
		txtTelf = new JTextField();
		txtTelf.setToolTipText("Telf");
		txtTelf.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtTelf.setText("");
			}
		});
		txtTelf.setText("Telf");
		txtTelf.setForeground(Color.GRAY);
		txtTelf.setColumns(10);
		txtTelf.setBounds(269, 363, 86, 23);
		contentPanel.add(txtTelf);
		
		txtNotas = new JEditorPane();
		txtNotas.setToolTipText("Notas");
		txtNotas.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtNotas.setText("");
			}
		});
		txtNotas.setText("Notas...");
		txtNotas.setForeground(Color.GRAY);
		txtNotas.setBounds(390, 329, 218, 54);
		contentPanel.add(txtNotas);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewCliente.class.getResource("/icon/ortopedias(2).png")));
		label.setBounds(-215, -292, 604, 616);
		contentPanel.add(label);
	}
	
	//--------------------------------------------------------------------------------PANEL INFERIOR BOTONES---------------------------------------------------------------------------	
	
	/*
	 * Crea el panel inferior de botones con el boton cancelar que cierra el dialog
	 */
	public void btnPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnVerNota = new JButton("Ver Nota");
		btnVerNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
					Cliente cli = cojerValores();
					
					ViewNota windowNota = new ViewNota(cli);
					windowNota.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					windowNota.setVisible(true);
					//dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero");
				}
			}
		});
		btnVerNota.setActionCommand("Ver Historial");
		buttonPane.add(btnVerNota);
		
		JButton btnVerHistorial = new JButton("Ver Historial");
		btnVerHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
					Cliente cli = cojerValores();
					
					ViewPedido windowPedido = new ViewPedido(cli);
					windowPedido.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					windowPedido.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero");
				}
			}
		});
		buttonPane.add(btnVerHistorial);
		btnVerHistorial.setActionCommand("Ver Historial");
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setActionCommand("Cancelar");
			buttonPane.add(cancelButton);
		}
	}
	
	//--------------------------------------------------------------------------------OBTIENE INFO REGISTRO---------------------------------------------------------------------------	
	
	private Cliente cojerValores() {
		
		Cliente cli = new Cliente(
				(String) model.getValueAt(table.getSelectedRow(), 0),
				(String) model.getValueAt(table.getSelectedRow(), 1),
				(String) model.getValueAt(table.getSelectedRow(), 2),
				(String) model.getValueAt(table.getSelectedRow(), 3),
				(String) model.getValueAt(table.getSelectedRow(), 4),
				(String) model.getValueAt(table.getSelectedRow(), 5),
				(String) model.getValueAt(table.getSelectedRow(), 6)
				);
		
		return cli;
	}
	
}
