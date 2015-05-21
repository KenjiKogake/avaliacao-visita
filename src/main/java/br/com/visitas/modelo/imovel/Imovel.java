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
	private Long id;
	
	@OneToMany(mappedBy="imovel", fetch=FetchType.LAZY)
	private List<Avaliacao> avaliacoes;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
//	public List<Avaliacao> getAvaliacoes() {
//		//Precisa implementar a consulta LAZY
//		return avaliacoes;
//	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	@Override
	public String toString() {
		return this.getId().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Imovel other = (Imovel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
