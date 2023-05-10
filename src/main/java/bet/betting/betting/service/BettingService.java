package bet.betting.betting.service;

import bet.betting.betting.entity.BettingMessage;
import bet.betting.betting.entity.BettingRoom;
import bet.betting.betting.entity.MessageType;
import bet.betting.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BettingService {
    public BettingMessage makeEnterOrLeaveBettingMessage(MessageType messageType, String roomId, User user) {
        BettingMessage bettingMessage = BettingMessage.builder()
                .type(messageType)
                .roomId(roomId)
                .sender(user.getUserId().toString())
                .userDisplayName(user.getDisplayName())
                .build();
        if (messageType.equals(MessageType.ENTER)) {
            setEnterMessage(bettingMessage);
        } else if (messageType.equals(MessageType.LEAVE)) {
            setLeaveMessage(bettingMessage);
        }
        bettingMessage.setBettingAt(LocalDateTime.now().toString());
        return bettingMessage;
    }

    public BettingMessage makeBettingMessage(BettingMessage bettingMessage) {
        bettingMessage.setBettingAt(LocalDateTime.now().toString());
        return bettingMessage;
    }
    private BettingMessage setEnterMessage(BettingMessage bettingMessage) {
        bettingMessage.setMessage("[알림] " + bettingMessage.getUserDisplayName() + "이 입장하셨습니다.");
        return bettingMessage;
    }

    private BettingMessage setLeaveMessage(BettingMessage bettingMessage) {
        bettingMessage.setMessage("[알림] " + bettingMessage.getUserDisplayName() + "이 퇴장하셨습니다.");
        return bettingMessage;
    }

}
