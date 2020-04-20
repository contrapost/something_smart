package me.contrapost.somethingsmart.bot

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.SlashCommandContext
import com.slack.api.bolt.jetty.SlackAppServer
import com.slack.api.bolt.request.builtin.SlashCommandRequest
import java.time.LocalDateTime

fun main() {
    val app = App()

    app.command(
        "/whatday"
    ) { req: SlashCommandRequest?, context: SlashCommandContext ->
        val day = LocalDateTime.now()
        context.ack("Today is $day, ${req?.payload?.userName}")
    }

    app.command(
        "/hello"
    ) { scr: SlashCommandRequest?, ctx: SlashCommandContext ->
        println(scr?.payload?.text)
        println(scr?.payload?.userName)
        ctx.ack(":wave: Hello you there, ${scr?.payload?.userName}! You want me to poke ${scr?.payload?.text}?")
    }

    val server = SlackAppServer(app, 8080)

    server.start()
}
