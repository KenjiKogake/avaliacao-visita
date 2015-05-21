package br.com.visitas.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.visitas.DAO.DAO;
import br.com.visitas.filter.FilterTable;
import br.com.visitas.filter.LazyData;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.questionario.TipoQuestao;

@Named
@RequestScoped
public class TipoQuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<TipoQuestao> dao;
	
	private LazyData<TipoQuestao> model;
	
	@Inject private TipoQuestao tipoQuestao;
	
	private TipoQuestao filtroTipoQuestao = new TipoQuestao();
	
	public TipoQuestaoBean() {
		this(null, null, null);
	}

	@Inject
	public TipoQuestaoBean(DAO<TipoQuestao> dao, LazyList<TipoQuestao> imoveis, FilterTable filtro) {
		this.dao = dao;
		
		model = new LazyData<TipoQuestao>(dao, filtroTipoQuestao, imoveis, filtro);
	}

	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}
	
	public LazyData<TipoQuestao> getModel() {
		return model;
	}
	
	public TipoQuestao getFiltroTipoQuestao() {
		return filtroTipoQuestao;
	}
	
	public void setFiltroTipoQuestao(TipoQuestao filtroTipoQuestao) {
		this.filtroTipoQuestao = filtroTipoQuestao;
	}

	public void salvar() {
		try {
			dao.adiciona(tipoQuestao);
			tipoQuestao = new TipoQuestao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
