package br.com.visitas.modelo.imovel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.visitas.modelo.questionario.Avaliacao;

@Entity
public class Imovel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private long codigoImovel;
	
	@OneToMany(mappedBy="imovel", fetch=FetchType.LAZY)
	private List<Avaliacao> avaliacoes;
	
	private String descricao;

	public long getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(long codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public List<Avaliacao> getAvaliacoes() {
		//Precisa implementar a consulta LAZY
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
