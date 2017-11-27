package com.gnt.movies.utilities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;

@Converter(autoApply = true)
public class JsonAttributeConverter implements AttributeConverter<JsonElement, String> {

	@Override
	public String convertToDatabaseColumn(JsonElement attribute) {
		if (attribute != null && !attribute.isJsonNull())
			return attribute.toString();
		else
			return null;
	}

	@Override
	public JsonElement convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return JsonNull.INSTANCE;
		}
		else {
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(dbData);
			// JsonArray trade = tradeElement.getAsJsonArray();
			return jsonElement;
		}
	}

}
