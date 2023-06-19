package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.ControleMarca;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarMarca {

	private JFrame frmCadastroDeMarca;
	private JTextField tfNome;
	private JTextField tfCnpj;
	private JTextField tfTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarMarca window = new CadastrarMarca();
					window.frmCadastroDeMarca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastrarMarca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ControleMarca controle_marca = new ControleMarca();		
		
		frmCadastroDeMarca = new JFrame();
		frmCadastroDeMarca.setTitle("Cadastro de marca");
		frmCadastroDeMarca.setBounds(100, 100, 642, 499);
		frmCadastroDeMarca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frmCadastroDeMarca.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle_marca.incluiMarca(tfNome, tfCnpj, tfTelefone);
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastrar.setBounds(212, 385, 196, 42);
		panel.add(btnCadastrar);
		
		JLabel lblNome_1 = new JLabel("Nome:");
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome_1.setBounds(77, 121, 122, 28);
		panel.add(lblNome_1);
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(212, 126, 299, 23);
		panel.add(tfNome);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCnpj.setBounds(77, 185, 122, 28);
		panel.add(lblCnpj);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(77, 245, 122, 28);
		panel.add(lblTelefone);
		
		tfCnpj = new JTextField();
		tfCnpj.setColumns(10);
		tfCnpj.setBounds(212, 187, 299, 23);
		panel.add(tfCnpj);
		
		tfTelefone = new JTextField();
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(212, 250, 299, 23);
		panel.add(tfTelefone);
	}

}
