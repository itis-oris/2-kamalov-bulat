package ru.itis.request.content;

import ru.itis.Content;

import java.nio.charset.StandardCharsets;

public class CreateLobbyRequestContent implements Content {

    private final int clientId;
    private final String username;

    public CreateLobbyRequestContent(int id, String username) {
        this.clientId = id;
        this.username = username;
    }

    public CreateLobbyRequestContent(byte[] data) {
        String content = new String(data, StandardCharsets.UTF_8);
        String[] parts = content.split("&");
        String clientId = parts[0].split("=")[1];
        String username = parts[1].split("=")[1];
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid content format");
        }
        this.clientId = Integer.parseInt(clientId);
        this.username = username;
    }

    @Override
    public byte[] toByteArray() {
        String contentString = "id=" + clientId + "&username=" + username;
        return contentString.getBytes(StandardCharsets.UTF_8);
    }

    public int getClientId() {
        return clientId;
    }

    public String getUsername() {
        return username;
    }
}
