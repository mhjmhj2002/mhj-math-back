package com.mhj.math.type;

import java.util.List;

import com.mhj.math.enums.MathjaxProperty;
import com.mhj.math.enums.MathjaxValue;

public class PropertyValue {
	
	private MathjaxProperty property;

	private List<MathjaxValue> values;

	public PropertyValue(MathjaxProperty property, List<MathjaxValue> values) {
		super();
		this.property = property;
		this.values = values;
	}

	public MathjaxProperty getProperty() {
		return property;
	}

	public MathjaxValue getValue(int index) {
		return values.get(index);
	}

	public void setValue(MathjaxValue value) {
		this.values.add(value);
	}

}
