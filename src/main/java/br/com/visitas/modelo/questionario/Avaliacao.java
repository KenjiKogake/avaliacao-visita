package br.com.visitas.modelo.questionario;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import br.com.visitas.modelo.imovel.Imovel;
import br.com.visitas.modelo.pessoa.Cliente;
import br.com.visitas.modelo.pessoa.Corretor;

@Entity
public class Avaliacao {
	@Id
	private long codAvaliacao;
	private List<Questao> listQuestoes;
	private double valorAtual;
	private double valorSugerido;
	
	private Imovel imovel;
	private Date dataVisita;
	/* Id da visita que é gerado do sistema */
	private long codVisita;
	
	private Cliente cliente;
	private Corretor corretor;
	
	private String observacoes;
	
	private SimpleDateFormat formatDate;
	
	public Avaliacao() {
		formatDate = new SimpleDateFormat("dd/MM/yyyy");
	}

	public long getCodAvaliacao() {
		return codAvaliacao;
	}

	public void setCodAvaliacao(long codAvaliacao) {
		this.codAvaliacao = codAvaliacao;
	}

	public List<Questao> getListQuestoes() {
		return listQuestoes;
	}

	public void setListQuestoes(List<Questao> listQuestoes) {
		this.listQuestoes = listQuestoes;
	}

	public double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public double getValorSugerido() {
		return valorSugerido;
	}

	public void setValorSugerido(double valorSugerido) {
		this.valorSugerido = valorSugerido;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getDataVisita() {
		return formatDate.format(dataVisita);
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public long getCodVisita() {
		return codVisita;
	}

	public void setCodVisita(long codVisita) {
		this.codVisita = codVisita;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Corretor getCorretor() {
		return corretor;
	}

	public void setCorretor(Corretor corretor) {
		this.corretor = corretor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
}
