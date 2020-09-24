package net.lz1998.mirai.bot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("spring.cq.event")
object EventProperties {
    @Value("\${spring.cq.event.corePoolSize}")
    lateinit var corePoolSize:String
    @Value("\${spring.cq.event.maxPoolSize}")
    lateinit var maxPoolSize:String
    @Value("\${spring.cq.event.keepAliveTime}")
    lateinit var keepAliveTime: String
    @Value("\${spring.cq.event.workQueueSize}")
    lateinit var workQueueSize:String

}