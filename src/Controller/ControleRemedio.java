package Controller;

import java.math.BigDecimal;

import javax.swing.JTextField;
import Classes.ConexaoBanco;
import Classes.Marca;
import Classes.Remedio;
import java.util.List;

public class ControleRemedio {
	
	ConexaoBanco bd;
	
	public ControleRemedio() {
		try {
			bd = new ConexaoBanco();
		} catch (Exception e){
			System.out.println("Erro de conexão com o banco de Dados!!");
		}
	}
	public void incluiRemedio(JTextField tfCodigoBarra, JTextField tfNome, Marca marca, JTextField tfValorCusto, JTextField tfValorVenda, JTextField tfQuantidade) {
		Remedio remedio = new Remedio();
		remedio.setCodigoBarra(tfCodigoBarra.getText());
		remedio.setNome(tfNome.getText());
		remedio.setMarca(marca);
		//data de producao
		//data de vencimento
		remedio.setValorCusto(new BigDecimal(tfValorCusto.getText()));
		remedio.setValorVenda(new BigDecimal(tfValorVenda.getText()));
		remedio.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
		bd.inserirRemedio(remedio);
	}
	public List<Remedio> listaRemedios(){
		return bd.listarRemedios();
	}
}
