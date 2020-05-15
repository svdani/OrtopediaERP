package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class ViewLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewLogin dialog = new ViewLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewLogin() {
		setBounds(100, 100, 329, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
	
		txtPanel();
		titel();
		btnPanel();
			
	}
	
	public void txtPanel() {
		passwordField = new JPasswordField();
		passwordField.setBounds(92, 62, 86, 20);
		
		textField = new JTextField();
		textField.setBounds(92, 31, 86, 20);
		textField.setColumns(10);
			
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(198, 28, 105, 20);
		contentPanel.add(comboBox);
	}
	
	private void titel() {
		JLabel lblUsuario = new JLabel("usuario:");
		lblUsuario.setBounds(24, 31, 58, 14);
		contentPanel.setLayout(null);
		
		JLabel lblContrasea = new JLabel("contraseña:");
		lblContrasea.setBounds(24, 62, 58, 14);
		contentPanel.add(lblContrasea);
		contentPanel.add(lblUsuario);
		contentPanel.add(textField);
		contentPanel.add(passwordField);
	}
	
	public void btnPanel() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		{
			JButton okButton = new JButton("Iniciar Sesion");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
	}
	
	
}
