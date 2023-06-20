package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ControleMarca;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastraMarca extends JFrame {

	private JPanel contentPane;
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
					CadastraMarca frame = new CadastraMarca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastraMarca() {
		ControleMarca controle_marca = new ControleMarca();	
		
		setTitle("Cadastro de marca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome_1 = new JLabel("Nome:");
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome_1.setBounds(83, 90, 122, 28);
		contentPane.add(lblNome_1);
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(218, 95, 299, 23);
		contentPane.add(tfNome);
		
		tfCnpj = new JTextField();
		tfCnpj.setColumns(10);
		tfCnpj.setBounds(218, 156, 299, 23);
		contentPane.add(tfCnpj);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCnpj.setBounds(83, 154, 122, 28);
		contentPane.add(lblCnpj);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(83, 214, 122, 28);
		contentPane.add(lblTelefone);
		
		tfTelefone = new JTextField();
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(218, 219, 299, 23);
		contentPane.add(tfTelefone);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle_marca.incluiMarca(tfNome, tfCnpj, tfTelefone);
				ListaMarca lista_marca = new ListaMarca();
				lista_marca.setVisible(true);
				setVisible(false);
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastrar.setBounds(211, 354, 196, 42);
		contentPane.add(btnCadastrar);
	}
}
