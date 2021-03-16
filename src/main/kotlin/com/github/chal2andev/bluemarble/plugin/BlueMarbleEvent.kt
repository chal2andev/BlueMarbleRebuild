package com.github.chal2andev.bluemarble.plugin

import com.github.chal2andev.bluemarble.invfx.BlueMarbleInvFX
import com.github.monun.invfx.openWindow
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerJoinEvent

class BlueMarbleEvent: Listener {

    private lateinit var plugin: BlueMarblePlugin
    internal fun initModule(plugin: BlueMarblePlugin){
        this.plugin = plugin
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent){
        val player: Player = event.player
        if (Bukkit.getOnlinePlayers().size > 4){
            player.kickPlayer("플레이어가 너무 많습니다.")
        }
    }
    @EventHandler
    fun onCloseCardInv(event: InventoryCloseEvent){
        val player: Player = event.player as Player
        val open = plugin.loadconfig.getBoolean("${player.name}.openInv")
        player.sendMessage("닫음")
        if (open) {
            player.openWindow(BlueMarbleInvFX.cardGUI(player))
        }
    }
}