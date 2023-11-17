package com.ketrina.httpserver.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "owners")
public class Owner {
    @Id
    private String id;
    private String name;
    private String accountNumber;
    private int level;

    // Empty constructor
    public Owner() { }

    // Parameterized constructor
    public Owner(final String id, final String name, final String accountNumber, final int level) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(final int level) {
        this.level = level;
    }
}
