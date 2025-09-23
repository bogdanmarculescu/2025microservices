package org.cards.resolver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(unique = true, nullable = false)
    @JsonProperty("id")
    private Long externalId;

    private int value;
    private Suite suite;
    private String filename;

    public Card(Long id, int value, Suite suite, String filename) {
        this.id = id;
        this.value = value;
        this.suite = suite;
        this.filename = filename;
    }

    public Card(){
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @JsonProperty("id")
    public Long getExternalId() {
        return externalId;
    }

    @JsonProperty("id")
    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }
}
