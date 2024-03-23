package me.emmy.basic;

import lombok.Getter;
import lombok.Setter;
import me.emmy.basic.commands.menus.ChangelogCommand;
import me.emmy.basic.commands.socials.*;
import me.emmy.basic.utils.command.CommandFramework;
import me.emmy.basic.utils.menu.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Basic extends JavaPlugin {

    @Getter
    public static Basic instance;
    private CommandFramework framework;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        framework = new CommandFramework(this);

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        new ChangelogCommand();

        new DiscordCommand();
        new StoreCommand();
        new TiktokCommand();
        new TwitterCommand();
        new WebsiteCommand();
        new YoutubeCommand();

    }

    @Override
    public void onDisable() {

    }
}
