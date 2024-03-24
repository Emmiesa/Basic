package me.emmy.basic.commands;

import me.emmy.basic.Basic;
import me.emmy.basic.utils.chat.CC;
import me.emmy.basic.utils.command.BaseCommand;
import me.emmy.basic.utils.command.Command;
import me.emmy.basic.utils.command.CommandArgs;
import org.bukkit.command.CommandSender;

/**
 * Created by Emmy
 * Project: Basic
 * Date: 23/03/2024 - 01:25
 */

public class StoreCommand extends BaseCommand {
    @Override
    @Command(name = "store", aliases = "buy" ,inGameOnly = false)
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();

        sender.sendMessage(CC.translate(Basic.getInstance().getConfig().getString("commands.store")));
    }
}