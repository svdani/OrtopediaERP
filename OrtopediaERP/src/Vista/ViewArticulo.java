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

import Datos.SQLArticulo;
import Datos.SQLCliente;
import Modelo.Articulo;
import Modelo.Cliente;
import Modelo.Proveedor;

public class ViewArticulo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtIdArticulo;
	private JTextField txtIdProveedor;
	private JTextField txtPrecio;
	private JTextField txtNombre;
	private JTextField txtStock;
	
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnInsertar;
	private JButton btnModificar;
	
	private JRadioButton rdbtnIdArticulo;
	private JRadioButton rdbtnNombre;
	private JRadioButton rdbtnProveedor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewArticulo dialog = new ViewArticulo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el dialog.
	 */
	public ViewArticulo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\ortopedias.png"));
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
		model.addColumn("Id Articulo");
		model.addColumn("Id Proveedor");
		model.addColumn("Nombre");
		model.addColumn("Precio");
		model.addColumn("Stock");

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
	
	/**
	 * Crea el dialog relacionado con el Proveedor.
	 */
	public ViewArticulo(Proveedor pro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\ortopedias.png"));
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
		model.addColumn("Id Articulo");
		model.addColumn("Id Proveedor");
		model.addColumn("Nombre");
		model.addColumn("Precio");
		model.addColumn("Stock");

		table.setModel(model);

		//---FUNCIONES TABLA 
		//updateTable();
		updateTableBuscar(pro.getIdProveedor(), "idProveedor");
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

	private void updateTable() {
	
		//---Actualiza valores que se muestran en la tabla
		SQLArticulo conArt = new SQLArticulo();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Articulo art:conArt.consultaArticulos()) {
				model.addRow(new Object[] {
						art.getIdArticulo(),
						art.getIdProveedor(),
						art.getNombre(),
						art.getPrecio(),
						art.getStock()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla Articulo");
		}		
	}	
	
	public void selectRow() {
		//----FUNCION AL SELECCIONAR CAMPO

			table.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {

					btnModificar.setEnabled(true);// DESBLOQUEA BTN EDITAR
					btnEliminar.setEnabled(true);// DESBLOQUEA BTN ELIMINAR
					btnInsertar.setEnabled(false);// BLOQUEA BTN INSERTAR
					btnNuevo.setEnabled(true);// DESBLOQUEA BTN NUEVO

					txtIdArticulo.setEnabled(false);// BLOQUEA CAJA DE TEXTO CIF

					//------CAMBIA VALOR CAJAS TEXTO SEGUN EL REGISTRO SELECCIONADO
					txtIdArticulo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());				
					txtIdProveedor.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtNombre.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					txtPrecio.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					txtStock.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					
				}
			});
		}
	
	private void updateTableBuscar(String registro, String filtro) {
		//---Actualiza valores que se muestran en la tabla
	
		SQLArticulo conArt = new SQLArticulo();
		
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Articulo art:conArt.buscaArticulos(registro, filtro)) {
				model.addRow(new Object[] {
						art.getIdArticulo(),
						art.getIdProveedor(),
						art.getNombre(),
						art.getPrecio(),
						art.getStock()
				});	
			}
		} catch (Exception e) {
			System.out.println("Error al buscar y actualizar la tabla ADMIN");
		}		
	}

	//--------------------------------------------------------------------------------MENU---------------------------------------------------------------------------------------------	
	
	public void menuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
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

		JMenuItem mntmComanda = new JMenuItem("Comanda");
		mntmComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ViewComanda windowComanda = new ViewComanda();
				windowComanda.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				windowComanda.setVisible(true);
				dispose();

			}
		});
		mnNewMenu.add(mntmComanda);

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
	
	public void btnNuevo() {

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModificar.setEnabled(false);// BLOQUEA BTN EDITAR
				btnEliminar.setEnabled(false);// BLOQUEA BTN ELIMINAR
				btnInsertar.setEnabled(true);// DESBLOQUEA BTN INSERTAR
				btnNuevo.setEnabled(false);// BLOQUEA BTN NUEVO

				txtIdArticulo.setEnabled(true);// DESBLOQUEA CAJA DE TEXTO DNI

				txtIdArticulo.setText("");				
				txtIdProveedor.setText("");
				txtNombre.setText("");
				txtPrecio.setText("");
				txtStock.setText("");
			}
		});

		btnNuevo.setActionCommand("Nuevo");
		btnNuevo.setBounds(630, 254, 119, 23);
		contentPanel.add(btnNuevo);
	}

	public void btnInserta() {
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLArticulo conArt = new SQLArticulo();
				
				try {
					if( isDouble(txtPrecio.getText()) == true && isInteger(txtStock.getText()) == true) {
						
						conArt.insertaArticulos(new Articulo(
								txtIdArticulo.getText().toString(),
								txtIdProveedor.getText().toString(),
								txtNombre.getText().toString(),
								Double.parseDouble(txtPrecio.getText()),	
								Integer.parseInt(txtStock.getText())
								));
						//Actualiza la tabla para ver el nuevo registro
						updateTable();

						//Limpia el contenido de las cajas
						txtIdArticulo.setText("");				
						txtIdProveedor.setText("");
						txtNombre.setText("");
						txtPrecio.setText("");
						txtStock.setText("");
					}else{
						JOptionPane.showMessageDialog(null, "Ha habido un error al insertar Articulo revise los campos\n Precio y Stock puede que no sean valores numericos.");
					}
				} catch (SQLException e1) {
					System.out.println("Falla al insertar Articulo");
					JOptionPane.showMessageDialog(null, "Ha habido un error al insertar Articulo revise los campos");	
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
				SQLArticulo conArt = new SQLArticulo();

				// Obtenemos el primer dato del registro seleccionado
				if (table.getSelectedRow() != -1) {
					//COMPRUEBA QUE EL VALOR PRECIO SEA DOUBLE
					if( isDouble(txtPrecio.getText()) == true && isInteger(txtStock.getText()) == true) {
						Articulo art = new Articulo(
								(String) model.getValueAt(table.getSelectedRow(), 0),
								(String) model.getValueAt(table.getSelectedRow(), 1),
								(String) model.getValueAt(table.getSelectedRow(), 2),
								(double) model.getValueAt(table.getSelectedRow(), 3),
								(int) model.getValueAt(table.getSelectedRow(), 4)
								);

						art.setIdProveedor(txtIdProveedor.getText().toString());
						art.setNombre(txtNombre.getText().toString());
						art.setPrecio(Double.parseDouble(txtPrecio.getText()));
						art.setStock(Integer.parseInt(txtStock.getText()));


						//Confirmacion de editado
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres editar el registro seleccionado?","Warning",dialogButton);			

						if(dialogResult == 0){

							if(txtIdArticulo.getText().toString().equals(art.getIdArticulo())) {
								try {
									conArt.modificaArticulos(art);						
									updateTable();

								} catch (SQLException e1) {
									e1.printStackTrace();
								}						
							} else {
								JOptionPane.showMessageDialog(null, "EL ID ARTICULO NO SE PUEDE CAMBIAR");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ha habido un error al insertar Articulo revise los campos\n Precio y Stock puede que no sean valores numericos.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero para Modificar");
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
				SQLArticulo conArt = new SQLArticulo();

				//Controla que tengas un registro seleccionado
				if (table.getSelectedRow() != -1) {

					//Confirmacion de borrado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres eliminar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						// Obtenemos el primer dato del registro seleccionado (El id Articulo)
						Articulo art = new Articulo((String) model.getValueAt(table.getSelectedRow(), 0));

						try {//---BORRA DE LA TABLA SQL
							model.removeRow(table.getSelectedRow());	
							conArt.deleteArticulos(art);

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
				if (rdbtnIdArticulo.isSelected()) {
					updateTableBuscar(txtBuscar.getText().toString(),"idArticulo");
				} else if (rdbtnProveedor.isSelected()) {
					updateTableBuscar(txtBuscar.getText().toString(),"idProveedor");
				} else if(rdbtnNombre.isSelected()) {
					updateTableBuscar(txtBuscar.getText().toString(),"nombre");
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona por que buscas");	
				}
			}
		});
		okButton.setIcon(new ImageIcon("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\detection.png"));
		okButton.setActionCommand("OK");
		okButton.setBounds(717, 57, 36, 23);
		contentPanel.add(okButton);
	}
	
	public void btnGrup() {

		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setBounds(640, 94, 86, 14);
		contentPanel.add(lblBuscarPor);
		
		rdbtnIdArticulo = new JRadioButton("Referencia");
		rdbtnIdArticulo.setBounds(661, 115, 109, 23);
		contentPanel.add(rdbtnIdArticulo);
		
		rdbtnNombre = new JRadioButton("Nombre");
		rdbtnNombre.setBounds(661, 145, 109, 23);
		contentPanel.add(rdbtnNombre);
		
		rdbtnProveedor = new JRadioButton("Proveedor");
		rdbtnProveedor.setBounds(661, 177, 109, 23);
		contentPanel.add(rdbtnProveedor);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnIdArticulo);
		group.add(rdbtnNombre);
		group.add(rdbtnProveedor);
	}
	
	//--------------------------------------------------------------------------------CAJAS TEXTO--------------------------------------------------------------------------------------	

	public void txtPanel() {

		txtIdArticulo = new JTextField();
		txtIdArticulo.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdArticulo.setText("");
			}
		});
		txtIdArticulo.setHorizontalAlignment(SwingConstants.LEFT);
		txtIdArticulo.setForeground(Color.GRAY);
		txtIdArticulo.setText("Id Articulo");
		txtIdArticulo.setColumns(10);
		txtIdArticulo.setBounds(30, 363, 86, 23);
		contentPanel.add(txtIdArticulo);
		
		txtIdProveedor = new JTextField();
		txtIdProveedor.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdProveedor.setText("");
			}
		});
		txtIdProveedor.setText("Id Proveedor");
		txtIdProveedor.setForeground(Color.GRAY);
		txtIdProveedor.setColumns(10);
		txtIdProveedor.setBounds(151, 363, 86, 23);
		contentPanel.add(txtIdProveedor);
		
		txtPrecio = new JTextField();
		txtPrecio.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtPrecio.setText("");
			}
		});
		txtPrecio.setText("Precio");
		txtPrecio.setForeground(Color.GRAY);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(391, 363, 86, 23);
		contentPanel.add(txtPrecio);
		
		txtNombre = new JTextField();
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
		txtNombre.setBounds(269, 363, 86, 23);
		contentPanel.add(txtNombre);
		
		txtStock = new JTextField();
		txtStock.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtStock.setText("");
			}
		});
		txtStock.setText("Stock");
		txtStock.setForeground(Color.GRAY);
		txtStock.setColumns(10);
		txtStock.setBounds(522, 363, 86, 23);
		contentPanel.add(txtStock);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\w7\\Desktop\\ortopedias.png"));
		label.setBounds(-215, -292, 604, 616);
		contentPanel.add(label);
	}

	//--------------------------------------------------------------------------------PANEL INFERIOR BOTONES---------------------------------------------------------------------------	
	
	public void btnPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnHistorial = new JButton("Ver Historial");
		buttonPane.add(btnHistorial);
		btnHistorial.setActionCommand("Ver Historial");
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			cancelButton.setActionCommand("Cancelar");
			buttonPane.add(cancelButton);
		}
	}

	//--------------------------------------------------------------------------------CONTROL ERRORES---------------------------------------------------------------------------	
	
	public static boolean isInteger(String cadena) {

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}
	
	public static boolean isDouble(String cadena) {

		boolean resultado;

		try {
			Double.parseDouble(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}
}
