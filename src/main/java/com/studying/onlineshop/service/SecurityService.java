package com.studying.onlineshop.service;

import com.studying.onlineshop.entity.Client;
import jakarta.servlet.http.Cookie;
import org.apache.commons.codec.digest.DigestUtils;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.*;


public class SecurityService {
    private ClientService CLIENT_SERVICE;
    private final List<String> CLIENT_TOKENS = Collections.synchronizedList(new ArrayList<>());

    public SecurityService() {
    }

    public SecurityService(ClientService clientService) {
        this.CLIENT_SERVICE = clientService;
    }

    public void signup(Client client) throws NoSuchAlgorithmException, NoSuchProviderException {
        String sole = generateSole();
        client.setSole(sole);
        String hashPassword = DigestUtils.md5Hex(client.getSole() + client.getPassword());
        client.setPassword(hashPassword);
        CLIENT_SERVICE.add(client);
    }

    public boolean isClientAuth(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (CLIENT_TOKENS.contains(cookie.getValue())) {
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
        // need to add logback

        String userToken = UUID.randomUUID().toString();
        CLIENT_TOKENS.add(userToken);
        return userToken;
    }

    private boolean isPasswordCorrect(Client loginClient) {
        Client dbClient = CLIENT_SERVICE.findByEmail(loginClient.getEmail());
        if (dbClient != null) {
            String hashPassword = DigestUtils.md5Hex(dbClient.getSole() + loginClient.getPassword());
            if (dbClient.getPassword().equals(hashPassword)) {
                return true;
            }
        }
        return false;
    }

    private String generateSole() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] soleBytes = new byte[16];
        secureRandom.nextBytes(soleBytes);
        return Arrays.toString(soleBytes);
    }
}
