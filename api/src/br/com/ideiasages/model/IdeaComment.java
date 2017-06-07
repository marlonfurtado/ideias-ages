package br.com.ideiasages.model;

import java.io.Serializable;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class IdeaComment implements Serializable {

	private static final long serialVersionUID = -789863172532826108L;
	private long id;
	private String comment;

	public IdeaComment() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String c) {
		this.comment = c;
	}

	@Override
	public String toString() {
		return "IdeaComment [id=" + id + ", comment=" + comment + "]";
	}
}