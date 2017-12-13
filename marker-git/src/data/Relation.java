package data;

import com.google.gson.annotations.Expose;

public class Relation {
	@Expose
	private String	aspectID;
	@Expose
	private String	statementID;
	private Phrase	aspect;
	private Phrase	statement;
	@Expose
	private String	review;

	public Relation(Phrase aspect, Phrase statement, String review) {
		this.aspect = aspect;
		this.statement = statement;
		this.aspectID = aspect.getId();
		this.statementID = statement.getId();
		this.review = review;
	}

	public Phrase getAspect() {
		return aspect;
	}

	public String getAspectID() {
		return aspectID;
	}

	public String getReview() {
		return review;
	}

	public Phrase getStatement() {
		return statement;
	}

	public String getStatementID() {
		return statementID;
	}

	public void initialize(Phrases phrases) {
		this.aspect = phrases.getPhrase(this.aspectID);
		this.statement = phrases.getPhrase(this.statementID);
	}

	public void setPhrases(Phrase aspect, Phrase statement) {
		this.aspect = aspect;
		this.statement = statement;
	}

	@Override
	public String toString() {
		return this.aspect + " <> " + this.statement;
	}

}
