package com.github.chal2andev.bluemarble.plugin

import com.github.chal2andev.bluemarble.command.BlueMarbleCommand
import com.github.chal2andev.bluemarble.invfx.BlueMarbleInvFX
import com.github.chal2andev.bluemarble.system.Progress
import com.github.monun.kommand.kommand
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class BlueMarblePlugin: JavaPlugin(){

    val loadfile: File = File(dataFolder, "players.yml")
    val loadconfig: FileConfiguration = YamlConfiguration.loadConfiguration(file)

    override fun onEnable() {
        loadConfig()
        setupCommands()
        setupInitModule()
        registerEvents()
    }

    override fun onDisable() {
        saveConfig()
    }
    private fun loadConfig(){
        loadfile.createNewFile()
        config.options().copyDefaults(true)
        saveConfig()
    }
    private fun setupCommands(){
        BlueMarbleCommand.initModule(this)
        kommand {
            BlueMarbleCommand.register(this)
        }
    }
    private fun setupInitModule(){
        Progress.initModule(this)
        BlueMarbleInvFX.initModule(this)
        BlueMarbleEvent().initModule(this)
    }
    private fun registerEvents(){
        server.pluginManager.registerEvents(BlueMarbleEvent(), this)
    }
}