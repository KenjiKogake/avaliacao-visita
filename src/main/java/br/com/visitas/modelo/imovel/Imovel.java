package br.com.visitas.modelo.imovel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.visitas.modelo.questionario.Avaliacao;

@Entity
public class Imovel {
	@Id
	private long codigoImovel;
	
	@OneToMany(mappedBy="imovel", fetch=FetchType.LAZY)
	private List<Avaliacao> avaliacoes;

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
	
}
