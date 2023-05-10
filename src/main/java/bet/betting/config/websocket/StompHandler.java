package bet.betting.config.websocket;

import bet.betting.betting.controller.BettingController;
import bet.betting.betting.controller.MessagingController;
import bet.betting.betting.entity.BettingMessage;
import bet.betting.betting.entity.MessageType;
import bet.betting.betting.service.BettingService;
import bet.betting.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class StompHandler implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        if (StompCommand.CONNECT.equals(command)) {
            System.out.println("CONNECT");
        } else if (StompCommand.SUBSCRIBE.equals(command)) {
            System.out.println("SUBSCRIBE");
            try {
                String roomId = parseRoomIdFromHeader(accessor);
//                messagingController.sendEnterOrLeaveMessage(MessageType.ENTER, roomId, User.builder().userId(1l).displayName("User").build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (StompCommand.UNSUBSCRIBE.equals(command)) {
            System.out.println("UNSUBSCRIBE");
        } else if (StompCommand.DISCONNECT.equals(command)) {
            System.out.println("DISCONNECT");
            try {
                String roomId = parseRoomIdFromHeader(accessor);
//                messagingController.sendEnterOrLeaveMessage(MessageType.ENTER, roomId, User.builder().userId(1l).displayName("User").build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return message;
    }

    private String parseRoomIdFromHeader(StompHeaderAccessor accessor) throws Exception {
        try {
            String destination = accessor.getDestination();
            String[] temp = destination.split("/");
            if (!temp[temp.length - 1].equals("room")) {
                return null;
            }
            return temp[temp.length - 1];
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
