package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import data.Review;

public class ReviewReader {

	private Gson gson;

	public ReviewReader(Gson gson) {
		this.gson = gson;
	}

	public Review getNextReview(File file) {
		Review review = new Review();
		try {
			JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			Gson gson = new GsonBuilder().create();

			reader.beginArray();
			if (reader.hasNext()) {
				review = gson.fromJson(reader, Review.class);
			}

			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return review;
	}

	public Review[] readReviews(File file) {
		Review[] reviews = new Review[0];
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			this.gson = new GsonBuilder().create();
			reviews = this.gson.fromJson(reader, Review[].class);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return reviews;
	}

	public Review[] readTextFileReviews(File file) {
		Review[] reviews = new Review[1];
		try {
			String reviewText = new String(Files.readAllBytes(file.toPath()), "UTF-8");
			Review tempReview = new Review(file.getName(), reviewText);
			reviews[0] = tempReview;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviews;
	}

}
