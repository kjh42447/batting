package bet.betting.config.websocket;

import bet.betting.betting.controller.MessagingController;;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

// WebSocketHandler 매핑
@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final StompHandler stompHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //클라이언트로 메세지를 응답 해 줄 때 prefix 정의 - 클라이언트가 메세지를 받을 때
        registry.enableSimpleBroker("/sub"); //ex) stomp.subscribe("/sub/chat/room/",function(){})
        //클라이언트에서 메세지 송신 시 붙일 prefix 정의 - 클라이언트가 메세지를 보낼때
        registry.setApplicationDestinationPrefixes("/pub"); //ex) stomp.send("/sub/chat/room/",function(){})
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //the url is for Websocket handshake
        registry.addEndpoint("/ws/betting") //handshake가 될 endpoint지정
                .setAllowedOriginPatterns("*"); //현재 구동되고 있는 서버와 다른 도메인에서도 접근 가능하게
//                .withSockJS(); //SockJS사용
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }
}
