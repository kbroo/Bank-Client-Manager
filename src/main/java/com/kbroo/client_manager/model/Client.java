package com.kbroo.client_manager.model;

import java.util.Objects;
import java.util.UUID;

public class Client {
    private String id;
    private String name;
    private String email;
    private String phone;

    public Client() {}
    public Client(String name, String email, String phone) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Client client = (Client) obj;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
