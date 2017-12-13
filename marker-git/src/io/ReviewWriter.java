package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import data.Review;

public class ReviewWriter {

	private Gson gson;

	public ReviewWriter(Gson gson) {
		this.gson = gson;
	}

	public Review[] readReviews(File file) {
		Review[] reviews = new Review[0];
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			Gson gson = new GsonBuilder().create();
			reviews = gson.fromJson(reader, Review[].class);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return reviews;
	}

	public void writeReviews(Review[] reviews, File file) throws IOException {
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		writer.setIndent("  ");
		writer.beginArray();
		for (Review review : reviews) {
			gson.toJson(review, Review.class, writer);
		}
		writer.endArray();
		writer.close();
	}
}
