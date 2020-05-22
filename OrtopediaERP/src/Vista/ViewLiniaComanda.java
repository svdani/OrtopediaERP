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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Spliterator;

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

import com.toedter.calendar.JDateChooser;

import Datos.SQLArticulo;
import Datos.SQLComanda;
import Datos.SQLLiniaComanda;
import Datos.SQLMovimientoAlmacen;
import Modelo.Articulo;
import Modelo.Comanda;
import Modelo.LiniaComanda;
import Modelo.MovimientoAlmacen;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JLayeredPane;
import java.awt.Choice;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ViewLiniaComanda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtIdComanda;
	private JTextField txtIdLiniaComanda;
	private JTextField txtPrecio;
	private JSpinner txtCantidad;
	private JComboBox txtIdArticulo;
	private JComboBox txtTipo;
	private JComboBox txtEstado;
	private JComboBox boxTipo;
	private JComboBox boxEstado;
	
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnInsertar;
	private JButton btnModificar;
	
	private JRadioButton rdbtnEstado;
	private JRadioButton rdbtnTipo;
	
	private double precioLinia = 0;
	
	String[] listaArticulos;
	
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
	 * Crea el dialog.
	 */
	public ViewLiniaComanda() {
		setTitle("ERP Ortopedias - Linias Comanda");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\ortopedias.png"));
		setBounds(100, 100, 783, 532);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 23, 578, 343);
		contentPanel.add(scrollPane);
		
		table = new JTable();		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row,int column) {
				//Todas las celdas en false
				return false;
			}
		};

		//----INTRODUCE NOMBRE COLUMNAS TABLA DESDE SQL
		model.addColumn("Id Linia Comanda");
		model.addColumn("Id Comanda");
		model.addColumn("Id Articulo");
		model.addColumn("Estado");
		model.addColumn("Tipo");
		model.addColumn("Precio");
		model.addColumn("Cantidad");

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
		btnFiltrar();
		
		menuBar();
		updateArticulos();
		btnGrup();
		txtPanel();
		btnPanel();
	}
	
	public ViewLiniaComanda(Comanda com) {
		setTitle("ERP Ortopedias - Linias Comanda");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\ortopedias.png"));
		setBounds(100, 100, 783, 532);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 23, 578, 343);
		contentPanel.add(scrollPane);
		
		table = new JTable();		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row,int column) {
				//Todas las celdas en false
				return false;
			}
		};

		//----INTRODUCE NOMBRE COLUMNAS TABLA DESDE SQL
		model.addColumn("Id Linia Comanda");
		model.addColumn("Id Comanda");
		model.addColumn("Id Articulo");
		model.addColumn("Estado");
		model.addColumn("Tipo");
		model.addColumn("Precio");
		model.addColumn("Cantidad");

		table.setModel(model);
		
		//---FUNCIONES TABLA
		updateBusca(new LiniaComanda(com.getIdComanda(), 0));	
		selectRow();
		scrollPane.setViewportView(table);
		
		btnMostrarTodo();
		btnBuscar();
		btnNuevo();
		btnModifica();
		btnInserta();
		btnElimina();
		btnFiltrar();
		
		menuBar();
		updateArticulos();
		btnGrup();
		txtPanel();
		btnPanel();
		
		
		txtIdComanda.setText(String.valueOf(com.getIdComanda()));
	}
	
	//--------------------------------------------------------------------------------FUNCIONES TABLA----------------------------------------------------------------------------------	
	
	/*
	 * muestra todos los registros de la base de datos
	 */
	private void updateTable() {
		//---Actualiza valores que se muestran en la tabla
		SQLLiniaComanda conLin = new SQLLiniaComanda();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (LiniaComanda lin:conLin.consultaLiniaComandas()) {
				model.addRow(new Object[] {
						lin.getIdLiniaComanda(),
						lin.getIdComanda(),
						lin.getIdArticulo(),
						lin.getEstado(),
						lin.getTipo(),
						lin.getPrecio(),
						lin.getCantidad()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla Linia Comanda");
		}		
	}	
	 
	private void updateArticulos() {
		SQLArticulo conArt = new SQLArticulo();
		try {			
			
			int valor = Integer.parseInt(conArt.numArticulos());
			listaArticulos = new String[valor];
			int i = 0;
					
			for (Articulo art:conArt.consultaArticulos()) {
				
				listaArticulos[i] = art.getNombre() + "-" + art.getIdArticulo();
				//System.out.println(listaArticulos[i]);
				i++;		
			}
			
		} catch (Exception e) {
			System.out.println("Error al LLENAR LISTA DE ARTICULOS");
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

				txtIdLiniaComanda.setEnabled(false);// DESBLOQUEA PRECIO 
				txtIdComanda.setEnabled(false);// DESBLOQUEA PRECIO 
				
				//------CAMBIA VALOR CAJAS TEXTO SEGUN EL REGISTRO SELECCIONADO
				txtIdLiniaComanda.setText(table.getValueAt(table.getSelectedRow(), 0).toString());				
				txtIdComanda.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				
//				txtIdArticulo.setSelectedItem(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtIdArticulo.setSelectedItem("hoola");
				
				txtPrecio.setText(table.getValueAt(table.getSelectedRow(), 5).toString());		
				txtCantidad.setValue(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 6).toString()));
			
				//CAMBIA VALOR DENTRO DE LA CAJA ESTADO SEGUN EL REGISTRO SELECCIONADO
				switch (table.getValueAt(table.getSelectedRow(), 3).toString()) {				
				case "Pendiente":
					txtEstado.setSelectedItem("Pendiente");
					break;
				case "Finalizado":
					txtEstado.setSelectedItem("Finalizado");
					break;
				default:
					break;
				}
				
				//CAMBIA VALOR DENTRO DE LA CAJA TIPO SEGUN EL REGISTRO SELECCIONADO 
				switch (table.getValueAt(table.getSelectedRow(), 4).toString()) {				
				case "Montaje":
					txtEstado.setSelectedItem("Montaje");
					break;
				case "Reparacion":
					txtEstado.setSelectedItem("Reparacion");
					break;
				case "Envio":
					txtEstado.setSelectedItem("Envio");
					break;
				case "Envio y Montaje":
					txtEstado.setSelectedItem("Envio y Montaje");
					break;
				default:
					break;
				}
			}
		});
	}

	private void updateBusca(LiniaComanda linia) {
		//---Actualiza valores que se muestran en la tabla
		SQLLiniaComanda conLin = new SQLLiniaComanda();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (LiniaComanda lin:conLin.buscaLiniasComandas(linia)) {
				model.addRow(new Object[] {
						lin.getIdLiniaComanda(),
						lin.getIdComanda(),
						lin.getIdArticulo(),
						lin.getEstado(),
						lin.getTipo(),
						lin.getPrecio(),
						lin.getCantidad()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla Linia Comanda");
		}		
	}	
	
	private void updateFiltrar(String registro, String columna) {
		//---Actualiza valores que se muestran en la tabla
		SQLLiniaComanda conLin = new SQLLiniaComanda();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (LiniaComanda lin:conLin.filtraLiniasComandas(registro , columna)) {
				model.addRow(new Object[] {
						lin.getIdLiniaComanda(),
						lin.getIdComanda(),
						lin.getIdArticulo(),
						lin.getEstado(),
						lin.getTipo(),
						lin.getPrecio(),
						lin.getCantidad()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla Linia Comanda");
		}		
	}	
	
	//--------------------------------------------------------------------------------MENU---------------------------------------------------------------------------------------------	
	
	/*
	 * Crea el Menu y sus difernetes items que actuan como boton de reconduccion a otro dialog
	 */
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
		
		JMenuItem mntmArticulo = new JMenuItem("Articulo");
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

				txtIdLiniaComanda.setEnabled(false);// BLOQUEA ID LINEAS DE COMANDA PORQUE ES AUTONUMERICO
				//txtIdComanda.setEnabled(false);// BLOQUEA FECHA COJE DIRECTAMENTE LA FECHA DEL MOMENTO ACTUAL

				txtIdLiniaComanda.setText("Id Linia Comanda");		
				txtIdComanda.setText("Id Comanda");
				txtIdArticulo.setSelectedItem("");
				txtEstado.setSelectedItem("Pendiente");
				txtTipo.setSelectedItem("Envio");
				txtPrecio.setText("");
				txtCantidad.setValue(0);
			}
		});
		btnNuevo.setActionCommand("Nuevo");
		btnNuevo.setBounds(624, 329, 119, 23);
		contentPanel.add(btnNuevo);
	}
	
	/*
	 * Crea el boton Insertar que llamando al archivo SQL inserta un nuevo registro y luego limpia las cajas para no causar errores
	 */
	public void btnInserta() {

		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLLiniaComanda conLin = new SQLLiniaComanda();

				try {

					conLin.insertaLiniaComandas(new LiniaComanda(
							Integer.parseInt(txtIdComanda.getText().toString()),
							txtIdArticulo.getSelectedItem().toString(),	
							txtEstado.getSelectedItem().toString(),
							txtTipo.getSelectedItem().toString(),	
							Double.parseDouble(txtPrecio.getText().toString()),
							Integer.parseInt(txtCantidad.getValue().toString())
							
							));
					
					//AL MODIFICAR LA LINEA DEBE ACTUALIZARSE LA COMANDA 
					SQLComanda conCom = new SQLComanda();	
					conCom.modificaPrecioComanda(new Comanda(
							Integer.parseInt(txtIdComanda.getText().toString()),//Coje el valor de la comanda que inserta
							conLin.precioLinias(new LiniaComanda(Integer.parseInt(txtIdComanda.getText().toString()), 0 ))//calcula el valor de todas linias
							));
					
					//ACTUALIZA LA TABLA
					updateTable();
					
					//Limpia el contenido de las cajas
					txtEstado.setSelectedItem("Pendiente");
					txtEstado.setSelectedItem("Envio");
					txtPrecio.setText("0");				
					txtCantidad.setValue(0);
					
				} catch (SQLException e1) {
					System.out.println("Falla al insertar Comanda");
					JOptionPane.showMessageDialog(null, "Ha habido un error al insertar Comanda revise los campos");	
					e1.printStackTrace();
				}
			}
		});
		btnInsertar.setActionCommand("Insertar");
		btnInsertar.setBounds(624, 295, 119, 23);
		contentPanel.add(btnInsertar);
	}

	public void btnModifica() {

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLLiniaComanda conLin = new SQLLiniaComanda();

				// Obtenemos el primer dato del registro seleccionado
				if (table.getSelectedRow() != -1) {

					LiniaComanda lin = new LiniaComanda(//Crea una linia comanda apartir del registro seleccionado 
							(int) model.getValueAt(table.getSelectedRow(), 0),
							(int) model.getValueAt(table.getSelectedRow(), 1),
							(String) model.getValueAt(table.getSelectedRow(), 2),
							(String) model.getValueAt(table.getSelectedRow(), 3),
							(String) model.getValueAt(table.getSelectedRow(), 4),
							(double) model.getValueAt(table.getSelectedRow(), 5),
							(int) model.getValueAt(table.getSelectedRow(), 6)
							);			
					
					//cambia los valores de dicho registro
					lin.setIdArticulo(txtIdArticulo.getSelectedItem().toString());
					lin.setEstado(txtEstado.getSelectedItem().toString());
					lin.setTipo(txtTipo.getSelectedItem().toString());
					lin.setCantidad(Integer.parseInt(txtCantidad.getValue().toString()));
					lin.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));

					//Confirmacion de editado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres editar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						if(Integer.parseInt(txtIdLiniaComanda.getText()) == (lin.getIdLiniaComanda())) {
							try {
								//MODIFICA ELLOS VALORES DEL REGISTRO SELECCONADO 
								conLin.modificaLiniaComandas(lin);
								
								//AL MODIFICAR LA LINEA DEBE ACTUALIZARSE LA COMANDA 
								SQLComanda conCom = new SQLComanda();	
								conCom.modificaPrecioComanda(new Comanda(
										lin.getIdComanda(),//Coje el valor de la comanda que modifica
										conLin.precioLinias(lin)//calcula el valor de todas linias
										));
								
								//ACTUALIZA LA TABLA
								updateTable();

							} catch (SQLException e1) {
								e1.printStackTrace();
							}						
						} else {
							JOptionPane.showMessageDialog(null, "EL ID LINIA COMANDA NO SE PUEDE CAMBIAR");
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero para Modificar");
				}
			}
		});
		btnModificar.setActionCommand("OK");
		btnModificar.setBounds(624, 397, 119, 23);
		contentPanel.add(btnModificar);
	}
	
	public void btnElimina() {

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SQLLiniaComanda conLin = new SQLLiniaComanda();

				//Controla que tengas un registro seleccionado
				if (table.getSelectedRow() != -1) {

					//Confirmacion de borrado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres eliminar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						// Obtenemos el primer dato del registro seleccionado (El Id Comanda)
						LiniaComanda lin = new LiniaComanda((int) model.getValueAt(table.getSelectedRow(), 0));
						
						try {//---BORRA DE LA TABLA SQL
							model.removeRow(table.getSelectedRow());	
							conLin.deleteLiniaComandas(lin);
							
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
		btnEliminar.setBounds(624, 363, 119, 23);
		contentPanel.add(btnEliminar);

	}

	public void btnFiltrar() {
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnEstado.isSelected()) {//SI SELECCIONAS ESTADO ENVIA LA ELECCION DE ESTADO POR LA QUE FILTRAS
					updateFiltrar(boxEstado.getSelectedItem().toString(), "estado" );			
				} else if(rdbtnTipo.isSelected()) {//SI SELECCIONAS TIPO ENVIA LA ELECCION DE TIPO POR LA QUE FILTRAS
					updateFiltrar(boxTipo.getSelectedItem().toString(), "tipo" );
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona por que buscas");	
				}
			}
		});
		btnFiltrar.setActionCommand("Filtrar");
		btnFiltrar.setBounds(630, 230, 119, 23);
		contentPanel.add(btnFiltrar);

		boxEstado = new JComboBox();
		boxEstado.setModel(new DefaultComboBoxModel(new String[] {"Pendiente", "Finalizado"}));
		boxEstado.setBounds(670, 145, 79, 20);
		contentPanel.add(boxEstado);
		
		boxTipo = new JComboBox();
		boxTipo.setModel(new DefaultComboBoxModel(new String[] {"Montaje", "Reparacion", "Envio", "Envio y Montaje"}));
		boxTipo.setBounds(670, 194, 79, 20);
		contentPanel.add(boxTipo);
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
		txtBuscar.setText("Buscar Comanda");
		txtBuscar.setBounds(630, 50, 88, 23);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
	
		//BOTON BUSCAR
		JButton okButton = new JButton("");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (isInteger(txtBuscar.getText())) {				
					updateBusca(new LiniaComanda ( Integer.parseInt(txtBuscar.getText()), 0));
				}else {
					JOptionPane.showMessageDialog(null, "El id debe ser numerico");
				}
			}
		});
		okButton.setIcon(new ImageIcon("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\detection.png"));
		okButton.setActionCommand("OK");
		okButton.setBounds(717, 50, 36, 23);
		contentPanel.add(okButton);
	}
	
	public void btnGrup() {
		
		JLabel lblBuscarPor = new JLabel("Filtrar por:");
		lblBuscarPor.setBounds(628, 99, 86, 14);
		contentPanel.add(lblBuscarPor);
		
		rdbtnEstado = new JRadioButton("Estado");
		rdbtnEstado.setBounds(626, 120, 65, 23);
		contentPanel.add(rdbtnEstado);
		
		rdbtnTipo = new JRadioButton("Tipo");
		rdbtnTipo.setBounds(626, 169, 67, 23);
		contentPanel.add(rdbtnTipo);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEstado);
		group.add(rdbtnTipo);
	}
	
	//--------------------------------------------------------------------------------CAJAS TEXTO--------------------------------------------------------------------------------------	
	
	/*
	 * Crea las cajas de texto para insertar y modificar registros
	 */
	public void txtPanel() {

		txtIdComanda = new JTextField();
		txtIdComanda.setToolTipText("ID Comanda");
		txtIdComanda.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdComanda.setText("");
			}
		});
		txtIdComanda.setForeground(Color.GRAY);
		txtIdComanda.setText("ID Comanda");
		txtIdComanda.setColumns(10);
		txtIdComanda.setBounds(30, 411, 86, 23);
		contentPanel.add(txtIdComanda);
		
		txtIdLiniaComanda = new JTextField();
		txtIdLiniaComanda.setToolTipText("ID Linia Comanda");
		
		txtIdLiniaComanda.setText("ID Linia Comanda");
		txtIdLiniaComanda.setForeground(Color.GRAY);
		txtIdLiniaComanda.setColumns(10);
		txtIdLiniaComanda.setBounds(30, 377, 86, 23);
		contentPanel.add(txtIdLiniaComanda);
		
		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setToolTipText("Precio");
		txtPrecio.setText("0");
		txtPrecio.setForeground(Color.GRAY);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(463, 377, 86, 23);
		contentPanel.add(txtPrecio);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\w7\\Desktop\\ortopedias.png"));
		label.setBounds(-215, -292, 604, 616);
		contentPanel.add(label);
		
		txtEstado = new JComboBox();
		txtEstado.setModel(new DefaultComboBoxModel(new String[] {"Pendiente", "Finalizado"}));
		txtEstado.setToolTipText("Estado");
		txtEstado.setBounds(317, 377, 88, 20);
		contentPanel.add(txtEstado);
		
		txtTipo = new JComboBox();
		txtTipo.setModel(new DefaultComboBoxModel(new String[] {"Montaje", "Reparacion", "Envio", "Envio y Montaje"}));
		txtTipo.setToolTipText("Estado");
		txtTipo.setBounds(159, 413, 115, 20);
		contentPanel.add(txtTipo);
	
		txtIdArticulo = new JComboBox();
		txtIdArticulo.addActionListener(new ActionListener() {
			//aL SELECCIONAR UN ARTICULO DE LA LISTA COJE SU PRECIO DE LA BASE DE DATOS 
			public void actionPerformed(ActionEvent arg0) {
				SQLArticulo conArt = new SQLArticulo();
				String cadena[] = txtIdArticulo.getSelectedItem().toString().split("-");
				
				try {
					precioLinia = conArt.precioArticulos(new Articulo(cadena[1]));
					if ((int)txtCantidad.getValue()>0) {		
						txtPrecio.setText( String.valueOf(precioLinia * (int) txtCantidad.getValue()) );
					}else {
						txtPrecio.setText(String.valueOf(precioLinia) );
					}
				} catch (SQLException e) {
					System.out.println("No coje precio articulo");
					e.printStackTrace();
				}
			}
		});
		txtIdArticulo.setModel(new DefaultComboBoxModel(listaArticulos));
		txtIdArticulo.setToolTipText("Articulo");
		txtIdArticulo.setBounds(159, 377, 115, 20);
		contentPanel.add(txtIdArticulo);
		
		txtCantidad = new JSpinner();
		txtCantidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if((int)txtCantidad.getValue()<0) {
					//IMPIDE QUE PONGAS UNA CANTIAD NEGATIVA EVITA POSSIBLES ERROES
					txtCantidad.setValue(0);
				}
				//CAMBIA EL PRECIO DEPENDIENDO DE LA CANTIDAD POR EL PRECIO DEL ARTICULO
				txtPrecio.setText(String.valueOf(precioLinia * (int) txtCantidad.getValue()));
			}
		});
		
		txtCantidad.setToolTipText("Cantidad");
		txtCantidad.setBounds(346, 411, 59, 20);
		contentPanel.add(txtCantidad);
		
	}
	
	//--------------------------------------------------------------------------------PANEL INFERIOR BOTONES---------------------------------------------------------------------------	

	/*
	 * Crea el panel inferior de botones con el boton cancelar que cierra el dialog
	 */
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

	/*
	 * comprueba si en string enviado se puede pasasr a Integer, ayuda al control de errores
	 */
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
}
