package data;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phrases {

	private ArrayList<Phrase>		wordList	= new ArrayList<Phrase>();
	private HashMap<String, Phrase>	wordMap		= new HashMap<String, Phrase>();

	@SerializedName("claims")
	@Expose
	private ArrayList<Phrase>		claims		= new ArrayList<Phrase>();

	@SerializedName("premises")
	@Expose
	private ArrayList<Phrase>		premises	= new ArrayList<Phrase>();

	public Phrases() {
	}

	public void add(Phrase w) {
		this.wordList.add(w);
		if (w.getType().equals("claim")) {
			w.setTypeId("claim", this.claims.size());
			this.claims.add(w);
		}
		else if (w.getType().equals("premise")) {
			w.setTypeId("premise", this.premises.size());
			this.premises.add(w);
		}
	}

	public void appendWords(Phrases other) {
		this.wordList.addAll(other.getWordList());
		this.claims.addAll(other.getClaims());
		this.premises.addAll(other.getPremises());
	}

	public ArrayList<Phrase> getClaims() {
		return claims;
	}

	public Phrase getPhrase(String id) {
		return this.wordMap.get(id);
	}

	public ArrayList<Phrase> getPremises() {
		return premises;
	}

	public ArrayList<Phrase> getWordList() {
		return this.wordList;
	}

	public void initialize() {
		for (Phrase phrase : this.claims) {
			this.wordList.add(phrase);
			this.wordMap.put(phrase.getId(), phrase);
		}
		for (Phrase phrase : this.premises) {
			this.wordList.add(phrase);
			this.wordMap.put(phrase.getId(), phrase);
		}
	}

	public void remove(Phrase w) {
		this.claims.remove(w);
		this.premises.remove(w);
		this.wordList.remove(w);
	}

	public void removeWords(Phrases other) {
		this.wordList.removeAll(other.getWordList());
		this.claims.removeAll(other.getClaims());
		this.premises.removeAll(other.getPremises());
	}

}
