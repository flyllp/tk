package net.historm.tk.format;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static final ObjectMapper jsonMapper = new ObjectMapper();

	public static String convertObject2Json(Object obj) {
		String jsonStr = "";
		try {
			jsonStr = jsonMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {

		}
		return jsonStr;
	}

	public static Object convertJson2Object(String json, Class<?> c) {
		Object o = null;
		try {
			o = jsonMapper.readValue(json, c);
		} catch (IOException e) {

		}
		return o;
	}

	public static Object convertJson2Object(String json, Class<?> c1, Class<?> c2) {
		Object o = null;
		try {
			JavaType javaType = jsonMapper.getTypeFactory().constructParametricType(c1, c2);
			o = jsonMapper.readValue(json, javaType);
		} catch (IOException e) {
		}
		return o;
	}

	public static <T> List<T> json2List(String jsonData, Class<T> beanType) {
		JavaType javaType = jsonMapper.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = jsonMapper.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
