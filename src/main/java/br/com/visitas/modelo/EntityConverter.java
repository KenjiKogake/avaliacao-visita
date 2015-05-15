package br.com.visitas.modelo;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="entityConverter", forClass=DefaultEntity.class)
public class EntityConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && !value.equals("")) return this.getAttributesFrom(component).get(value);

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && !value.equals("")){
			DefaultEntity entity = (DefaultEntity) value;
			
			if(entity.getId() != null){
				this.addAttribute(component, entity);
				
				if(entity.getId() != null){
					return String.valueOf(entity.getId());
				}
				return (String) value;
			}
		}
		return "";
	}
	
	private void addAttribute(UIComponent component, DefaultEntity entity) {
		this.getAttributesFrom(component).put(entity.getId().toString(), entity);
	}

	private Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes();
	}

}
