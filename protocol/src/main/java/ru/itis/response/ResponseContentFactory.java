package ru.itis.response;

import ru.itis.Content;
import ru.itis.response.content.SignInResponseContent;
import ru.itis.response.content.SuccessContent;

public class ResponseContentFactory {
    public static Content createResponseContent(ResponseType responseType, byte[] data) {
        switch (responseType) {
            case SUCCESS -> {
                return new SuccessContent();
            }
            case SIGN_IN -> {
                return new SignInResponseContent(data);
            }
            default -> throw new IllegalArgumentException("Invalid response type");
        }
    }
}
