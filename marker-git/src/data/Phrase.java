package data;

import com.google.gson.annotations.Expose;

public class Phrase {
	private static int	maxStringLength	= 40;
	@Expose
	private Integer		start;
	@Expose
	private Integer		end;
	@Expose
	private String		review;
	@Expose
	private String		text;
	@Expose
	private String		id;
	@Expose
	private String		type;

	@Expose
	private Boolean		isTopic			= false;

	public Phrase() {

	}

	public Phrase(int start, int end, String review, String text, String type) {
		this.start = start;
		this.end = end;
		this.review = review;
		this.text = text;
		this.type = type;
	}

	public Phrase(String type) {
		this.type = type;
	}

	public Boolean equals(Phrase other) {
		return this.text.equals(other.getText());
	}

	public int getEnd() {
		return end;
	}

	public String getId() {
		return id;
	}

	public String getRawType() {
		return this.type;
	}

	public String getReview() {
		return review;
	}

	public int getStart() {
		return this.start;
	}

	public String getText() {
		return text;
	}

	public String getType() {
		return this.type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTypeId(String type, int count) {
		this.id += "-" + type + "-" + count;
	}

	public void toggleTopic() {
		this.isTopic = !isTopic;

	}

	@Override
	public String toString() {
		String result = (isTopic ? "*" : "") + "(" + this.start + "," + this.end + ") " + this.text;
		if (result.length() > maxStringLength) {
			result = result.substring(0, maxStringLength) + "...";
		}
		return result;
	}

}
