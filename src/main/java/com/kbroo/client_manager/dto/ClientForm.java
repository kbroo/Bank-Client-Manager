package com.kbroo.client_manager.dto;

import com.kbroo.client_manager.validation.ValidEmail;
import com.kbroo.client_manager.validation.ValidPhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientForm {
    @NotBlank(message = "Имя обязательно.")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов длиной.")
    private String name;
    @ValidEmail
    private String email;
    @ValidPhone
    private String phone;

    public ClientForm() {}

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
