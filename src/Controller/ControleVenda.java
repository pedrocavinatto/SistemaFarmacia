package Controller;

import java.util.List;

import javax.swing.JTextField;

import Model.ConexaoBanco;
import Model.Marca;
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
	public void incluiVenda(JTextField tfCodigoBarra, JTextField tfNome, Marca marca, JTextField tfValorCusto, JTextField tfValorVenda, JTextField tfQuantidade) {
		Venda venda = new Venda();
		//todo
		bd.inserirVenda(venda);
	}
	public List<Venda> listaVendas(){
		return bd.listarVendas();
	}
}
