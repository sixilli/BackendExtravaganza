package com.tekkenhub.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "tournaments")
public class Tournament {
    private @Id @GeneratedValue Long id;
    private String title;
    private String region;
    private String location;

    @Column(name = "event_link")
    private String eventLink;

    @Column(name = "stream_link")
    private String streamLink;

    private int attendees;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "created_at", updatable = false, insertable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Tournament() {}

    public Tournament(String title, String region, String location, String eventLink, String streamLink, int attendees,
                      Timestamp startTime, Timestamp createdAt, Timestamp updatedAt) {
        this.title = title;
        this.region = region;
        this.location = location;
        this.eventLink = eventLink;
        this.streamLink = streamLink;
        this.attendees = attendees;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startTime = startTime;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventLink() {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public String getStreamLink() {
        return streamLink;
    }

    public void setStreamLink(String streamLink) {
        this.streamLink = streamLink;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;
        Tournament tournament = (Tournament) o;
        return Objects.equals(this.id, tournament.id) && Objects.equals(this.title, tournament.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.updatedAt, this.createdAt, this.attendees, this.eventLink,
                this.location, this.region, this.streamLink);
    }

    @Override
    public String toString() {
        return "Tournament{" + " id=" + this.id  + ", title=" + this.title + ", attendees=" + this.attendees + ", event_link="
                + this.eventLink + ", stream_link=" + this.streamLink + ", region=" + this.region + ", location="
                + this.location + ", start_time=" + this.startTime +  ", created_at=" + this.createdAt + ", updated_at=" + this.updatedAt;
    }
}
