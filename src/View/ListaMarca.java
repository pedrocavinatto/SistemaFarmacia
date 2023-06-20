package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControleMarca;
import Model.Marca;

public class ListaMarca extends JFrame {

	private JPanel contentPane;
	private JTable tbMarcas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaMarca frame = new ListaMarca();
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
	public ListaMarca() {
		Map<Integer, Integer> rowId_marcaId = new HashMap<>();
		
		setTitle("Marcas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastraMarca = new JButton("Cadastrar marca");
		btnCadastraMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastraMarca cadastra_marca = new CadastraMarca();
				cadastra_marca.setVisible(true);
				setVisible(false);
			}
		});
		btnCadastraMarca.setBounds(10, 425, 187, 52);
		btnCadastraMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnCadastraMarca);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 600, 405);
		contentPane.add(scrollPane);
		
		tbMarcas = new JTable();
		scrollPane.setViewportView(tbMarcas);
		tbMarcas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "CNPJ", "Telefone"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tbMarcas.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbMarcas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbMarcas.getColumnModel().getColumn(2).setPreferredWidth(100);
		atualizaLista(tbMarcas, rowId_marcaId);
	}
	
	private void atualizaLista(JTable tbMarcas, Map<Integer, Integer> rowId_marcaId) {
		ControleMarca controle_marca = new ControleMarca();
		List<Marca> marcas = controle_marca.listaMarcas();
		
		int i = -1; //contador do ID linha
		for (Marca marca : marcas) {
			i++;
			DefaultTableModel model = (DefaultTableModel) tbMarcas.getModel();
			Object[] newRow = {marca.getNome(), marca.getCnpj(), marca.getTelefone(), marca.getId()};
			model.addRow(newRow);
			rowId_marcaId.put(i, marca.getId());
		}
	}
}
