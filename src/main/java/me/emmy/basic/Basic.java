package me.emmy.basic;

import lombok.Getter;
import lombok.Setter;
import me.emmy.basic.commands.menu.ChangelogCommand;
import me.emmy.basic.commands.global.JoinCommand;
import me.emmy.basic.commands.menu.SettingsCommand;
import me.emmy.basic.commands.toggle.ToggleChatFilterCommand;
import me.emmy.basic.commands.toggle.ToggleGlobalChat;
import me.emmy.basic.commands.socials.*;
import me.emmy.basic.listener.ChatListener;
import me.emmy.basic.managers.flat.ChatFilterManagerFlat;
import me.emmy.basic.managers.flat.GlobalChatManagerFlat;
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
    private GlobalChatManagerFlat globalChatManagerFlat;
    private ChatFilterManagerFlat chatFilterManagerFlat;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        registerManagers();
        registerListeners();
        registerCommands();

        CC.on();
    }

    @Override
    public void onDisable() {
        CC.off();
    }

    private void registerManagers() {
        framework = new CommandFramework(this);

        globalChatManagerFlat = new GlobalChatManagerFlat();
        globalChatManagerFlat.loadFromConfig();

        chatFilterManagerFlat = new ChatFilterManagerFlat();
        chatFilterManagerFlat.loadFromConfig();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        getServer().getPluginManager().registerEvents(new ChatListener(globalChatManagerFlat, chatFilterManagerFlat), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    private void registerCommands() {
        new ToggleGlobalChat();
        new ToggleChatFilterCommand();

        new ChangelogCommand();
        new SettingsCommand();

        new DiscordCommand();
        new TwitterCommand();
        new WebsiteCommand();
        new YoutubeCommand();
        new TiktokCommand();
        new StoreCommand();
        new JoinCommand();
    }
}
