package br.com.visitas.modelo.questionario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.visitas.modelo.imovel.Imovel;
import br.com.visitas.modelo.pessoa.Cliente;
import br.com.visitas.modelo.pessoa.Funcionario;

@Entity
public class Avaliacao{
	//	Não precisamos de código da avaliação, uma visita pode ter apenas uma avaliação, portanto será PK
	@Id
	private long id;

	@ManyToOne
	private Imovel imovel;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Funcionario corretor;
	
	private double valorAtual;
	private double valorSugerido;
	
	private Date dataVisita;
	
	private String observacoes;
	
	private SimpleDateFormat formatDate;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="avaliacao")
	private List<QuestoesDaAvaliacao> questoes = new ArrayList<QuestoesDaAvaliacao>();
	
	public Avaliacao() {
		formatDate = new SimpleDateFormat("dd/MM/yyyy");
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

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getCorretor() {
		return corretor;
	}

	public void setCorretor(Funcionario corretor) {
		this.corretor = corretor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<QuestoesDaAvaliacao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<QuestoesDaAvaliacao> questoes) {
		this.questoes = questoes;
	}

}
