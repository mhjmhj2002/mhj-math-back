package com.mhj.math.util;

import java.util.ArrayList;
import java.util.List;

import com.mhj.math.data.Descricao;
import com.mhj.math.data.PropertyComposta;
import com.mhj.math.data.TagComposta;
import com.mhj.math.data.ValueComposta;
import com.mhj.math.data.interfaces.Data;
import com.mhj.math.enums.MathjaxProperty;
import com.mhj.math.enums.MathjaxTag;
import com.mhj.math.enums.MathjaxValue;
import com.mhj.math.enums.Simbolo;

public class MathjaxUtil {
	
	private MathjaxUtil() {
		
	}
	
	public static List<Data> montarTagComposta(TagComposta tagProperty){
		List<Data> retorno = new ArrayList<>();
		retorno.add(Simbolo.MENOR);
		retorno.add(new Descricao(tagProperty.getTag().getHtml().substring(1, tagProperty.getTag().getHtml().length()-1)));
		
		for (PropertyComposta property : tagProperty.getProperties()) {
			retorno.add(Simbolo.ESPACO);
			retorno.add(property.getProperty());
			retorno.add(Simbolo.IGUAL);
			retorno.add(Simbolo.ASPA_DUPLA);
			for (ValueComposta value : property.getValues()) {
				retorno.add(value.getData());
				retorno.add(Simbolo.ESPACO);
			}
			retorno.remove(retorno.size()-1);
			retorno.add(Simbolo.ASPA_DUPLA);
		}
		retorno.add(Simbolo.MAIOR);
		return retorno;
	}
	
	public static List<Data> abreMath(){
		List<ValueComposta> values = new ArrayList<>();
		List<PropertyComposta> properties = new ArrayList<>();

		ValueComposta value = new ValueComposta(MathjaxValue.LINK_MATHML);
		values.add(value);
		PropertyComposta property = new PropertyComposta(MathjaxProperty.XMLNS, values);
		properties.add(property);
		
		values = new ArrayList<>();
		value = new ValueComposta(MathjaxValue.BLOCK);
		values.add(value);
		property = new PropertyComposta(MathjaxProperty.DISPLAY, values);		
		properties.add(property);
		TagComposta tag = new TagComposta(MathjaxTag.MATH_OPEN, properties);
		return montarTagComposta(tag);
	}
	
}
