package net.lz1998.mirai.boot

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("spring.mirai.event")
object EventProperties {
    var corePoolSize: Int = 5
    var maxPoolSize: Int = 10
    var keepAliveTime: Long = 2000
    var workQueueSize: Int = 512
}
