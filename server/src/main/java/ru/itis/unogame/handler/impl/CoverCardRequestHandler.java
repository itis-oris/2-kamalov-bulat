package ru.itis.unogame.handler.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.cards.Card;
import ru.itis.request.Request;
import ru.itis.request.RequestType;
import ru.itis.request.content.CoverCardRequestContent;
import ru.itis.response.Response;
import ru.itis.response.ResponseType;
import ru.itis.response.content.ErrorResponseContent;
import ru.itis.service.MessageService;
import ru.itis.unogame.handler.RequestHandler;
import ru.itis.unogame.model.Game;
import ru.itis.unogame.service.GameService;

import java.net.Socket;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoverCardRequestHandler implements RequestHandler {

    private final GameService gameService;
    private final MessageService<Response, Request> serverProtocolService;

    @Override
    public void handle(Socket socket, Request request) {
        CoverCardRequestContent content = (CoverCardRequestContent) request.content();
        int gameId = content.getGameId();
        int playerId = content.getPlayerId();
        Card card = content.getCard();
        Optional<Game> gameOptional = gameService.findGameById(gameId);
        if (gameOptional.isEmpty()) {
            serverProtocolService.send(new Response(
                    ResponseType.ERROR,
                    new ErrorResponseContent("Game with ID: " + gameId + " do not found")),
                    socket);
            return;
        }
        Game game = gameOptional.get();
        game.coverCard(playerId, card);
    }

    @Override
    public RequestType getType() {
        return RequestType.COVER_CARD;
    }
}
