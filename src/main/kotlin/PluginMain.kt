package com.blrabbit.mirai.chatbot

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import net.mamoe.mirai.console.data.ReadOnlyPluginConfig
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.subscribeMessages

object ChatBotMain : KotlinPlugin(
    JvmPluginDescription(
        id = "com.blrabbit.mirai.chatbot",
        name = "chatbot",
        version = "0.1.0"
    )
) {
    override fun onEnable() {
        MySetting.reload()
        GlobalEventChannel.subscribeMessages {
            atBot(){

                val mes: String = this.message.toString()
                var mess = ""
                var flag = 0
                for (a in mes) {
                    if (a == '[') {
                        flag = 1
                        continue
                    }
                    if (a == ']') {
                        flag = 0
                        continue
                    }
                    if (a == ' ')
                        continue
                    if (flag == 0)
                        mess += a.toString()
                }
                subject.sendMessage(getDataByGet(mess))
            }


        }
    }
}

val client = HttpClient(OkHttp)

suspend fun getDataByGet(params: String): String {
    return client.get("http://i.itpk.cn/api.php?question=$params&api_key=${MySetting.ApiKey}&api_secret=${MySetting.ApiSecret}")
}

object MySetting: ReadOnlyPluginConfig("chatbot-config"){
    var ApiKey:String by value("0")
    var ApiSecret by value("0")
}