package com.ticketmanagement.booking.model;

import java.util.Date;
import java.util.Objects;

public class Ticket {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Date creationDate;


    public Ticket(Long id, String title, String description, Status status, Priority priority, Date creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.creationDate = creationDate;
    }

    public Ticket() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return getId() == ticket.getId() && Objects.equals(getTitle(), ticket.getTitle()) && Objects.equals(getDescription(), ticket.getDescription()) && getStatus() == ticket.getStatus() && getPriority() == ticket.getPriority() && Objects.equals(getCreationDate(), ticket.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getStatus(), getPriority(), getCreationDate());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", creationDate=" + creationDate +
                '}';
    }
}
