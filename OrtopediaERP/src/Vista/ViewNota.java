package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Cliente;

import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.JTextPane;
import java.awt.List;
import java.awt.Canvas;

public class ViewNota extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Cliente cli;

	/**
	 * Create the dialog.
	 */
	public ViewNota(Cliente cli) {
		this.cli = cli;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(cli.getNotas());
		textPane.setEditable(false);
		textPane.setBounds(10, 11, 414, 240);
		contentPanel.add(textPane);
		


	}
}
