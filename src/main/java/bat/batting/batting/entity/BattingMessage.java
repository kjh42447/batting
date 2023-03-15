package bat.batting.batting.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattingMessage {
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
