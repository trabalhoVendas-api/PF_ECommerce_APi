package br.com.serratec.ecommerce.dto;

public class ItemPedidoDTO {
    private Long produtoId;
    private Integer quantidade;
    private Double desconto;
    private Double preco;

    /*public ItemPedidoDTO(Long produtoId, Integer quantidade, Double desconto, Double preco) {
		super();
		this.produtoId = produtoId;
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.preco = preco;
	}*/

	public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
