package me.emmy.basic.commands;

import me.emmy.basic.Basic;
import me.emmy.basic.player.PlayerSettingsManager;
import me.emmy.basic.utils.chat.CC;
import me.emmy.basic.utils.command.BaseCommand;
import me.emmy.basic.utils.command.Command;
import me.emmy.basic.utils.command.CommandArgs;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Emmy
 * Project: Basic
 * Date: 23/03/2024 - 11:42
 */

public class ToggleGlobalChat extends BaseCommand {
    private final PlayerSettingsManager settingsManager;

    public ToggleGlobalChat(PlayerSettingsManager settingsManager) {
        this.settingsManager = settingsManager;
    }

    @Override
    @Command(name = "toggleglobalchat", aliases = {"tgc"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        boolean globalChatEnabled = !settingsManager.getGlobalChatStatus(player);
        settingsManager.setGlobalChatStatus(player, globalChatEnabled);

        settingsManager.saveToConfig();

        String enabled = Basic.getInstance().getConfig().getString("chat.format.enabled");
        String disabled = Basic.getInstance().getConfig().getString("chat.format.disabled");

        player.sendMessage(CC.translate(Basic.getInstance().getConfig().getString("chat.status-changed").replace("{status}", globalChatEnabled ? enabled : disabled)));
    }
}
