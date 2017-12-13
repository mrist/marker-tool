package data;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relations {
	@SerializedName("relations")
	@Expose
	private ArrayList<Relation>						relations				= new ArrayList<Relation>();
	private HashMap<Phrase, ArrayList<Relation>>	relationsByAspect		= new HashMap<Phrase, ArrayList<Relation>>();
	private HashMap<Phrase, ArrayList<Relation>>	relationsByStatement	= new HashMap<Phrase, ArrayList<Relation>>();

	public Relations() {
	}

	public void add(Relation r) {
		this.relations.add(r);

		Phrase tempWord = r.getAspect();
		ArrayList<Relation> tempRelations = new ArrayList<Relation>();

		if (relationsByAspect.containsKey(tempWord)) {
			tempRelations = relationsByAspect.get(tempWord);
		}
		tempRelations.add(r);
		relationsByAspect.put(tempWord, tempRelations);

		tempWord = r.getStatement();
		tempRelations = new ArrayList<Relation>();
		if (relationsByStatement.containsKey(tempWord)) {
			tempRelations = relationsByStatement.get(tempWord);
		}
		tempRelations.add(r);
		relationsByStatement.put(tempWord, tempRelations);
	}

	public ArrayList<Relation> all() {
		return relations;
	}

	public void append(Relations other) {
		this.relations.addAll(other.all());
		this.relationsByAspect.putAll(other.relationsByAspect);
		this.relationsByStatement.putAll(other.relationsByStatement);
	}

	public ArrayList<Relation> getByAspect(Phrase aspect) {
		return this.relationsByAspect.get(aspect);
	}

	public ArrayList<Relation> getByStatement(Phrase statement) {
		return this.relationsByStatement.get(statement);
	}

	public void initialize(Phrases phrases) {
		for (Relation relation : relations) {
			relation.initialize(phrases);

			Phrase tempWord = relation.getAspect();
			ArrayList<Relation> tempRelations = new ArrayList<Relation>();

			if (relationsByAspect.containsKey(tempWord)) {
				tempRelations = relationsByAspect.get(tempWord);
			}
			tempRelations.add(relation);
			relationsByAspect.put(tempWord, tempRelations);

			tempWord = relation.getStatement();
			tempRelations = new ArrayList<Relation>();
			if (relationsByStatement.containsKey(tempWord)) {
				tempRelations = relationsByStatement.get(tempWord);
			}
			tempRelations.add(relation);
			relationsByStatement.put(tempWord, tempRelations);
		}
	}

	public void remove(Relation r) {
		this.relations.remove(r);

		Phrase tempWord = r.getAspect();
		ArrayList<Relation> tempRelations = new ArrayList<Relation>();

		if (relationsByAspect.containsKey(tempWord)) {
			tempRelations = relationsByAspect.get(tempWord);
			tempRelations.remove(r);
			relationsByAspect.put(tempWord, tempRelations);
		}

		tempWord = r.getStatement();
		tempRelations = new ArrayList<Relation>();
		if (relationsByStatement.containsKey(tempWord)) {
			tempRelations = relationsByStatement.get(tempWord);
			tempRelations.remove(r);
			relationsByStatement.put(tempWord, tempRelations);
		}
	}
}
