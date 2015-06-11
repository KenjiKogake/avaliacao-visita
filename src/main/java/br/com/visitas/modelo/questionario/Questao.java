package br.com.visitas.modelo.questionario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import br.com.visitas.ENUM.Status;
import br.com.visitas.modelo.DefaultEntity;

@NamedQueries({
	@NamedQuery(name="desativaPorTipoQuestao", query="UPDATE Questao q SET q.status = :pStatus WHERE q.tipo.id = :pId")
})

@Entity
public class Questao extends DefaultEntity{
	private static final long serialVersionUID = 1L;

	private String questao;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status = Status.Ativo;
	
	@ManyToOne(optional=false)
	private TipoQuestao tipo = new TipoQuestao();

	@OneToMany(mappedBy = "questao", fetch = FetchType.LAZY)
	private List<QuestoesDaAvaliacao> avaliacoes;

	public String getQuestao() {
		return questao;
	}

	public void setQuestao(String questao) {
		this.questao = questao;
	}

	public TipoQuestao getTipo() {
		return tipo;
	}

	public void setTipo(TipoQuestao tipo) {
		this.tipo = tipo;
	}

	public List<QuestoesDaAvaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<QuestoesDaAvaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
