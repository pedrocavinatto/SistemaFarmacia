package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBanco {
	private Connection conexao;
	
	public ConexaoBanco() throws Exception{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conexao = DriverManager.getConnection("jdbc:mysql://localhost/farmacia","root","");
		}catch (Exception e) {
			throw new Exception("Ocorreu um erro na conexão");
		}
	}
	public void fecharConexao() {
		try {
			this.conexao.close();
		}catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro no encerramento da conexão");
		}
	}
	public void liberar(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		}catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro na liberação do cursor");
		}
	}
	public void inserirRemedio(Remedio remedio) {
		PreparedStatement psInsert = null;
		try {
			psInsert = conexao.prepareStatement("INSERT INTO remedios "
			+ "(codigo_barras, nome, id_marca, data_producao, data_validade, valor_custo, valor_venda, quantidade) "
			+ "VALUES (?,?,?,?,?,?,?,?) ");
			psInsert.setString(1, remedio.getCodigoBarra());
			psInsert.setString(2, remedio.getNome());
			psInsert.setInt(3, remedio.getMarca().getId());
			psInsert.setString(4, remedio.getDataProducao().toString());
			psInsert.setString(5, remedio.getDataValidade().toString());
			psInsert.setBigDecimal(6, remedio.getValorCusto());
			psInsert.setBigDecimal(7, remedio.getValorVenda());
			psInsert.setInt(8, remedio.getQuantidade());
			psInsert.executeUpdate();
		}catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro na inserção do remédio");
		}finally {
			this.liberar(psInsert);
		}
	}
	public List<Remedio> listarRemedios(){
		List<Remedio> remedios = new ArrayList<Remedio>();
		PreparedStatement psSelect = null;
		try {
			psSelect = conexao.prepareStatement("SELECT * "
					+ "FROM remedios "
					+ "Order by id");
			ResultSet rs = psSelect.executeQuery();
			while (rs.next()) {
				remedios.add(new Remedio(rs.getInt("id"),
										 rs.getString("codigo_barras"),
										 rs.getString("nome"),
										 new Marca(rs.getInt("id_marca"), null, null, null),
										 rs.getDate("data_producao"),
										 rs.getDate("data_validade"),
										 rs.getBigDecimal("valor_custo"),
										 rs.getBigDecimal("valor_venda"),
										 rs.getInt("quantidade")
										 )
				);
			}
		}catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro na listagem dos remédios");
		}finally {
			this.liberar(psSelect);
		}
		return remedios;
	}
	public void inserirMarca(Marca marca) {
		PreparedStatement psInsert = null;
		try {
			psInsert = conexao.prepareStatement("INSERT INTO marcas "
			+ "(nome, cnpj, telefone) "
			+ "VALUES (?,?,?) ");
			psInsert.setString(1, marca.getNome());
			psInsert.setString(2, marca.getCnpj());
			psInsert.setString(3, marca.getTelefone());
			psInsert.executeUpdate();
		}catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro na inserção da marca");
		}finally {
			this.liberar(psInsert);
		}
	}
	public List<Marca> listarMarcas(){
		List<Marca> marcas = new ArrayList<Marca>();
		PreparedStatement psSelect = null;
		try {
			psSelect = conexao.prepareStatement("SELECT * "
					+ "FROM marcas "
					+ "Order by id");
			ResultSet rs = psSelect.executeQuery();
			while (rs.next()) {
				marcas.add(new Marca(rs.getInt("id"),
									 rs.getString("nome"),
									 rs.getString("cnpj"),
									 rs.getString("telefone")
									 )
				);
			}
		}catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro na listagem das marcas");
		}finally {
			this.liberar(psSelect);
		}
		return marcas;
	}
	public void inserirVenda(Venda venda) {
		PreparedStatement psInsert = null;
		try {
			psInsert = conexao.prepareStatement("INSERT INTO vendas "
			+ "(id_metodo_pagamento, data_venda, valor_total) "
			+ "VALUES (?,?,?,?) "
			+ "RETURNING id");
			psInsert.setInt(1, venda.getMetodoPagamento().getId());
			psInsert.setDate(2, new java.sql.Date(venda.getDataVenda().getTime()));
			psInsert.setBigDecimal(3, venda.getValorTotal());
			ResultSet rs = psInsert.executeQuery();
			int generatedId = -1;
	        if (rs.next()) {
	            generatedId = rs.getInt("id");
	        }
	        if (generatedId > -1) {
	        	for (Remedio remedio : venda.getRemedios()) {
	        		psInsert = null;
	        		psInsert = conexao.prepareStatement("INSERT INTO venda_remedios "
        			+ "(id_venda, id_remedio, valor_venda, quantidade) "
        			+ "VALUES (?,?,?,?) ");
        			psInsert.setInt(1, generatedId);
        			psInsert.setInt(2, remedio.getId());
        			psInsert.setBigDecimal(3, remedio.getValorVenda()); //valor unitario do remedio
        			psInsert.setInt(4, remedio.getQuantidade());
        			psInsert.executeUpdate();
	        	}
	        }
		}catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro na inserção da marca");
		}finally {
			this.liberar(psInsert);
		}
	}
	public List<Venda> listarVendas(){
		List<Venda> vendas = new ArrayList<Venda>();
		PreparedStatement psSelect = null;
		try {
			psSelect = conexao.prepareStatement("SELECT * "
					+ "FROM vendas "
					+ "Order by id");
			ResultSet rs = psSelect.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setMetodoPagamento(MetodoPagamento.getMetodoPagamentoById(rs.getInt("id_metodo_pagamento")));
				venda.setDataVenda(rs.getDate("data_venda"));
				venda.setValorTotal(rs.getBigDecimal("valor_total"));
				vendas.add(venda);
			}
			for (Venda venda : vendas) {
				psSelect = null;
				rs = null;
				psSelect = conexao.prepareStatement("SELECT * "
						+ "FROM venda_remedios "
						+ "WHERE id = ?");
				psSelect.setInt(1, venda.getId());
				rs = psSelect.executeQuery();
				List<Remedio> remedios = new ArrayList<>();
				while (rs.next()) {
					Remedio remedio = new Remedio();
	                remedio.setId(rs.getInt("id_remedio"));
	                remedio.setValorVenda(rs.getBigDecimal("valor_venda"));
	                remedio.setQuantidade(rs.getInt("quantidade"));
	                remedios.add(remedio);
				}
				
				venda.setRemedios(remedios.toArray(new Remedio[0]));
			}
		}catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro na listagem das vendas");
		}finally {
			this.liberar(psSelect);
		}
		return vendas;
	}
}
