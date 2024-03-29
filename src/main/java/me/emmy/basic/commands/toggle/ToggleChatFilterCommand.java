package me.emmy.basic.commands.toggle;

import me.emmy.basic.Basic;
import me.emmy.basic.utils.chat.CC;
import me.emmy.basic.utils.command.BaseCommand;
import me.emmy.basic.utils.command.Command;
import me.emmy.basic.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class ToggleChatFilterCommand extends BaseCommand {

    @Override
    @Command(name = "togglechatfilter", aliases = {"tcf"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        boolean chatFilterEnabled = !Basic.getInstance().getChatFilterManagerFlat().getChatFilterStatus(player);
        Basic.getInstance().getChatFilterManagerFlat().setChatFilterStatus(player, chatFilterEnabled);
        Basic.getInstance().getChatFilterManagerFlat().saveToConfig();

        String enabled = Basic.getInstance().getConfig().getString("chat-filter.format.enabled");
        String disabled = Basic.getInstance().getConfig().getString("chat-filter.format.disabled");

        player.sendMessage(CC.translate(Basic.getInstance().getConfig().getString("chat-filter.status-changed").replace("{status}", chatFilterEnabled ? enabled : disabled)));
    }
}