package net.lz1998.mirai

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringMiraiServerApplication

fun main(args: Array<String>) {
    runApplication<SpringMiraiServerApplication>(*args)
}
