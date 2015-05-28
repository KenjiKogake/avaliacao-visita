package br.com.visitas.modelo.questionario;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.visitas.modelo.DefaultEntity;

@Entity
public class Questao extends DefaultEntity{
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String questao;
	
	@ManyToOne(optional=false)
	private TipoQuestao tipo = new TipoQuestao();

//	@OneToMany(mappedBy = "questao", fetch = FetchType.LAZY)
//	private List<QuestoesDaAvaliacao> avaliacoes;

	public String getQuestao() {
		System.out.println("GetQuestao");
		return questao;
	}

	public void setQuestao(String questao) {
		System.out.println("SetQuestao");
		this.questao = questao;
	}

	public TipoQuestao getTipo() {
		return tipo;
	}

	public void setTipo(TipoQuestao tipo) {
		System.out.println("SetTipoQuestao");
		System.out.println(tipo.getTipo());
		this.tipo = tipo;
	}

//	public List<QuestoesDaAvaliacao> getAvaliacoes() {
//		return avaliacoes;
//	}

//	public void setAvaliacoes(List<QuestoesDaAvaliacao> avaliacoes) {
//		this.avaliacoes = avaliacoes;
//	}
	
}
