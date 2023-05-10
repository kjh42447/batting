package bet.betting.betting.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BettingMessage {
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String userDisplayName;
    private String bettingAt;
}
