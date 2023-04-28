package com.mhj.math.type;

import java.util.List;

import com.mhj.math.type.interfaces.Tag;

public class TagComposta {
	
	private Tag tag;
	private List<PropertyComposta> properties;
	
	public TagComposta(Tag tag, List<PropertyComposta> properties) {
		super();
		this.tag = tag;
		this.properties = properties;
	}
	
	public Tag getTag() {
		return tag;
	}
	
	public PropertyComposta getPropertie(int index) {
		return properties.get(index);
	}
	
	public void setPropertie(PropertyComposta propertie) {
		this.properties.add(propertie);
	}

	public List<PropertyComposta> getProperties() {
		return properties;
	}

}
