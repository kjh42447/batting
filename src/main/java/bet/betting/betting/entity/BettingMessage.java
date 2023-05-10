package bet.betting.betting.entity;

import lombok.*;

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
}
