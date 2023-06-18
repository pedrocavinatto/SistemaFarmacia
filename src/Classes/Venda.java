package Classes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {
	private int id;
	private Remedio[] remedios;
	private MetodoPagamento metodo_pagamento;
	private LocalDate data_venda;
	private BigDecimal valor_total;
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Remedio[] getRemedios() {
        return remedios;
    }

    public void setRemedios(Remedio[] remedios) {
        this.remedios = remedios;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodo_pagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }

    public LocalDate getDataVenda() {
        return data_venda;
    }

    public void setDataVenda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }

    public BigDecimal getValorTotal() {
        return valor_total;
    }

    public void setValorTotal(BigDecimal valor_total) {
        this.valor_total = valor_total;
    }
}
