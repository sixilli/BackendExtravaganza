package com.tekkenhub.backend.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "blog_posts")
public class BlogPost {
    private @Id @GeneratedValue Long id;
    private String title, author, text, link;
    private int category;

    @Column(name = "created_at", updatable = false, insertable = false)
    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    @UpdateTimestamp
    private java.sql.Timestamp updatedAt;

    public BlogPost() {}

    public BlogPost(String title, String author, String text, String link, int category) {
        this.title = title;
        this.author = author;
        this.text = text;
        this.link = link;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlogPost)) return false;
        BlogPost blogPost = (BlogPost) o;
        return Objects.equals(this.id, blogPost.id) && Objects.equals(this.title, blogPost.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.author, this.text, this.category, this.createdAt, this.updatedAt, this.link);
    }

    @Override
    public String toString() {
        return "BlogPost{" + " id=" + this.id  + ", title=" + this.title + ", author=" + this.author + ", text=" + this.text +
                ", link" + this.link + ", category=" + this.category + ", created_at=" + this.createdAt + ", updated_at=" + this.updatedAt;
    }
}
