package me.emmy.basic.commands;

import me.emmy.basic.menus.changelog.ChangelogMenu;
import me.emmy.basic.utils.command.BaseCommand;
import me.emmy.basic.utils.command.Command;
import me.emmy.basic.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by Emmy
 * Project: Basic
 * Date: 23/03/2024 - 01:45
 */

public class ChangelogCommand extends BaseCommand {
    @Override
    @Command(name = "changelog", aliases = {"news"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        new ChangelogMenu().openMenu(player);
    }
}