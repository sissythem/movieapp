package com.gnt.movies.utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

	public static JsonArray getJsonArrayFromJson(String field, JsonObject jo) {
		JsonArray result = null;
		JsonElement je = jo.get(field);
		if (je != null && !je.isJsonNull())
			result = je.getAsJsonArray();
		return result;
	}

	public static JsonArray getJsonArrayFromJson(String field, JsonElement jsonElement) {
		JsonArray result = null;
		JsonObject jo = jsonElement.getAsJsonObject();
		if (jo != null && !jo.isJsonNull()) {
			JsonElement je = jo.get(field);
			if (je != null && !je.isJsonNull()) {
				result = je.getAsJsonArray();
			}
		}
		return result;
	}

	public static String getStringFromJson(String field, JsonObject jo) {
		String result = null;
		JsonElement je = jo.get(field);
		if (je != null && !je.isJsonNull())
			result = je.getAsString();
		return result;
	}

	public static Integer getIntegerFromJson(String field, JsonObject jo) {
		Integer result = null;
		JsonElement je = jo.get(field);
		if (je != null && !je.isJsonNull())
			result = je.getAsInt();
		return result;
	}

	public static Double getDoubleFromJson(String field, JsonObject jo) {
		Double result = null;
		JsonElement je = jo.get(field);
		if (je != null && !je.isJsonNull())
			result = je.getAsDouble();
		return result;
	}

	public static Boolean getBooleanFromJson(String field, JsonObject jo) {
		Boolean result = false;
		JsonElement je = jo.get(field);
		if (je != null && !je.isJsonNull())
			result = je.getAsBoolean();
		return result;
	}

	public static JsonObject getJsonObjectFromString(String jsonString) {
		JsonParser parser = new JsonParser();
		JsonElement je = parser.parse(jsonString);
		return je.getAsJsonObject();
	}
}
