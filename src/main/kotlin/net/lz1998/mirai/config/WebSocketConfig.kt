package net.lz1998.mirai.config

import net.lz1998.mirai.handler.WebSocketHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.server.standard.ServerEndpointExporter


@Configuration
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {
    @Autowired
    lateinit var webSocketHandler: WebSocketHandler
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler, "/ws/*/").setAllowedOrigins("*");
    }
}