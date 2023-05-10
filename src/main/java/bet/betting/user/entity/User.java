package bet.betting.user.entity;

import bet.betting.wallet.entity.Wallet;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Long userId;
    private String displayName;
    private Wallet wallet;
}
