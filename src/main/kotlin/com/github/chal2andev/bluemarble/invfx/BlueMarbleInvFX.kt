package com.github.chal2andev.bluemarble.invfx

import com.github.chal2andev.bluemarble.plugin.BlueMarblePlugin
import com.github.chal2andev.bluemarble.system.Progress
import com.github.monun.invfx.InvFX
import com.github.monun.invfx.InvScene
import net.md_5.bungee.api.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object BlueMarbleInvFX {
    private lateinit var plugin: BlueMarblePlugin
    fun initModule(plugin: BlueMarblePlugin){
        this.plugin = plugin
    }
    private val oneButton = ItemStack(Material.ENCHANTED_BOOK).apply { setDisplayName("${ChatColor.GOLD}${ChatColor.BOLD}첫번째") }
    private val twoButton = ItemStack(Material.ENCHANTED_BOOK).apply { setDisplayName("${ChatColor.GOLD}${ChatColor.BOLD}두번째") }
    private val threeButton = ItemStack(Material.ENCHANTED_BOOK).apply { setDisplayName("${ChatColor.GOLD}${ChatColor.BOLD}세번째") }
    private val fourButton = ItemStack(Material.ENCHANTED_BOOK).apply { setDisplayName("${ChatColor.GOLD}${ChatColor.BOLD}네번째") }

    private var clickOne: Boolean = false
    private var clickTwo: Boolean = false
    private var clickThree: Boolean = false
    private var clickFour: Boolean = false

    fun cardGUI(player: Player): InvScene{
        return InvFX.scene(1, "${ChatColor.BLUE}${ChatColor.BOLD}순서 정하기"){
            plugin.loadconfig.set("${player.name}.openInv", true)
            plugin.loadconfig.save(plugin.loadfile)

            panel(0,0, 9, 1){
                if(plugin.loadconfig.getInt("players") == 2) {
                    button(3, 0) {
                        onInit {
                            it.item = oneButton
                        }
                        onClick{_, _ ->
                            clickOneButton(player)
                        }
                    }
                    button(5, 0) {
                        onInit {
                            it.item = twoButton
                        }
                        onClick { _, _ ->
                            clickTwoButton(player)
                        }
                    }
                }else if(plugin.loadconfig.getInt("players") == 3){
                    button(2, 0) {
                        onInit {
                            it.item = oneButton
                        }
                        onClick { _, _ ->
                            clickOneButton(player)
                        }
                    }
                    button(4, 0) {
                        onInit {
                            it.item = twoButton
                        }
                        onClick { _, _ ->
                            clickTwoButton(player)
                        }
                    }
                    button(6, 0) {
                        onInit {
                            it.item = threeButton
                        }
                        onClick { _, _ ->
                            clickThreeButton(player)
                        }
                    }
                }else if(plugin.loadconfig.getInt("players") == 4){
                    button(1, 0) {
                        onInit {
                            it.item = oneButton
                        }
                        onClick { _, _ ->
                            clickOneButton(player)
                        }
                    }
                    button(3, 0) {
                        onInit {
                            it.item = twoButton
                        }
                        onClick { _, _ ->
                            clickTwoButton(player)
                        }
                    }
                    button(5, 0) {
                        onInit {
                            it.item = threeButton
                        }
                        onClick { _, _ ->
                            clickThreeButton(player)
                        }
                    }
                    button(7, 0) {
                        onInit {
                            it.item = fourButton
                        }
                        onClick { _, _ ->
                            clickFourButton(player)
                        }
                    }
                }
            }
        }
    }
    private fun clickOneButton(player: Player){
        if (!clickOne){
            clickOne = true
            player.closeInventory()
            player.sendTitle("","${ChatColor.YELLOW}첫번재 순서 입니다.",0, 40, 10)
            player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f)
            plugin.loadconfig.set("${player.name}.openInv", false)
            plugin.loadconfig.set("${player.name}.turn", 1)
            plugin.loadconfig.save(plugin.loadfile)
        }else{
            player.sendMessage("${ChatColor.YELLOW}이미 누군가 선택했습니다!")
        }
    }
    private fun clickTwoButton(player: Player){
        if (!clickTwo){
            clickTwo = true
            player.closeInventory()
            player.sendTitle("","${ChatColor.YELLOW}두번째 순서 입니다.",0, 40, 10)
            player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f)
            plugin.loadconfig.set("${player.name}.openInv", false)
            plugin.loadconfig.set("${player.name}.turn", 2)
            plugin.loadconfig.save(plugin.loadfile)
        }else{
            player.sendMessage("${ChatColor.YELLOW}이미 누군가 선택했습니다!")
        }
    }
    private fun clickThreeButton(player: Player){
        if (!clickThree){
            clickThree = true
            player.closeInventory()
            player.sendTitle("","${ChatColor.YELLOW}세번재 순서 입니다.",0, 40, 10)
            player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f)
            plugin.loadconfig.set("${player.name}.openInv", false)
            plugin.loadconfig.set("${player.name}.turn", 3)
            plugin.loadconfig.save(plugin.loadfile)
        }else{
            player.sendMessage("${ChatColor.YELLOW}이미 누군가 선택했습니다!")
        }
    }
    private fun clickFourButton(player: Player){
        if (!clickFour){
            clickFour = true
            player.closeInventory()
            player.sendTitle("","${ChatColor.YELLOW}네번재 순서 입니다.",0, 40, 10)
            player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f)
            plugin.loadconfig.set("${player.name}.openInv", false)
            plugin.loadconfig.set("${player.name}.turn", 4)
            plugin.loadconfig.save(plugin.loadfile)
        }else{
            player.sendMessage("${ChatColor.YELLOW}이미 누군가 선택했습니다!")
        }
    }
    private fun ItemStack.setDisplayName(name: String){
        itemMeta = itemMeta.apply { this.setDisplayName(name) }
    }
}