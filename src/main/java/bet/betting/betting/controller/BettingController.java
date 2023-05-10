package bet.betting.betting.controller;

import bet.betting.betting.entity.BettingMessage;
import bet.betting.betting.entity.MessageType;
import bet.betting.betting.service.BettingService;
import bet.betting.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BettingController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final BettingService bettingService;

    @MessageMapping("/chat")
    public void message(BettingMessage bettingMessage) {
        bettingMessage = bettingService.makeBettingMessage(bettingMessage);
        simpMessageSendingOperations.convertAndSend("/sub/room/" + bettingMessage.getRoomId(), bettingMessage);
    }

    /**
        통신테스트용
     */
    @GetMapping("/test")
    public ResponseEntity getTest() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
