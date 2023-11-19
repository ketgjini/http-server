package com.ketrina.httpserver.model.input;

/**
 * Input class representing owner information used for creating or updating an owner.
 * @author Ketrina
 */
public class OwnerInput {
    private String id;
    private String name;
    private String accountNumber;
    private int level;

    /**
     * Default constructor.
     */
    public OwnerInput() { }

    /**
     * Parameterized constructor.
     */
    public OwnerInput(final String id, final String name, final String accountNumber, final int level) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
