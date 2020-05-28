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
import java.text.SimpleDateFormat;
import java.util.Date;

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
import Datos.SQLCliente;
import Datos.SQLPedido;
import Datos.SQLLiniaPedido;
import Datos.SQLMovimientoAlmacen;
import Modelo.Articulo;
import Modelo.Cliente;
import Modelo.Pedido;
import Modelo.LiniaPedido;
import Modelo.MovimientoAlmacen;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class ViewMovimientoAlmacen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtIdMovimiento;
	private JTextField txtIdArticulo;
	private JComboBox txtTipo;
	private JTextField txtUbicacion;
	private JSpinner txtCantidad;
	private JDateChooser dateFecha;
	private JDateChooser dateHasta;
	private JDateChooser dateDesde;
	
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnInsertar;
	private JButton btnModificar;
	
	private JRadioButton rdbtnTipo;
	private JRadioButton rdbtnFecha;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * inicia la aplicacion.
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
	 * Crea el dialog, con la tabla y diversos elementos llamando a las funciones para que estos se muestren.
	 */
	public ViewMovimientoAlmacen() {
		setTitle("ERP Ortopedias - Movimientos Almacen");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewMovimientoAlmacen.class.getResource("/icon/ortopedias.png")));
		setBounds(100, 100, 783, 498);
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
		model.addColumn("Id Movimiento");
		model.addColumn("Id Articulo");
		model.addColumn("Tipo Movimiento");
		model.addColumn("Ubicacion");
		model.addColumn("Fecha");
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
		btnGrup();
		calendarPanel();
		txtPanel();
		btnPanel();
	}
	
	/** 
	 * Crea el dialog relacionado con el Articulo.
	 * @param art
	 */
	public ViewMovimientoAlmacen(Articulo art) {
		setTitle("ERP Ortopedias - Movimientos Almacen");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\ortopedias.png"));
		setBounds(100, 100, 783, 498);
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
		model.addColumn("Id Movimiento");
		model.addColumn("Id Articulo");
		model.addColumn("Tipo Movimiento");
		model.addColumn("Ubicacion");
		model.addColumn("Fecha");
		model.addColumn("Cantidad");

		table.setModel(model);

		//---FUNCIONES TABLA 
		updateTableBuscar(art.getIdArticulo(), "idArticulo");
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
		btnGrup();
		calendarPanel();
		txtPanel();
		btnPanel();
	}
	
	//--------------------------------------------------------------------------------FUNCIONES TABLA----------------------------------------------------------------------------------	
	
	/**
	 * muestra todos los registros de la base de datos
	 */
	private void updateTable() {
		//---Actualiza valores que se muestran en la tabla
		SQLMovimientoAlmacen conMov = new SQLMovimientoAlmacen();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (MovimientoAlmacen mov:conMov.consultaMovimientoAlmacenes()) {
				model.addRow(new Object[] {
						mov.getIdMovimientoAlmacen(),
						mov.getIdArticulo(),
						mov.getTipoMovimiento(),
						mov.getUbicacion(),
						mov.getFecha(),
						mov.getCantidad()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla Movimientos Almacen");
		}		
	}	
	
	/**
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

				txtIdMovimiento.setEnabled(false);// BLOQUEA CAJA DE TEXTO CIF

				//------CAMBIA VALOR CAJAS TEXTO SEGUN EL REGISTRO SELECCIONADO
				txtIdMovimiento.setText(table.getValueAt(table.getSelectedRow(), 0).toString());				
				txtIdArticulo.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtUbicacion.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtCantidad.setValue(table.getValueAt(table.getSelectedRow(), 5));
				
				//CAMBIA VALOR DENTRO DE LA CAJA DE ELECCION SEGUN EL REGISTRO SELECCIONADO
				switch (table.getValueAt(table.getSelectedRow(), 2).toString()) {				
				case "Entrada":
					txtTipo.setSelectedItem("Entrada");
					break;
				case "Salida":
					txtTipo.setSelectedItem("Salida");
					break;
				default:
					break;
				}	

				//CAMBIA LA FECHA DENTRO DEL CALENDARIO SEGUN EL REGISTRO SELECCIONADO
				try {
					dateFecha.setDate(sdf.parse((String) table.getValueAt(table.getSelectedRow(), 4)));			
				} catch (ParseException e1) {
					System.out.println("ERROR AL CAMIARO CONTENIDO DEL CALENDARIO AL SELECCIONAR REGISTRO");
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * muestra los registros filtrados de la base de datos en la tabla
	 */
	private void updateTableBuscar(String registro, String filtro) {
		//---Actualiza valores que se muestran en la tabla
		
		SQLMovimientoAlmacen conMov = new SQLMovimientoAlmacen();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (MovimientoAlmacen mov:conMov.filtraMovimientoAlmacen(registro, filtro)) {
				model.addRow(new Object[] {
						mov.getIdMovimientoAlmacen(),
						mov.getIdArticulo(),
						mov.getTipoMovimiento(),
						mov.getUbicacion(),
						mov.getFecha(),
						mov.getCantidad()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla Movimientos Almacen");
		}		
	}	
	
	/**
	 * muestra los registros filtrados de la base de datos en la tabla
	 * @param desde
	 * @param hasta
	 */
	private void updateTableFiltrar(String desde, String hasta) {
		//---Actualiza valores que se muestran en la tabla
		
		SQLMovimientoAlmacen conMov = new SQLMovimientoAlmacen();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (MovimientoAlmacen mov:conMov.filtraMovimientoAlmacenFecha(desde, hasta)) {
				model.addRow(new Object[] {
						mov.getIdMovimientoAlmacen(),
						mov.getIdArticulo(),
						mov.getTipoMovimiento(),
						mov.getUbicacion(),
						mov.getFecha(),
						mov.getCantidad()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla Movimientos Almacen");
		}		
	}	
	
	//--------------------------------------------------------------------------------MENU---------------------------------------------------------------------------------------------	
	
	/**
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
	
	/**
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

				txtIdMovimiento.setEnabled(false);// BLOQUEA PRECIO PORQUE DERIVA DE LAS LINEAS DE COMANDA
				dateFecha.setEnabled(false);// BLOQUEA FECHA COJE DIRECTAMENTE LA FECHA DEL MOMENTO ACTUAL

				txtIdMovimiento.setText("Id Movimiento");				
				txtIdArticulo.setText("");
				txtUbicacion.setText("");
				txtTipo.setSelectedItem("Entrada");
				dateFecha.setDate(new Date());
				txtCantidad.setValue(0);;

			}
		});
		btnNuevo.setActionCommand("Nuevo");
		btnNuevo.setBounds(630, 304, 119, 23);
		contentPanel.add(btnNuevo);
	}

	/**
	 * Crea el boton Insertar que llamando al archivo SQL inserta un nuevo registro y luego limpia las cajas para no causar errores
	 */
	public void btnInserta() {

		btnInsertar = new JButton("Insertar");
		btnInsertar.setEnabled(false);// BLOQUEA BTN INSERTAR
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLMovimientoAlmacen conMov = new SQLMovimientoAlmacen();

				try {
					conMov.insertaMovimientoAlmacenes(new MovimientoAlmacen(
							txtIdArticulo.getText().toString(),
							txtTipo.getSelectedItem().toString(),
							txtUbicacion.getText().toString(),
							Integer.parseInt(txtCantidad.getValue().toString())
							));
					
					SQLArticulo conArt = new SQLArticulo();
					//SI HAYUNA ENTRADA DE ARTICULOS INCREMENTA EL STOCK
					if (txtTipo.getSelectedItem().toString().equals("Entrada")) {
						
						int stock = conArt.stockArticulos(new Articulo(txtIdArticulo.getText().toString()));
						stock = stock + Integer.parseInt(txtCantidad.getValue().toString());	
						conArt.modificaStockArticulos(new Articulo(txtIdArticulo.getText().toString(), stock));
					
						//SI HAYUNA SALIDA DE ARTICULOS REDUCE EL STOCK
					} else if (txtTipo.getSelectedItem().toString().equals("Salida")) {
						int stock = conArt.stockArticulos(new Articulo(txtIdArticulo.getText().toString()));
						stock = stock - Integer.parseInt(txtCantidad.getValue().toString());	
						conArt.modificaStockArticulos(new Articulo(txtIdArticulo.getText().toString(), stock));
					}
					
					
					//Actualiza la tabla para ver el nuevo registro
					updateTable();

					//Limpia el contenido de las cajas
					txtIdMovimiento.setText("");				
					txtIdArticulo.setText("");
					txtUbicacion.setText("");
					txtTipo.setSelectedItem("Entrada");
					dateFecha.setDate(null);

				} catch (SQLException e1) {
					System.out.println("Falla al insertar Movimiento Almacen");
					JOptionPane.showMessageDialog(null, "Ha habido un error al insertar Movimiento Almacen revise los campos");	
					e1.printStackTrace();
				}
			}
		});
		btnInsertar.setActionCommand("Insertar");
		btnInsertar.setBounds(630, 270, 119, 23);
		contentPanel.add(btnInsertar);
	}

	/**
	 * Crea el boton Modificar que llamando al archivo SQL modificar un  registro
	 */
	public void btnModifica() {

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SQLMovimientoAlmacen conMov = new SQLMovimientoAlmacen();
				boolean modificaStock = false;
				int diferencia = 0;
				
				// Obtenemos el primer dato del registro seleccionado
				if (table.getSelectedRow() != -1) {

					MovimientoAlmacen mov = new MovimientoAlmacen(
							(int) model.getValueAt(table.getSelectedRow(), 0),
							(String) model.getValueAt(table.getSelectedRow(), 1),
							(String) model.getValueAt(table.getSelectedRow(), 2),
							(String) model.getValueAt(table.getSelectedRow(), 3),
							(String) model.getValueAt(table.getSelectedRow(), 4),
							(int) model.getValueAt(table.getSelectedRow(), 5)
							);
					
					//COMPRUEVA SI HAY CAMBIO DE STOCK
					if (Integer.parseInt(txtCantidad.getValue().toString()) != (mov.getCantidad())) {
						modificaStock = true; 
						if(txtTipo.getSelectedItem().toString().equals("Entrada")) {
							diferencia = Integer.parseInt(txtCantidad.getValue().toString()) - mov.getCantidad();
							System.out.println("entrada" + diferencia);
						} else {
							diferencia = mov.getCantidad() - Integer.parseInt(txtCantidad.getValue().toString());
							System.out.println("salida" + diferencia);
						}
					}
					
					if (txtTipo.getSelectedItem().toString().equals(mov.getTipoMovimiento())) {							
					} else {//SI MODIFICAS ENTRADA POR SALIDA 
						modificaStock = true;
						switch (mov.getTipoMovimiento().toString()) {
						case "Entrada"://AHORA ES SALIDA
							diferencia = -1 *(2 * mov.getCantidad());
							break;
						case "Salida"://AHORA ES ENTRADA
							diferencia = 2 * mov.getCantidad();
							break;
						default:
							break;
						}					
					}
					
					//CAMBIA ATRIBUTOS DEL MOVIMIENTO
					mov.setIdArticulo(txtIdArticulo.getText().toString());
					mov.setTipoMovimiento(txtTipo.getSelectedItem().toString());
					mov.setUbicacion(txtUbicacion.getText().toString());
					mov.setCantidad(Integer.parseInt(txtCantidad.getValue().toString()));					
					
					//Confirmacion de editado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres editar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						if(Integer.parseInt(txtIdMovimiento.getText()) == (mov.getIdMovimientoAlmacen())) {//SI EL ID ES EL MISMO MODIFICA EL CONTENIDO
							try {
								conMov.modificaMovimientoAlmacenes(mov);
								if (modificaStock) {//DESPUES DE MODIFICAR EL MOVIMIENTO ACTUALIZA EL STOCK			
									
									SQLArticulo conArt = new SQLArticulo();
									
									int stock = conArt.stockArticulos(new Articulo(txtIdArticulo.getText().toString()));//RECOJE STOCK DE LA BD ARTICULO  							
									stock = stock + diferencia;	
									conArt.modificaStockArticulos(new Articulo(txtIdArticulo.getText().toString(), stock));//MODIFICA LA EL STOCK ARTICULO																	

								} 
								updateTable();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}						
						} else {
							JOptionPane.showMessageDialog(null, "EL ID MOVIMEINTO NO SE PUEDE CAMBIAR");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero para Modificar");
				}
			}
		});
		btnModificar.setActionCommand("OK");
		btnModificar.setBounds(630, 372, 119, 23);
		contentPanel.add(btnModificar);
	}

	/**
	 * Crea el boton Eliminar que al seleccioanr un registro lo elimina
	 */
	public void btnElimina() {

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLMovimientoAlmacen conMov = new SQLMovimientoAlmacen();

				//Controla que tengas un registro seleccionado
				if (table.getSelectedRow() != -1) {

					//Confirmacion de borrado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres eliminar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						// Obtenemos el primer dato del registro seleccionado (El Id Pedido)
						MovimientoAlmacen mov = new MovimientoAlmacen((int) model.getValueAt(table.getSelectedRow(), 0));
						
						try {//---BORRA DE LA TABLA SQL
							model.removeRow(table.getSelectedRow());	
							conMov.deleteMovimientoAlmacenes(mov);
							
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
		btnEliminar.setBounds(630, 338, 119, 23);
		contentPanel.add(btnEliminar);
	}
	
	/**
	 * Crea el boton para efectuar el evento de filtrado
	 */
	public void btnFiltrar() {
		
		JComboBox tipoBox = new JComboBox();
		tipoBox.setModel(new DefaultComboBoxModel(new String[] {"Entrada", "Salida"}));
		tipoBox.setBounds(707, 108, 50, 20);
		contentPanel.add(tipoBox);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnTipo.isSelected()) {
					updateTableBuscar(tipoBox.getSelectedItem().toString(),"tipoMovimiento");
				} else if (rdbtnFecha.isSelected()) {
					updateTableFiltrar(
							sdf.format(dateDesde.getDate()),
							sdf.format(dateHasta.getDate())
							);
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona por que buscas");	
				}
			}
		});
		btnFiltrar.setActionCommand("Filtrar");
		btnFiltrar.setBounds(630, 226, 119, 23);
		contentPanel.add(btnFiltrar);		
	}
	
	/**
	 * Crea el boton Mostrar Todos que al usarlo actualiza y muetra todos los registros
	 */
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

	/**
	 * Crea el boton Buscar que al usarlo busca los registros 
	 */
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
		txtBuscar.setText("Buscar Articulo");
		txtBuscar.setBounds(630, 48, 88, 23);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		//BOTON BUSCAR
		JButton okButton = new JButton("");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTableBuscar(txtBuscar.getText(), "idArticulo");
			}
		});
		okButton.setIcon(new ImageIcon(ViewProveedor.class.getResource("/icon/detection.png")));
		okButton.setActionCommand("OK");
		okButton.setBounds(717, 48, 36, 23);
		contentPanel.add(okButton);
	}
	
	//--------------------------------------------------------------------------------CALENDARIOS--------------------------------------------------------------------------------------	
	
	/**
	 * Crea los calendarios para seleccioanr fechas
	 */
	public void calendarPanel() {
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(618, 167, 86, 14);
		contentPanel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(618, 198, 86, 14);
		contentPanel.add(lblHasta);
		
		//CALENDARIO DE INSERTAR Y MODIFICAR
		dateFecha = new JDateChooser();
		dateFecha.setToolTipText("Fecha");
		dateFecha.setBounds(441, 377, 100, 23);
		contentPanel.add(dateFecha);
		
		//CALENDARIOS DE FILTRAR
		dateDesde = new JDateChooser();
		dateDesde.setToolTipText("Desde");
		dateDesde.setBounds(658, 167, 95, 20);
		contentPanel.add(dateDesde);
		
		dateHasta = new JDateChooser();
		dateHasta.setToolTipText("Hasta");
		dateHasta.setBounds(658, 195, 95, 20);
		contentPanel.add(dateHasta);
		
	}
	
	/**
	 * Crea los radioButton y los agrupa para inpedir la seleccion de mas de 1
	 */
	public void btnGrup() {
		
		JLabel lblBuscarPor = new JLabel("Filtrar por:");
		lblBuscarPor.setBounds(630, 86, 86, 14);
		contentPanel.add(lblBuscarPor);
		
		rdbtnTipo = new JRadioButton("Tipo");
		rdbtnTipo.setBounds(642, 107, 59, 23);
		contentPanel.add(rdbtnTipo);
		
		rdbtnFecha = new JRadioButton("Fecha");
		rdbtnFecha.setBounds(640, 137, 109, 23);
		contentPanel.add(rdbtnFecha);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnTipo);
		group.add(rdbtnFecha);
	}
	
	//--------------------------------------------------------------------------------CAJAS TEXTO--------------------------------------------------------------------------------------	
	
	/*
	 * Crea las cajas de texto para insertar y modificar registros
	 */
	public void txtPanel() {

		txtIdMovimiento = new JTextField();
		txtIdMovimiento.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdMovimiento.setText("");
			}
		});
		txtIdMovimiento.setToolTipText("ID Movimeinto");
		txtIdMovimiento.setForeground(Color.GRAY);
		txtIdMovimiento.setText("ID Movimeinto");
		txtIdMovimiento.setColumns(10);
		txtIdMovimiento.setBounds(30, 377, 86, 23);
		contentPanel.add(txtIdMovimiento);
		
		txtIdArticulo = new JTextField();
		txtIdArticulo.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdArticulo.setText("");
			}
		});
		txtIdArticulo.setToolTipText("ID Art\u00EDculo");
		txtIdArticulo.setText("ID Art\u00EDculo");
		txtIdArticulo.setForeground(Color.GRAY);
		txtIdArticulo.setColumns(10);
		txtIdArticulo.setBounds(132, 377, 86, 23);
		contentPanel.add(txtIdArticulo);
		
		txtUbicacion = new JTextField();
		txtUbicacion.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtUbicacion.setText("");
			}
		});
		txtUbicacion.setToolTipText("Ubicacion");
		txtUbicacion.setText("Ubicacion");
		txtUbicacion.setForeground(Color.GRAY);
		txtUbicacion.setColumns(10);
		txtUbicacion.setBounds(337, 377, 86, 23);
		contentPanel.add(txtUbicacion);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewCliente.class.getResource("/icon/ortopedias(2).png")));
		label.setBounds(-215, -292, 604, 616);
		contentPanel.add(label);
		
		txtTipo = new JComboBox();
		txtTipo.setModel(new DefaultComboBoxModel(new String[] {"Entrada", "Salida"}));
		txtTipo.setBounds(234, 377, 86, 23);
		contentPanel.add(txtTipo);	
		
		txtCantidad = new JSpinner();
		txtCantidad.setBounds(558, 377, 50, 23);
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
}
