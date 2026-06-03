package com.kbroo.client_manager.controllers;

import com.kbroo.client_manager.model.Client;
import com.kbroo.client_manager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String listClients(@RequestParam(required = false) String sort, @RequestParam(required = false) String name, Model model) {
        List<Client> clients;

        if (name != null && !name.isBlank()) {
            clients = clientService.getClientsByName(name);
        } else {
            clients = clientService.getAllClients();
        }

        if ("name".equals(sort) && !clients.isEmpty()) {
            clients = clients.stream()
                    .sorted(Comparator.comparing(Client::getName))
                    .toList();
        }

        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/{id}")
    public String addClient(@PathVariable String id, Model model) {
        clientService.getClient(id).ifPresentOrElse(
                client -> model.addAttribute("client", client),
                () -> model.addAttribute("error", "Клиент с ID " + id + " не найден.")
        );
        return "client-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable String id, Model model) {
        clientService.getClient(id).ifPresentOrElse(
                client -> model.addAttribute("client", client),
                () -> model.addAttribute("error", "Клиент с ID " + id + " не найден.")
        );
        return "client-delete";
    }

    @PostMapping("/delete/{id}")
    public String completeDeleteClient(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean result = clientService.deleteClient(id);
        if (result) {
            redirectAttributes.addFlashAttribute("success", "Клиент с ID " + id + " удален.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Клиент с ID " + id + " не найден.");
        }
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable String id, Model model) {
        clientService.getClient(id).ifPresentOrElse(
                client -> model.addAttribute("client", client),
                () -> model.addAttribute("error", "Клиент с ID " + id + " не найден.")
        );
        return "client-edit";
    }

    @PostMapping("/edit/{id}")
    public String completeEditClient(@PathVariable String id, @ModelAttribute Client modelAttribute, RedirectAttributes redirectAttributes) {
        Client client = clientService.getClient(id).orElse(null);
        if (client == null) {
            redirectAttributes.addFlashAttribute("error", "Пользователь с ID "  + id +" не найден");
            return "redirect:/clients";
        }
        client.setName(modelAttribute.getName());
        client.setEmail(modelAttribute.getEmail());
        client.setPhone(modelAttribute.getPhone());
        redirectAttributes.addFlashAttribute("success", "Данные пользователя с ID " + id + " изменены.");
        return "redirect:/clients";
    }

    @GetMapping("/add")
    public String addClient(Model model) {
        model.addAttribute("client", new Client());
        return "client-add";
    }

    @PostMapping("/add")
    public String completeAddClient(@ModelAttribute Client modelAttribute, RedirectAttributes redirectAttributes) {
        boolean result = clientService.addClient(modelAttribute);
        if (result) {
            redirectAttributes.addFlashAttribute("success", "Новый клиент добавлен успешно.");
            return "redirect:/clients/" + modelAttribute.getId();
        } else {
            redirectAttributes.addFlashAttribute("error", "Ошибка при добавлении клиента.");
        }
        return "redirect:/clients";
    }
}
