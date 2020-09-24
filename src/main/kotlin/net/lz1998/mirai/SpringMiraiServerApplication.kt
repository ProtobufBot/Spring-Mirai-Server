package net.lz1998.mirai

import net.lz1998.mirai.bot.EventProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(EventProperties::class)
class SpringMiraiServerApplication

fun main(args: Array<String>) {
    runApplication<SpringMiraiServerApplication>(*args)
}
