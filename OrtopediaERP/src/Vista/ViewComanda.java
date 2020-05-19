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

import Datos.SQLCliente;
import Datos.SQLComanda;
import Datos.SQLLiniaComanda;
import Modelo.Cliente;
import Modelo.Comanda;
import Modelo.LiniaComanda;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ViewComanda extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private JTable table;
	private JTextField txtBuscar;
	private JTextField txtIdComanda;
	private JTextField txtIdCliente;
	private JTextField txtPrecioTotal;
	private JEditorPane txtNotas;
	private JComboBox txtEstado;
	
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnInsertar;
	private JButton btnModificar;
	private JButton btnVerNota;
	
	private JRadioButton rdbtnEstado;
	private JRadioButton rdbtnFechaInicio;
	private JRadioButton rdbtnFechaLimite;
	
	private JDateChooser dateFechaInicio;
	private JDateChooser dateFechaLimite;
	private JDateChooser dateHasta;
	private JDateChooser dateDesde;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
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
		
		table = new JTable();		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row,int column) {
				//Todas las celdas en false
				return false;
			}
		};

		//----INTRODUCE NOMBRE COLUMNAS TABLA DESDE SQL
		model.addColumn("Id Comanda");
		model.addColumn("Id Cliente");
		model.addColumn("Precio Total");
		model.addColumn("Estado");
		model.addColumn("Fecha Inicio");
		model.addColumn("Fecha Limite");
		model.addColumn("Descripción");

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
	 * Crea el dialog relacionado con el Cliente.
	 */
	public ViewComanda(Cliente cli) {
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
		
		table = new JTable();		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row,int column) {
				//Todas las celdas en false
				return false;
			}
		};

		//----INTRODUCE NOMBRE COLUMNAS TABLA DESDE SQL
		model.addColumn("Id Comanda");
		model.addColumn("Id Cliente");
		model.addColumn("Precio Total");
		model.addColumn("Estado");
		model.addColumn("Fecha Inicio");
		model.addColumn("Fecha Limite");
		model.addColumn("Descripción");

		table.setModel(model);
		
		//---FUNCIONES TABLA
		updateBusca(cli.getDni());
		//updateTable();
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

	private void updateTable() {
		//---Actualiza valores que se muestran en la tabla
		SQLComanda conCom = new SQLComanda();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Comanda com:conCom.consultaComandas()) {
				model.addRow(new Object[] {
						com.getIdComanda(),
						com.getIdCliente(),
						com.getPrecioTotal(),
						com.getEstado(),
						com.getFechaInicio(),
						com.getFechaLimite(),
						com.getDescripcion()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla CLIENTE");
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

					txtPrecioTotal.setEnabled(true);// DESBLOQUEA PRECIO 
					dateFechaInicio.setEnabled(true);// DESBLOQUEA FECHA INICIO
					
					//------CAMBIA VALOR CAJAS TEXTO SEGUN EL REGISTRO SELECCIONADO
					txtIdComanda.setText(table.getValueAt(table.getSelectedRow(), 0).toString());				
					txtIdCliente.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtPrecioTotal.setText(table.getValueAt(table.getSelectedRow(), 2).toString());	
					txtNotas.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					
					//CAMBIA VALOR DENTRO DE LA CAJA DE ELECCION SEGUN EL REGISTRO SELECCIONADO
					switch (table.getValueAt(table.getSelectedRow(), 3).toString()) {				
					case "Pendiente":
						txtEstado.setSelectedItem("Pendiente");
						break;
					case "Curso":
						txtEstado.setSelectedItem("Curso");
						break;
					case "Finalizado":
						txtEstado.setSelectedItem("Finalizado");
						break;
					default:
						break;
					}
					
					//CAMBIA LA FECHA DENTRO DEL CALENDARIO SEGUN EL REGISTRO SELECCIONADO
					try {
						dateFechaInicio.setDate(sdf.parse((String) table.getValueAt(table.getSelectedRow(), 4)));
						dateFechaLimite.setDate(sdf.parse((String) table.getValueAt(table.getSelectedRow(), 5)));
					} catch (ParseException e1) {
						System.out.println("ERROR AL CAMIARO CONTENIDO DEL CALENDARIO AL SELECCIONAR REGISTRO");
						e1.printStackTrace();
					}

				}
			});
		}

	private void updateBusca(String cli) {
		//---Actualiza valores que se muestran en la tabla
		SQLComanda conCom = new SQLComanda();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Comanda com:conCom.buscaComandas(cli)) {
				model.addRow(new Object[] {
						com.getIdComanda(),
						com.getIdCliente(),
						com.getPrecioTotal(),
						com.getEstado(),
						com.getFechaInicio(),
						com.getFechaLimite(),
						com.getDescripcion()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al actualizar la tabla CLIENTE");
		}		
	}	

	private void updateFiltrar( String estado) {
		//---Actualiza valores que se muestran en la tabla
		SQLComanda conCom = new SQLComanda();
		try {			

			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Comanda com:conCom.filtraComandasEstado(estado)) {
				model.addRow(new Object[] {
						com.getIdComanda(),
						com.getIdCliente(),
						com.getPrecioTotal(),
						com.getEstado(),
						com.getFechaInicio(),
						com.getFechaLimite(),
						com.getDescripcion()
				});	
			}	
		} catch (Exception e) {
			System.out.println("Error al buscar y actualizar la tabla ADMIN");
		}		
	}
	
	private void updateFecha(String column, String desde, String hasta) {
		//---Actualiza valores que se muestran en la tabla
		SQLComanda conCom = new SQLComanda();
		try {			

			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Comanda com:conCom.filtraComandasFecha(column, desde, hasta)) {
				model.addRow(new Object[] {
						com.getIdComanda(),
						com.getIdCliente(),
						com.getPrecioTotal(),
						com.getEstado(),
						com.getFechaInicio(),
						com.getFechaLimite(),
						com.getDescripcion()
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

				txtPrecioTotal.setEnabled(false);// BLOQUEA PRECIO PORQUE DERIVA DE LAS LINEAS DE COMANDA
				dateFechaInicio.setEnabled(false);// BLOQUEA FECHA INICIO COJE DIRECTAMENTE LA FECHA DELMOMENTO ACTUAL
				
				txtIdComanda.setText("ID Comanda");				
				txtIdCliente.setText("");
				txtPrecioTotal.setText("Precio Total");
				txtEstado.setSelectedItem("Pendiente");
				dateFechaInicio.setDate(new Date());
				dateFechaLimite.setCalendar(null);
				
				txtNotas.setText("");
			}
		});
		btnNuevo.setActionCommand("Nuevo");
		btnNuevo.setBounds(630, 343, 119, 23);
		contentPanel.add(btnNuevo);
	}
	
	public void btnInserta() {
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.setEnabled(false);// BLOQUEA BTN INSERTAR
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLComanda conCom = new SQLComanda();

				try {

					conCom.insertaComandas(new Comanda(
							txtIdCliente.getText().toString(),
							txtEstado.getSelectedItem().toString(),	
							sdf.format(dateFechaLimite.getDate()).toString(),	
							txtNotas.getText().toString()
							));
					//Actualiza la tabla para ver el nuevo registro
					updateTable();
					
					//Limpia el contenido de las cajas
					txtIdComanda.setText("");				
					txtIdCliente.setText("");
					txtPrecioTotal.setText("");
					txtEstado.setSelectedItem("Pendiente");
					txtNotas.setText("");

				} catch (SQLException e1) {
					System.out.println("Falla al insertar Comanda");
					JOptionPane.showMessageDialog(null, "Ha habido un error al insertar Comanda revise los campos");	
					e1.printStackTrace();
				}
			}
		});
		btnInsertar.setActionCommand("Insertar");
		btnInsertar.setBounds(630, 309, 119, 23);
		contentPanel.add(btnInsertar);
	}
	
	public void btnModifica() {

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SQLComanda conCom = new SQLComanda();

				// Obtenemos el primer dato del registro seleccionado
				if (table.getSelectedRow() != -1) {
					//COMPRUEBA QUE EL VALOR PRECIO SEA DOUBLE
					if( isDouble(txtPrecioTotal.getText()) == true) {
						Comanda com = new Comanda(
								(int) model.getValueAt(table.getSelectedRow(), 0),
								(String) model.getValueAt(table.getSelectedRow(), 1),
								(double) model.getValueAt(table.getSelectedRow(), 2),
								(String) model.getValueAt(table.getSelectedRow(), 3),
								(String) model.getValueAt(table.getSelectedRow(), 4),
								(String) model.getValueAt(table.getSelectedRow(), 5),
								(String) model.getValueAt(table.getSelectedRow(), 6)
								);

						com.setIdCliente(txtIdCliente.getText().toString());
						com.setPrecioTotal(Double.parseDouble(txtPrecioTotal.getText()));
						com.setEstado(txtEstado.getSelectedItem().toString());
						//com.setFechaInicio(sdf.format(dateFechaInicio.getDate()).toString());
						com.setFechaLimite(sdf.format(dateFechaLimite.getDate()).toString());
						com.setDescripcion(txtNotas.getText().toString());

						//Confirmacion de editado
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres editar el registro seleccionado?","Warning",dialogButton);			

						if(dialogResult == 0){

							if(Integer.parseInt(txtIdComanda.getText()) == (com.getIdComanda())) {
								try {
									conCom.modificaComandas(com);						
									updateTable();

								} catch (SQLException e1) {
									e1.printStackTrace();
								}						
							} else {
								JOptionPane.showMessageDialog(null, "EL ID COMANDA NO SE PUEDE CAMBIAR");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ha habido un error al insertar Articulo revise los campos\n Precio y Stock puede que no sean valores numericos.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero para EDITAR");
				}
			}
		});
		btnModificar.setActionCommand("OK");
		btnModificar.setBounds(630, 411, 119, 23);
		contentPanel.add(btnModificar);
	}
	
	public void btnElimina() {

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLComanda conCom = new SQLComanda();
				SQLLiniaComanda conLin = new SQLLiniaComanda();
				
				//Controla que tengas un registro seleccionado
				if (table.getSelectedRow() != -1) {

					//Confirmacion de borrado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres eliminar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						// Obtenemos el primer dato del registro seleccionado (El Id Comanda)
						Comanda com = new Comanda((int) model.getValueAt(table.getSelectedRow(), 0));
						LiniaComanda linCom = new LiniaComanda((int) model.getValueAt(table.getSelectedRow(), 0),0);
								
						try {//---BORRA DE LA TABLA SQL
							model.removeRow(table.getSelectedRow());	
							conCom.deleteComandas(com);
							conLin.deleteComandas(linCom);

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
		btnEliminar.setBounds(630, 377, 119, 23);
		contentPanel.add(btnEliminar);
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
		txtBuscar.setText("Buscar Cliente");
		txtBuscar.setBounds(630, 48, 88, 23);
		contentPanel.add(txtBuscar);
		txtBuscar.setColumns(10);
	
		//BOTON BUSCAR
		JButton okButton = new JButton("");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBusca(txtBuscar.getText().toString());
			}
		});
		okButton.setIcon(new ImageIcon("C:\\Users\\w7\\git\\OrtopediaERP\\OrtopediaERP\\icon\\detection.png"));
		okButton.setActionCommand("OK");
		okButton.setBounds(717, 48, 36, 23);
		contentPanel.add(okButton);
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
	
	public void btnFiltrar() {
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pendiente", "Curso", "Finalizado"}));
		comboBox.setBounds(697, 108, 60, 20);
		contentPanel.add(comboBox);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				if (rdbtnEstado.isSelected()) {//SI SELECCIONAS ESTADO ENVIA LA ELECCION DE ESTADO POR LA QUE FILTRAS
					updateFiltrar(comboBox.getSelectedItem().toString());
					
				} else if (rdbtnFechaInicio.isSelected()) {//SI SELECCIONAS FECHA INICIO ENVIA LAS FECHAS ENTRE LAS QUE FILTRAS
					updateFecha(
							"fechaInicio",
							sdf.format(dateDesde.getDate()).toString(), 
							sdf.format(dateHasta.getDate()).toString()
							);
				} else if(rdbtnFechaLimite.isSelected()) {//SI SELECCIONAS FECHA LIMITE ENVIA LAS FECHAS ENTRE LAS QUE FILTRAS
					updateFecha(
							"fechaLimite",
							sdf.format(dateDesde.getDate()).toString(), 
							sdf.format(dateHasta.getDate()).toString()
							);
				} else {
					JOptionPane.showMessageDialog(null, "Selecciona por que buscas");	
				}
			}
		});
		btnFiltrar.setActionCommand("Filtrar");
		btnFiltrar.setBounds(630, 257, 119, 23);
		contentPanel.add(btnFiltrar);
		
		
	}
	
	public void btnGrup() {

		JLabel lblBuscarPor = new JLabel("Filtrar por:");
		lblBuscarPor.setBounds(618, 83, 86, 14);
		contentPanel.add(lblBuscarPor);
		
		rdbtnEstado = new JRadioButton("Estado");
		rdbtnEstado.setBounds(625, 107, 64, 23);
		contentPanel.add(rdbtnEstado);
		
		rdbtnFechaInicio = new JRadioButton("Fecha Inicio");
		rdbtnFechaInicio.setBounds(625, 138, 114, 23);
		contentPanel.add(rdbtnFechaInicio);
		
		rdbtnFechaLimite = new JRadioButton("Fecha Limite");
		rdbtnFechaLimite.setBounds(625, 169, 114, 23);
		contentPanel.add(rdbtnFechaLimite);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEstado);
		group.add(rdbtnFechaInicio);
		group.add(rdbtnFechaLimite);
	}
	
	//--------------------------------------------------------------------------------CALENDARIOS--------------------------------------------------------------------------------------	
	
	public void calendarPanel() {
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(618, 198, 86, 14);
		contentPanel.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(618, 229, 86, 14);
		contentPanel.add(lblHasta);
		
		//CALENDARIOS DE FILTRAR
		dateDesde = new JDateChooser();
	 	dateDesde.setBounds(658, 198, 95, 20);
		contentPanel.add (dateDesde);
		
		dateHasta = new JDateChooser();
		dateHasta.setBounds(658, 226, 95, 20);
		contentPanel.add(dateHasta);
		
		//CALENDARIOS DE INSERTAR Y MODIFICAR
		dateFechaInicio = new JDateChooser();
		dateFechaInicio.setBounds(254, 377, 109, 23);
		contentPanel.add(dateFechaInicio);
		
		dateFechaLimite = new JDateChooser();
		dateFechaLimite.setBounds(254, 411, 109, 23);
		contentPanel.add(dateFechaLimite);
	}
	
	//--------------------------------------------------------------------------------CAJAS TEXTO--------------------------------------------------------------------------------------	

	public void txtPanel() {

		txtIdComanda = new JTextField();
		txtIdComanda.setEditable(false);
		txtIdComanda.setForeground(Color.GRAY);
		txtIdComanda.setText("ID Comanda");
		txtIdComanda.setColumns(10);
		txtIdComanda.setBounds(30, 377, 86, 23);
		contentPanel.add(txtIdComanda);
		
		txtIdCliente = new JTextField();
		txtIdCliente.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtIdCliente.setText("");
			}
		});
		txtIdCliente.setText("ID Cliente");
		txtIdCliente.setForeground(Color.GRAY);
		txtIdCliente.setColumns(10);
		txtIdCliente.setBounds(30, 411, 86, 23);
		contentPanel.add(txtIdCliente);
		
		txtPrecioTotal = new JTextField();
		txtPrecioTotal.setText("Precio Total");
		txtPrecioTotal.setForeground(Color.GRAY);
		txtPrecioTotal.setColumns(10);
		txtPrecioTotal.setBounds(144, 377, 86, 23);
		contentPanel.add(txtPrecioTotal);
		
		txtNotas = new JEditorPane();
		txtNotas.addMouseListener(new MouseAdapter() {
			//AL HACER CLICK LIMPIA LA CAJA DE TEXTO 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtNotas.setText("");
			}
		});
		txtNotas.setText("Notas...");
		txtNotas.setForeground(Color.GRAY);
		txtNotas.setBounds(389, 377, 218, 54);
		contentPanel.add(txtNotas);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\w7\\Desktop\\ortopedias.png"));
		label.setBounds(-215, -292, 604, 616);
		contentPanel.add(label);
		
		txtEstado = new JComboBox();
		txtEstado.setModel(new DefaultComboBoxModel(new String[] {"Pendiente", "Curso", "Finalizado"}));
		txtEstado.setBounds(144, 412, 88, 20);
		contentPanel.add(txtEstado);
		
	}
	
	//--------------------------------------------------------------------------------PANEL INFERIOR BOTONES---------------------------------------------------------------------------	
	
	public void btnPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		//BOTON VER LINIAS DE COMANDA
		JButton	 btnVerLiniaComanda = new JButton("Ver Lineas Comanda");
		btnVerLiniaComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		//BOTON VER DESCRIPCION
		JButton btnVerNota = new JButton("Ver Descripcion");
		btnVerNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (table.getSelectedRow() != -1) {
					System.out.println("hola");
					SQLComanda conCom = new SQLComanda();
					// Obtenemos el primer dato del registro seleccionado
						Comanda com = new Comanda(
								(int) model.getValueAt(table.getSelectedRow(), 0),
								(String) model.getValueAt(table.getSelectedRow(), 1),
								(double) model.getValueAt(table.getSelectedRow(), 2),
								(String) model.getValueAt(table.getSelectedRow(), 3),
								(String) model.getValueAt(table.getSelectedRow(), 4),
								(String) model.getValueAt(table.getSelectedRow(), 5),
								(String) model.getValueAt(table.getSelectedRow(), 6)
								);
					
				ViewNota windowNota = new ViewNota(com);
				windowNota.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				windowNota.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero");
				}
			}
		});
		buttonPane.add(btnVerNota);
		buttonPane.add(btnVerLiniaComanda);
		btnVerLiniaComanda.setActionCommand("Ver Historial");
		
		//BOTON CANCELAR
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancelButton.setActionCommand("Cancelar");
		buttonPane.add(cancelButton);

		
		
	}


	//--------------------------------------------------------------------------------CONTROL ERRORES---------------------------------------------------------------------------	
	
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
