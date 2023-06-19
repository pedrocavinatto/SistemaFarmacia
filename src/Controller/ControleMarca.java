package Controller;

import java.util.List;

import javax.swing.JTextField;

import Classes.ConexaoBanco;
import Classes.Marca;

public class ControleMarca {
	
	ConexaoBanco bd;
	
	public ControleMarca() {
		try {
			bd = new ConexaoBanco();
		} catch (Exception e){
			System.out.println("Erro de conexão com o banco de Dados!!");
		}
	}
	public void incluiMarca(JTextField tfNome, JTextField tfCnpj, JTextField tfTelefone) {
		Marca marca = new Marca();
		marca.setNome(tfNome.getText());
		marca.setCnpj(tfCnpj.getText());
		marca.setTelefone(tfTelefone.getText());
		bd.inserirMarca(marca);
	}
	public List<Marca> listaMarcas(){
		return bd.listarMarcas();
	}
	
}
