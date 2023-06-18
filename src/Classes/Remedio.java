package Classes;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Remedio {
	private int id;
	private String codigo_barra; //usar UNIQUE no SQL
	private String nome;
	private Marca marca;
	private LocalDate data_producao;
	private LocalDate data_validade;
	private BigDecimal valor_custo;
	private BigDecimal valor_venda;
	private int quantidade;
	
	public Remedio(int id, String codigo_barra, String nome, Marca marca, LocalDate data_producao, LocalDate data_validade, BigDecimal valor_custo, BigDecimal valor_venda, int quantidade) {
		this.id = id;
		this.codigo_barra = codigo_barra;
		this.nome = nome;
		this.marca = marca;
		this.data_producao = data_producao;
		this.data_validade = data_validade;
		this.valor_custo = valor_custo;
		this.valor_venda = valor_venda;
	 	this.quantidade = quantidade;
	}
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoBarra() {
        return codigo_barra;
    }

    public void setCodigoBarra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return marca;
    }
    
    public void setMarca(int id, String nome, String cnpj, String telefone) {
    	Marca marca = new Marca(id, nome, cnpj, telefone);
        this.marca = marca;
    }
    
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public LocalDate getDataProducao() {
        return data_producao;
    }

    public void setDataProducao(LocalDate data_producao) {
        this.data_producao = data_producao;
    }

    public LocalDate getDataValidade() {
        return data_validade;
    }

    public void setDataValidade(LocalDate data_validade) {
        this.data_validade = data_validade;
    }

    public BigDecimal getValorCusto() {
        return valor_custo;
    }

    public void setValorCusto(BigDecimal valor_custo) {
        this.valor_custo = valor_custo;
    }

    public BigDecimal getValorVenda() {
        return valor_venda;
    }

    public void setValorVenda(BigDecimal valor_venda) {
        this.valor_venda = valor_venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
