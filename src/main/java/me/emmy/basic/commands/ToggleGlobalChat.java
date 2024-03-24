package me.emmy.basic.commands;

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
    }
}