package br.com.visitas.modelo.imovel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Imovel {
	@Id
	private long codigoImovel;

	public long getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(long codigoImovel) {
		this.codigoImovel = codigoImovel;
	}
}
