package ru.stqa.pft.rest;

import java.util.Objects;

public class Issue {

    private String subject;
    private String description;
    private int id;

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                Objects.equals(subject, issue.subject) &&
                Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subject, description, id);
    }

    public Issue withId(int id) {
        this.id = id;

        return this;
    }
}
