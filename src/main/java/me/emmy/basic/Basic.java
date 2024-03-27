package me.emmy.basic;

import lombok.Getter;
import lombok.Setter;
import me.emmy.basic.commands.*;
import me.emmy.basic.listener.ChatListener;
import me.emmy.basic.player.PlayerSettingsManager;
import me.emmy.basic.utils.chat.CC;
import me.emmy.basic.utils.command.CommandFramework;
import me.emmy.basic.utils.menu.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Basic extends JavaPlugin {

    @Getter
    public static Basic instance;
    private CommandFramework framework;
    private PlayerSettingsManager settingsManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        framework = new CommandFramework(this);

        settingsManager = new PlayerSettingsManager();
        settingsManager.loadFromConfig();

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(settingsManager), this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        new ChangelogCommand();
        new SettingsCommand();

        new ToggleGlobalChat(settingsManager);

        new DiscordCommand();
        new TwitterCommand();
        new WebsiteCommand();
        new YoutubeCommand();
        new TiktokCommand();
        new StoreCommand();
        new JoinCommand();

        CC.on();
    }

    @Override
    public void onDisable() {
        CC.off();
    }
}
