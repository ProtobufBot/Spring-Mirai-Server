package net.lz1998.pbbot.interceptor;

import lombok.SneakyThrows;
import net.lz1998.pbbot.handler.BotSessionInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Primary
@Component
public class MyWsInterceptor extends BotSessionInterceptor {
    @SneakyThrows
    @Override
    public boolean checkSession(@NotNull WebSocketSession session) {
        HttpHeaders headers = session.getHandshakeHeaders();
        String botId = headers.getFirst("x-self-id");
        System.out.println(headers);
        System.out.println("新的连接" + botId);
        if ("123".equals(botId)) {
            System.out.println("机器人账号是123，关闭连接");
            session.close();
            return false; // 禁止连接
        }
        return true; // 正常连接
    }
}
