package com.kbroo.client_manager.service;

import com.kbroo.client_manager.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientService {
    private List<Client> clients;

    public ClientService() {
        clients.add(new Client("Евгеха", "ssss@gmail.com", "+3751234567"));
        clients.add(new Client("Андрюха", "aaaaa@gmail.com", "+3758901234"));
        clients.add(new Client("Кирюха", "bbbbb@gmail.com", "+3755678901"));
    }

    public List<Client> getClients() {
        return clients;
    }
    public Optional<Client> getClient(String id) {
        return clients.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public List<Client> searchClientsByName(String name) {
        return clients.stream()
                .filter(client -> client.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public void addClient(Client client) {
        if (clients.contains(client)) {
            System.out.println("Клиент уже существует");
            return;
        }
        if (client.getId() == null || client.getId().isEmpty()) {
            client.setId(UUID.randomUUID().toString());
        }
        clients.add(client);
    }

    public void deleteClient(String id) {
        clients.removeIf(client -> client.getId().equals(id));
    }
}
