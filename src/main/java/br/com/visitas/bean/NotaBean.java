package br.com.visitas.bean;

import java.io.Serializable;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.visitas.DAO.DAO;
import br.com.visitas.ENUM.Status;
import br.com.visitas.filter.LazyData;
import br.com.visitas.filter.LazyList;
import br.com.visitas.modelo.questionario.Nota;

@Named
@ViewScoped
public class NotaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAO<Nota> dao;
	private LazyData<Nota> model;
	private LazyList<Nota> notas;

	private Nota filtroNota = new Nota();
	
	@Inject private Nota nota;
	
	public NotaBean() {
		this(null, null);
	}

	@Inject
	public NotaBean(DAO<Nota> dao, LazyList<Nota> notas) {
		this.dao = dao;
		this.notas = notas;
		
		atualizaTabela();
	}

	public void atualizaTabela(){
		model = new LazyData<Nota>(dao, notas, filtroNota, null);
	}
	
	public Nota getNota() {
		return nota;
	}
	
	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public LazyData<Nota> getModel() {
		return model;
	}

	public Nota getFiltroNota() {
		return filtroNota;
	}
	
	public void setFiltroNota(Nota filtroNota) {
		this.filtroNota = filtroNota;
	}
	
	public void salvar() {
		try {
			if(nota.getId() == null)
				dao.adiciona(nota);
			else 
				dao.atualiza(nota);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alterarStatus(){
		try {
			if(nota.getStatus() == Status.Ativo){
				dao.queryById("alteraStatusNota", nota.getId(), Status.Inativo);
			}else{
				dao.queryById("alteraStatusNota", nota.getId(), Status.Ativo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event){
		nota = (Nota) event.getObject();
	}
	
	public boolean isNovo(){
		return this.nota.getId() == null;
	}
	
	public boolean isAtivo(){
		if(!isNovo()) return this.nota.getStatus() == Status.Ativo;
		return false;
	}
	
	public void criaNovaNota(){
		this.nota = new Nota();
	}
	
	public void aplicaFiltroOneMenu(AjaxBehaviorEvent event){
		atualizaTabela();
	}
}
