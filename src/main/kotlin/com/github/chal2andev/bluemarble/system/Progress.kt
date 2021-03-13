package com.github.chal2andev.bluemarble.system

import com.github.chal2andev.bluemarble.plugin.BlueMarblePlugin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class Progress {
    private lateinit var plugin: BlueMarblePlugin
    private var ticks = 0
    internal fun initModule(plugin: BlueMarblePlugin){
        this.plugin = plugin
    }

    fun onStart(){
        ticks++
        if(20 == ticks){
            for(players: Player in Bukkit.getServer().onlinePlayers){
                players.sendTitle("${ChatColor.AQUA}BLUE MARBLE", "", 40, 60, 40)
            }
        }
        if (160 == ticks){
            for(players: Player in Bukkit.getServer().onlinePlayers){
                players.sendTitle("", "${ChatColor.YELLOW}기본 게임머니가 주어졌습니다.", 40, 60, 40)
            }
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, Runnable(){
            onStart()
        }, 1)
    }
    fun onStop(){

    }
}