package com.gnt.movies.utilities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanAttributeConverter implements AttributeConverter<Boolean, Integer> {
	
    @Override
    public Integer convertToDatabaseColumn(Boolean bool) {
    	return (bool == null ? null : (bool?1: 0));
    }

    @Override
    public Boolean convertToEntityAttribute(Integer integer) {
    	return (integer == null ? null : (integer.equals(Integer.valueOf(1)) ?true:false));
    }
}