package connect.Classes;

public class FormaPagamento {
	private Long codigo; //Código
	private String pagamento;
	private Boolean prazo;
	private Boolean cartao;
	private Long codcaixa;
	private Boolean encaixa;
	private Boolean fechamento;
	private Boolean cheque;
	private String listapre;
	private Boolean naolancareceber;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public Boolean getPrazo() {
		return prazo;
	}
	public void setPrazo(Boolean prazo) {
		this.prazo = prazo;
	}
	public Boolean getCartao() {
		return cartao;
	}
	public void setCartao(Boolean cartao) {
		this.cartao = cartao;
	}
	public Long getCodcaixa() {
		return codcaixa;
	}
	public void setCodcaixa(Long codcaixa) {
		this.codcaixa = codcaixa;
	}
	public Boolean getEncaixa() {
		return encaixa;
	}
	public void setEncaixa(Boolean encaixa) {
		this.encaixa = encaixa;
	}
	public Boolean getFechamento() {
		return fechamento;
	}
	public void setFechamento(Boolean fechamento) {
		this.fechamento = fechamento;
	}
	public Boolean getCheque() {
		return cheque;
	}
	public void setCheque(Boolean cheque) {
		this.cheque = cheque;
	}
	public String getListapre() {
		return listapre;
	}
	public void setListapre(String listapre) {
		this.listapre = listapre;
	}
	public Boolean getNaolancareceber() {
		return naolancareceber;
	}
	public void setNaolancareceber(Boolean naolancareceber) {
		this.naolancareceber = naolancareceber;
	}
	
	
	
}
