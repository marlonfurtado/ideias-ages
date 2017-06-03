package br.com.ideiasages.model;

import java.util.ArrayList;
import java.util.Date;

public class Idea {
    private int id;
    private String title;
    private String description;
    private IdeaStatus status;
    private String tags;
    private User user;
    private String goal;
    private Date creationDate;
    public Idea() {
    }

    public Idea(String title, String description, String tags, String goal, Date creationDate) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.goal = goal;
        this.creationDate = creationDate;
    }

    public Idea(int id, String title, String description, IdeaStatus status, String tags, User user, String goal, Date creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.tags = tags;
        this.user = user;
        this.goal = goal;
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
		return creationDate;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IdeaStatus getStatus() {
        return status;
    }

    public void setStatus(IdeaStatus status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
