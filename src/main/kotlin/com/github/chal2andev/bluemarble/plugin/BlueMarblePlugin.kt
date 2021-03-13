package com.github.chal2andev.bluemarble.plugin

import com.github.chal2andev.bluemarble.command.BlueMarbleCommand
import com.github.monun.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin

class BlueMarblePlugin: JavaPlugin(){
    override fun onEnable() {
        loadConfig()
        setupCommands()
    }
    private fun loadConfig(){
        config.options().copyDefaults(true)
        saveConfig()
    }
    private fun setupCommands(){
        BlueMarbleCommand.initModule(this)
        kommand {
            BlueMarbleCommand.register(this)
        }
    }
}