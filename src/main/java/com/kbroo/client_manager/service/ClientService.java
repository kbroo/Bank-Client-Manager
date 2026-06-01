package com.kbroo.client_manager.service;

import com.kbroo.client_manager.model.Client;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {
    private List<Client> clients;

    public ClientService() {
        this.clients = new ArrayList<>();
        clients.add(new Client("Евгеха", "ssss@gmail.com", "+3751234567"));
        clients.add(new Client("Андрюха", "aaaaa@gmail.com", "+3758901234"));
        clients.add(new Client("Кирюха", "bbbbb@gmail.com", "+3755678901"));
    }

    public List<Client> getAllClients() {
        return clients;
    }
    public Optional<Client> getClient(String id) {
        return clients.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }
    public int getCountClients() {
        return clients.size();
    }
    public List<Client> getAllClientsSortedByName() {
        return clients.stream()
                .sorted(Comparator.comparing(Client::getName))
                .toList();
    }

    public List<Client> searchClientsByName(String name) {
        return clients.stream()
                .filter(client -> client.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public boolean addClient(Client client) {
        if (clients.contains(client)) {
            System.out.println("Клиент уже существует");
            return false;
        }
        if (client.getId() == null || client.getId().isEmpty()) {
            client.setId(UUID.randomUUID().toString());
        }
        clients.add(client);
        return true;
    }

    public boolean deleteClient(String id) {
        return clients.removeIf(client -> client.getId().equals(id));
    }
}
