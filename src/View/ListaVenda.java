package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControleRemedio;
import Controller.ControleVenda;
import Model.Remedio;
import Model.Venda;

public class ListaVenda extends JFrame {

	private JPanel contentPane;
	private JTable tbVendas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaVenda frame = new ListaVenda();
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
	public ListaVenda() {
		Map<Integer, Integer> rowId_vendaId = new HashMap<>();
		
		setTitle("Vendas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastraVenda = new JButton("Cadastrar venda");
		btnCadastraVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastraVenda cadastra_venda = new CadastraVenda();
				cadastra_venda.setVisible(true);
				setVisible(false);
			}
		});
		btnCadastraVenda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastraVenda.setBounds(10, 407, 187, 52);
		contentPane.add(btnCadastraVenda);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 613, 387);
		contentPane.add(scrollPane);
		
		tbVendas = new JTable();
		tbVendas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E9todo de pagamento", "Data da venda", "Valor total"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbVendas.getColumnModel().getColumn(0).setPreferredWidth(116);
		scrollPane.setViewportView(tbVendas);
		atualizaLista(tbVendas, rowId_vendaId);
	}
	private void atualizaLista(JTable tbVendas, Map<Integer, Integer> rowId_vendaId) {
		ControleVenda controle_venda = new ControleVenda();
		List<Venda> vendas = controle_venda.listaVendas();
		
		int i = -1; //contador do ID linha
		for (Venda venda : vendas) {
			i++;
			DefaultTableModel model = (DefaultTableModel) tbVendas.getModel();
			Object[] newRow = {venda.getMetodoPagamento().toString(), venda.getDataVenda().toString(), venda.getValorTotal()};
			model.addRow(newRow);
			rowId_vendaId.put(i, venda.getId());
		}
	}
}
