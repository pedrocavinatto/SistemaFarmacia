package Controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import Model.ConexaoBanco;
import Model.Venda;

public class ControleVenda {
	
	ConexaoBanco bd;
	
	public ControleVenda() {
		try {
			bd = new ConexaoBanco();
		} catch (Exception e){
			System.out.println("Erro de conexão com o banco de Dados!!");
		}
	}
	public void incluiVenda(JComboBox cbMetodoPagamento, JTextField tfDataVenda, JTable tbRemedios) {
		Venda venda = new Venda();
		//todo
		bd.inserirVenda(venda);
	}
	public List<Venda> listaVendas(){
		return bd.listarVendas();
	}
}
