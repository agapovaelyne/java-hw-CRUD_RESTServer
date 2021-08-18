package org.example.model;

import java.time.LocalDateTime;

public class Post {
    private long id;
    private String content;
    private LocalDateTime removed = null;

    public Post() {
    }

    public Post(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRemoved() {
        return removed;
    }

    public void setRemoved(LocalDateTime removed) {
        this.removed = removed;
    }

    public boolean isRemoved() {
        return removed != null;
    }

}