package net.lz1998.mirai.boot

import net.lz1998.mirai.handler.WebSocketHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@ComponentScan(
        basePackages = ["net.lz1998.mirai"]
)
@Configuration
@EnableWebSocket
@EnableConfigurationProperties(MiraiProperties::class, EventProperties::class)
@Import(MiraiBean::class)
class MiraiAutoConfiguration : WebSocketConfigurer {
    @Autowired
    lateinit var cqProperties: MiraiProperties

    @Autowired
    lateinit var webSocketHandler: WebSocketHandler

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler, cqProperties.url).setAllowedOrigins("*");
    }
}