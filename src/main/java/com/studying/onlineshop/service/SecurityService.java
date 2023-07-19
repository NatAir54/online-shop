package com.studying.onlineshop.service;

import com.studying.onlineshop.entity.Client;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class SecurityService {
    private final ClientService clientService;
    private final List<String> clientTokens = Collections.synchronizedList(new ArrayList<>());


    public SecurityService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void signup(Client client) {
        String sole = UUID.randomUUID().toString();
        client.setSole(sole);
        String hashPassword = DigestUtils.md5Hex(client.getSole() + client.getPassword());
        client.setPassword(hashPassword);
        clientService.add(client);
    }

    public boolean isClientAuth(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (clientTokens.contains(cookie.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String login(Client client) {
        if (isPasswordCorrect(client)) {
            return generateToken();
        } else {
            return null;
        }
    }

    public void logout(Client client) {

    }

    private String generateToken() {
        String userToken = UUID.randomUUID().toString();
        clientTokens.add(userToken);
        return userToken;
    }

    private boolean isPasswordCorrect(Client loginClient) {
        Client dbClient = clientService.findClient(loginClient.getEmail());
        if (dbClient != null) {
            String hashPassword = DigestUtils.md5Hex(dbClient.getSole() + loginClient.getPassword());
            if (dbClient.getPassword().equals(hashPassword)) {
                return true;
            }
        }
        return false;
    }
}
