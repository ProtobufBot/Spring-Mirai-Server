package net.lz1998.mirai.bot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("spring.mirai.event")
object EventProperties {
    @Value("\${spring.mirai.event.corePoolSize}")
    lateinit var corePoolSize:String
    @Value("\${spring.mirai.event.maxPoolSize}")
    lateinit var maxPoolSize:String
    @Value("\${spring.mirai.event.keepAliveTime}")
    lateinit var keepAliveTime: String
    @Value("\${spring.mirai.event.workQueueSize}")
    lateinit var workQueueSize:String

}
