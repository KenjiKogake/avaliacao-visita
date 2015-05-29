package br.com.visitas.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

import br.com.visitas.DAO.DAO;
import br.com.visitas.filter.LazyData;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.questionario.Questao;
import br.com.visitas.modelo.questionario.TipoQuestao;

@Named
@RequestScoped
public class QuestaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Questao> dao;
	@Inject DAO<TipoQuestao> daoTipo;

	@Inject
	private Questao questao;

	private Questao filtroQuestao = new Questao();
	private LazyData<Questao> model;
	private LazyList<Questao> imoveis;
	private Map<String, Object> filtrosAdicionais = new HashMap<String, Object>();
	
	private InputText inputQuestao = new InputText();
	private SelectOneMenu selectTipoQuestao = new SelectOneMenu();
	
	public InputText getInputQuestao() {
		return inputQuestao;
	}

	public void setInputQuestao(InputText inputQuestao) {
		this.inputQuestao = inputQuestao;
	}

	public SelectOneMenu getSelectTipoQuestao() {
		return selectTipoQuestao;
	}

	public void setSelectTipoQuestao(SelectOneMenu selectTipoQuestao) {
		this.selectTipoQuestao = selectTipoQuestao;
	}

	public QuestaoBean() {
		this(null, null);
	}

	@Inject
	public QuestaoBean(DAO<Questao> dao, LazyList<Questao> imoveis) {
		this.dao = dao;
		this.imoveis = imoveis;
		
		atualizaTabela();
	}
	
	public void atualizaTabela(){
		try {
			System.out.println(selectTipoQuestao.getValue());
			filtroQuestao.setTipo((TipoQuestao) selectTipoQuestao.getValue());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			System.out.println(inputQuestao.getValue());
			filtroQuestao.setQuestao((String) inputQuestao.getValue());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		if(filtroQuestao.getTipo() != null) filtrosAdicionais.put("tipo", filtroQuestao.getTipo());
		
		model = new LazyData<Questao>(dao, imoveis, filtroQuestao, filtrosAdicionais);
	}
	
	public void salvar() {
		try {
			if(questao.getId() == null){
				dao.adiciona(questao);
			}else{
				dao.atualiza(questao);
			}
			questao = new Questao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TipoQuestao> getTiposQuestao() {
		return daoTipo.listaTodos();
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public LazyData<Questao> getModel() {
		return model;
	}

	public void onRowSelect(SelectEvent event) {
		this.questao = (Questao) event.getObject();
	}
	
	public Questao getFiltroQuestao() {
		return filtroQuestao;
	}
	
	public void setFiltroQuestao(Questao filtroQuestao) {
		this.filtroQuestao = filtroQuestao;
	}
	
	public void aplicaFiltroOneMenu(AjaxBehaviorEvent event){
		atualizaTabela();
	}
}
