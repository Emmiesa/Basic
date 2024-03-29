package me.emmy.basic.commands.toggle;

import me.emmy.basic.Basic;
import me.emmy.basic.utils.chat.CC;
import me.emmy.basic.utils.command.BaseCommand;
import me.emmy.basic.utils.command.Command;
import me.emmy.basic.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by Emmy
 * Project: Basic
 * Date: 23/03/2024 - 11:42
 */

public class ToggleGlobalChat extends BaseCommand {

    @Override
    @Command(name = "toggleglobalchat", aliases = {"tgc"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        boolean globalChatEnabled = !Basic.getInstance().getGlobalChatManagerFlat().getGlobalChatStatus(player);
        Basic.getInstance().getGlobalChatManagerFlat().setGlobalChatStatus(player, globalChatEnabled);

        Basic.getInstance().getGlobalChatManagerFlat().saveToConfig();

        String enabled = Basic.getInstance().getConfig().getString("global-chat.format.enabled");
        String disabled = Basic.getInstance().getConfig().getString("global-chat.format.disabled");

        player.sendMessage(CC.translate(Basic.getInstance().getConfig().getString("global-chat.status-changed").replace("{status}", globalChatEnabled ? enabled : disabled)));
    }
}
