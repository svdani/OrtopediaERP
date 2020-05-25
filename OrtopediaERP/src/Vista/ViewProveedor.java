package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Datos.SQLCliente;
import Datos.SQLProveedor;
import Modelo.Cliente;
import Modelo.Proveedor;

public class ViewProveedor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtIdProveedor;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtTelf;
	
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnInsertar;
	private JButton btnModificar;
	
	private JRadioButton rdbtnIdProveedor;
	private JRadioButton rdbtnNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewProveedor dialog = new ViewProveedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el dialog.
	 */
	public ViewProveedor() {
		setTitle("ERP Ortopedias - Proveedores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewProveedor.class.getResource("/icon/ortopedias.png")));
		setBounds(100, 100, 783, 486);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 23, 578, 329);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row,int column) {
				//Todas las celdas en false
				return false;
			}
		};

		//----INTRODUCE NOMBRE COLUMNAS TABLA DESDE SQL
		model.addColumn("Id Proveedor");
		model.addColumn("Nombre");
		model.addColumn("Email");
		model.addColumn("Telf");


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
		SQLProveedor conPro = new SQLProveedor();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Proveedor pro:conPro.consultaProveedors()) {
				model.addRow(new Object[] {
						pro.getIdProveedor(),
						pro.getNombre(),
						pro.getEmail(),
						pro.getTelf()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla Proveedor");
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

					txtIdProveedor.setEnabled(false);// BLOQUEA CAJA DE TEXTO CIF

					//------CAMBIA VALOR CAJAS TEXTO SEGUN EL REGISTRO SELECCIONADO
					txtIdProveedor.setText(table.getValueAt(table.getSelectedRow(), 0).toString());				
					txtNombre.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtEmail.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					txtTelf.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					
				}
			});
		}
	
	private void updateTableBuscar(String registro, String filtro) {
		
		//---Actualiza valores que se muestran en la tabla
		SQLProveedor conPro = new SQLProveedor();
		try {			

			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Proveedor pro:conPro.buscaProveedores(registro, filtro)) {
				model.addRow(new Object[] {
						pro.getIdProveedor(),
						pro.getNombre(),
						pro.getEmail(),
						pro.getTelf()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla Proveedor");
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
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewCliente windowCliente = new ViewCliente();
				windowCliente.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				windowCliente.setVisible(true);
				dispose();
		
			}
		});
		mnNewMenu.add(mntmCliente);
		
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

				txtIdProveedor.setEnabled(true);// DESBLOQUEA CAJA DE TEXTO DNI

				txtIdProveedor.setText("");				
				txtNombre.setText("");
				txtEmail.setText("");
				txtTelf.setText("");
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
				SQLProveedor conPro = new SQLProveedor();

				try {

					conPro.insertaProveedors(new Proveedor(
							txtIdProveedor.getText().toString(),
							txtNombre.getText().toString(),
							txtEmail.getText().toString(),	
							txtTelf.getText().toString()
							));
					//Actualiza la tabla para ver el nuevo registro
					updateTable();
					
					//Limpia el contenido de las cajas
					txtIdProveedor.setText("");				
					txtNombre.setText("");
					txtEmail.setText("");
					txtTelf.setText("");

				} catch (SQLException e1) {
					System.out.println("Falla al insertar Admin");
					JOptionPane.showMessageDialog(null, "Ha habido un error al insertar Proveedor revise los campos");	
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
				SQLProveedor conPro = new SQLProveedor();

				// Obtenemos el primer dato del registro seleccionado
				if (table.getSelectedRow() != -1) {
					Proveedor pro = cojerValores();
					
					pro.setNombre(txtNombre.getText().toString());
					pro.setEmail(txtEmail.getText().toString());
					pro.setTelf(txtTelf.getText().toString());
					
					//Confirmacion de editado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres editar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						if(txtIdProveedor.getText().toString().equals(pro.getIdProveedor())) {
							try {
								conPro.modificaProveedors(pro);						
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
				SQLProveedor conPro = new SQLProveedor();

				//Controla que tengas un registro seleccionado
				if (table.getSelectedRow() != -1) {

					//Confirmacion de borrado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres eliminar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						// Obtenemos el primer dato del registro seleccionado (El dni)
						Proveedor pro = new Proveedor((String) model.getValueAt(table.getSelectedRow(), 0));

						try {//---BORRA DE LA TABLA SQL
							model.removeRow(table.getSelectedRow());	
							conPro.deleteProveedors(pro);

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
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnIdProveedor.isSelected()) {
					updateTableBuscar(txtBuscar.getText().toString(),"idProveedor");
				} else if (rdbtnNombre.isSelected()) {
					updateTableBuscar(txtBuscar.getText().toString(),"nombre");
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
		
		rdbtnIdProveedor = new JRadioButton("Id Proveedor");
		rdbtnIdProveedor.setBounds(663, 115, 109, 23);
		contentPanel.add(rdbtnIdProveedor);
		
		rdbtnNombre = new JRadioButton("Nombre");
		rdbtnNombre.setBounds(661, 145, 109, 23);
		contentPanel.add(rdbtnNombre);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnIdProveedor);
		group.add(rdbtnNombre);
	}
	
	//--------------------------------------------------------------------------------CAJAS TEXTO--------------------------------------------------------------------------------------	
	
	/*
	 * Crea las cajas de texto para insertar y modificar registros
	 */
	public void txtPanel() {

		txtIdProveedor = new JTextField();
		txtIdProveedor.setToolTipText("ID Proveedor");
		txtIdProveedor.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdProveedor.setText("");
			}
		});
		txtIdProveedor.setForeground(Color.GRAY);
		txtIdProveedor.setText("ID Proveedor");
		txtIdProveedor.setColumns(10);
		txtIdProveedor.setBounds(30, 363, 86, 23);
		contentPanel.add(txtIdProveedor);
		
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
		txtNombre.setBounds(151, 363, 86, 23);
		contentPanel.add(txtNombre);
		
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
		txtEmail.setBounds(274, 363, 86, 23);
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
		txtTelf.setBounds(396, 363, 86, 23);
		contentPanel.add(txtTelf);
		
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
		
		JButton btnModificar_4 = new JButton("Ver Art\u00EDculos");
		btnModificar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtenemos el primer dato del registro seleccionado
				if (table.getSelectedRow() != -1) {
					Proveedor pro = cojerValores();
					
					ViewArticulo windowArticulo = new ViewArticulo(pro);
					windowArticulo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					windowArticulo.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero ver sus Articulos");
				}
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
	
	//--------------------------------------------------------------------------------OBTIENE INFO REGISTRO---------------------------------------------------------------------------	

	private Proveedor cojerValores() {

		Proveedor pro = new Proveedor(
				(String) model.getValueAt(table.getSelectedRow(), 0),
				(String) model.getValueAt(table.getSelectedRow(), 1),
				(String) model.getValueAt(table.getSelectedRow(), 2),
				(String) model.getValueAt(table.getSelectedRow(), 3)
				);

		return pro;
	}
}
