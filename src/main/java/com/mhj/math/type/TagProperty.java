package com.mhj.math.type;

import java.util.List;

import com.mhj.math.enums.MathjaxTag;

public class TagProperty {
	
	private MathjaxTag tag;
	private List<PropertyValue> properties;
	
	public TagProperty(MathjaxTag tag, List<PropertyValue> properties) {
		super();
		this.tag = tag;
		this.properties = properties;
	}
	
	public MathjaxTag getTag() {
		return tag;
	}
	
	public PropertyValue getPropertie(int index) {
		return properties.get(index);
	}
	
	public void setPropertie(PropertyValue propertie) {
		this.properties.add(propertie);
	}

}
