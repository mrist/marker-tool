package io;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import data.Review;

public class ReviewSerializer implements JsonSerializer<Review> {

	@Override
	public JsonElement serialize(Review rev, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObj = new JsonObject();

		return jsonObj;
	}
}