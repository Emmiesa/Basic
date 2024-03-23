package me.emmy.basic.utils.command;

import me.emmy.basic.Basic;

public abstract class BaseCommand {

    public Basic main = Basic.getInstance();

    public BaseCommand() {
        main.getFramework().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs command);

}
