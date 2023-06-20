package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Model.Marca;
import Model.MetodoPagamento;
import Model.Remedio;
import Model.Venda;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControleMarca;
import Controller.ControleVenda;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastraVenda extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTable tbRemedios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraVenda frame = new CadastraVenda();
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
	public CadastraVenda() {
		Map<Integer, Integer> rowId_remedioId = new HashMap<>();
		
		setTitle("Cadastro de venda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//todo
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastrar.setBounds(207, 392, 196, 42);
		contentPane.add(btnCadastrar);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(217, 83, 299, 23);
		contentPane.add(textField_1);
		
		JLabel lblDataDeVenda = new JLabel("Data de venda:");
		lblDataDeVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeVenda.setBounds(63, 78, 122, 28);
		contentPane.add(lblDataDeVenda);
		
		JLabel lblNome_1 = new JLabel("Método de pagamento:");
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome_1.setBounds(63, 40, 153, 28);
		contentPane.add(lblNome_1);
		
		JLabel lblRemdios = new JLabel("Remédios");
		lblRemdios.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemdios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRemdios.setBounds(63, 127, 453, 28);
		contentPane.add(lblRemdios);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(MetodoPagamento.values()));
		comboBox.setBounds(217, 46, 299, 21);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 165, 602, 217);
		contentPane.add(scrollPane);
		
		tbRemedios = new JTable();
		tbRemedios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Quantidade", "Nome", "Marca", "C\u00F3digo de barras", "Valor de venda", "Estoque"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Double.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tbRemedios);
		atualizaLista(tbRemedios, rowId_remedioId);
	}
	
	private void atualizaLista(JTable tbRemedios, Map<Integer, Integer> rowId_remedioId) {
		ControleVenda controle_venda = new ControleVenda();
		List<Venda> vendas = controle_venda.listaVendas();
		
		int i = -1; //contador do ID linha
		for (Venda venda : vendas) {
			for (Remedio remedio : venda.getRemedios()) {
				i++;
				DefaultTableModel model = (DefaultTableModel) tbRemedios.getModel();
				Object[] newRow = {0, remedio.getNome(), remedio.getMarca().getNome(), remedio.getCodigoBarra(), remedio.getValorVenda(), remedio.getQuantidade()};
				model.addRow(newRow);
				rowId_remedioId.put(i, remedio.getId());
			}
		}
	}
}
