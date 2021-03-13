package com.github.chal2andev.bluemarble.command

import com.github.chal2andev.bluemarble.plugin.BlueMarblePlugin
import com.github.monun.kommand.KommandDispatcherBuilder
import net.md_5.bungee.api.ChatColor
import org.bukkit.entity.Player

internal object BlueMarbleCommand {
    private lateinit var plugin: BlueMarblePlugin

    internal fun initModule(plugin: BlueMarblePlugin){
        this.plugin = plugin
    }
    internal fun register(builder: KommandDispatcherBuilder){
        builder.register("bluemarble"){
            require { this is Player }
            executes {
                context ->
                bluemarble(context.sender as Player)
            }
            then("start"){
                executes {
                    context ->
                    start(context.sender as Player)
                }
            }
            then("stop"){
                executes {
                    context ->
                    stop(context.sender as Player)
                }
                then("confirm"){
                    executes {
                        context ->
                        stopConfirm(context.sender as Player)
                    }
                }
            }
        }
    }
    private val prefix: String = "${ChatColor.GOLD}[BlueMarble] ${ChatColor.YELLOW}"

    private fun bluemarble(sender: Player){
        if (sender.isOp){
            sender.sendMessage("${prefix}사용법 - /bluemarble [start | stop]")
        }else{
            sender.sendMessage("${ChatColor.RED}권한이 없습니다.")
        }
    }
    private fun start(sender: Player) {
        if (sender.isOp){
            TODO("게임 시작")
        }else{
            sender.sendMessage("${ChatColor.RED}권한이 없습니다.")
        }
    }
    private fun stop(sender: Player){
        if (sender.isOp){
            sender.sendMessage("${prefix}부루마불을 강제종료 하려면 /bluemarble stop confirm 을 입력해주세요")
        }else{
            sender.sendMessage("${ChatColor.RED}권한이 없습니다.")
        }

    }
    private fun stopConfirm(sender: Player){
        if (sender.isOp){
            TODO("게임 강제종료")
        }else{
            sender.sendMessage("${ChatColor.RED}권한이 없습니다.")
        }
    }
}