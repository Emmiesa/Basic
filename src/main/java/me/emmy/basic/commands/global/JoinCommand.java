package me.emmy.basic.commands.global;

import me.emmy.basic.Basic;
import me.emmy.basic.utils.others.JoinCommandUtil;
import me.emmy.basic.utils.chat.CC;
import me.emmy.basic.utils.command.BaseCommand;
import me.emmy.basic.utils.command.Command;
import me.emmy.basic.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by Emmy
 * Project: Basic
 * Date: 24/03/2024 - 13:10
 */

public class JoinCommand extends BaseCommand {

    @Override
    @Command(name = "join", aliases = {"joinserver"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        if (command.length() == 0) {
            player.sendMessage(CC.translate(Basic.getInstance().getConfig().getString("join.usage")));
            return;
        }

        String server = command.getArgs(0);
        JoinCommandUtil.sendPlayer(player, server);
    }
}