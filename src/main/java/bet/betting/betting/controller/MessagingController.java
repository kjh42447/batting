package bet.betting.betting.controller;

import bet.betting.betting.entity.BettingMessage;
import bet.betting.betting.entity.MessageType;
import bet.betting.betting.service.BettingService;
import bet.betting.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessagingController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final BettingService bettingService;
    public void sendEnterOrLeaveMessage(MessageType messageType, String roomId, User user) {
        BettingMessage bettingMessage = bettingService.makeEnterOrLeaveBettingMessage(messageType, roomId, user);
        simpMessageSendingOperations.convertAndSend("/sub/room/" + bettingMessage.getRoomId(), bettingMessage);
    }
}
