package com.github.chal2andev.bluemarble.system

import com.github.chal2andev.bluemarble.command.BlueMarbleCommand
import com.github.chal2andev.bluemarble.invfx.BlueMarbleInvFX
import com.github.chal2andev.bluemarble.plugin.BlueMarblePlugin
import com.github.monun.invfx.openWindow
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.scoreboard.*

object Progress {
    private var rungame: Boolean = false
    private lateinit var plugin: BlueMarblePlugin
    internal fun initModule(plugin: BlueMarblePlugin){
        this.plugin = plugin
    }

    fun onStart(sender: Player){
        when (getOnlinePlayersCount()) {
            0 -> {
                sender.sendMessage("${BlueMarbleCommand.prefix}플레이어가 부족합니다.")
            }
            1 -> {
                if (!rungame) {
                    sender.sendMessage("${BlueMarbleCommand.prefix}게임이 시작됩니다.")
                    readyStart(true)
                }else{
                    sender.sendMessage("${BlueMarbleCommand.prefix}이미 시작되어 있습니다.")
                }
            }
            else -> {
                if (!rungame) {
                    sender.sendMessage("${BlueMarbleCommand.prefix}게임이 시작됩니다.")
                    readyStart(false)
                }else{
                    sender.sendMessage("${BlueMarbleCommand.prefix}이미 시작되어 있습니다.")
                }
            }
        }
    }
    private fun readyStart(pvp: Boolean){
        rungame = true
        val scheduler = Bukkit.getScheduler()
        for(players: Player in Bukkit.getOnlinePlayers()){
            plugin.loadconfig.set("players", Bukkit.getOnlinePlayers().size)
            plugin.loadconfig.save(plugin.loadfile) // TODO 개발후 삭제
            players.sendTitle("${ChatColor.of("#0097FF")}BLUE MARBLE", "", 40,60,30)
        }
        scheduler.scheduleSyncDelayedTask(plugin, Runnable(){
                for(players: Player in Bukkit.getOnlinePlayers()){
                    players.sendTitle("", "${ChatColor.GOLD}기본 게임머니가 주어졌습니다.", 10,40,10)
                    playDing(players, players.location)
                    setMoney(players, pvp)
                }
        }, 130)
        scheduler.scheduleSyncDelayedTask(plugin, Runnable(){
            for(players: Player in Bukkit.getOnlinePlayers()){
                players.sendTitle("", "${ChatColor.GOLD}다른 플레이어보다 더 많은 땅을 구매하여 승리하세요!", 10,40,10)
                playDing(players, players.location)
            }
        }, 190)
        scheduler.scheduleSyncDelayedTask(plugin, Runnable(){
            for(players: Player in Bukkit.getOnlinePlayers()){
                players.sendTitle("", "${ChatColor.GOLD}순서를 정하기 선착순으로 카드를 뽑습니다..", 10,40,10)
                playDing(players, players.location)
            }
        }, 250)
        scheduler.scheduleSyncDelayedTask(plugin, Runnable(){
            for(players: Player in Bukkit.getOnlinePlayers()){
                players.openWindow(BlueMarbleInvFX.cardGUI(players))
            }
        }, 290)
    }
    fun onStop(){

    }
    private fun playDing(player: Player, location: Location){
        player.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f)
    }
    private fun setMoney(players: Player, pvp: Boolean){
        val score: ScoreboardManager = Bukkit.getScoreboardManager()
        val board: Scoreboard = score.newScoreboard
        val obj: Objective = board.registerNewObjective("money", "dummy")
        obj.displaySlot = DisplaySlot.SIDEBAR
        obj.displayName = "${ChatColor.AQUA}${ChatColor.BOLD}MONEY"
        for (online: Player in Bukkit.getServer().onlinePlayers){
            val money: Score = obj.getScore(online)
            if (pvp) {
                val configmoney = plugin.config.getInt("money.pvp")
                plugin.loadconfig.set("${players.name}.money", configmoney)
                money.score = configmoney
            }else{
                val configmoney = plugin.config.getInt("money.default")
                plugin.loadconfig.set("${players.name}.money", configmoney)
                money.score = configmoney
            }
            online.scoreboard = board
        }
        plugin.loadconfig.save(plugin.loadfile) // TODO 개발후 삭제
    }
    private fun rolldice(): Int{
        return (Math.random() * 6 + 1).toInt()
    }
    fun getFirstDice(player: Player){
        val dice = rolldice()
        player.sendMessage("주사위 : $dice")
        player.closeInventory()
    }
    private fun getOnlinePlayersCount(): Int? {
        return when {
            Bukkit.getOnlinePlayers().size == 1 -> { //플레이어가 혼자일때
                0
            }
            Bukkit.getOnlinePlayers().size == 2 -> { //플레이어가 2명일때
                1
            }
            Bukkit.getOnlinePlayers().size > 2 -> { //플레이어가 3명 이상일때
                2
            }
            else -> null
        }
    }
}