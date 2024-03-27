package me.emmy.basic.commands;

import me.emmy.basic.menus.settings.SettingsMenu;
import me.emmy.basic.utils.command.BaseCommand;
import me.emmy.basic.utils.command.Command;
import me.emmy.basic.utils.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * Created by Emmy
 * Project: Basic
 * Date: 23/03/2024 - 11:41
 */

public class SettingsCommand extends BaseCommand {
    @Override
    @Command(name = "settings", aliases = {"preferences"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        new SettingsMenu().openMenu(player);
    }
}