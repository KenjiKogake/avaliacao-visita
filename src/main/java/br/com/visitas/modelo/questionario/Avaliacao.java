package br.com.visitas.modelo.questionario;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Avaliacao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@ManyToOne
	private Imovel imovel;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Funcionario corretor;
	
	private double valorAtual;
	
	private double valorSugerido;
	
	private String observacoes;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="avaliacao")
	private List<QuestoesDaAvaliacao> questoes = new ArrayList<QuestoesDaAvaliacao>();

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

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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
	
	public void adicionaQuestao(Questao questao, Nota nota){
		this.questoes.add(new QuestoesDaAvaliacao(this, questao, nota));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
