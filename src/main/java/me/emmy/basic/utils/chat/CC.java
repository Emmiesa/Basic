package me.emmiesa.flowercore.utils.chat;

import me.emmiesa.flowercore.FlowerCore;
import me.emmiesa.flowercore.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Emmy
 * Project: FlowerCore
 * Discord: dsc.gg/emmiesa
 */

public class CC {

    public static final String BLUE;
    public static final String AQUA;
    public static final String YELLOW;
    public static final String RED;
    public static final String GRAY;
    public static final String GOLD;
    public static final String GREEN;
    public static final String WHITE;
    public static final String BLACK;
    public static final String BOLD;
    public static final String ITALIC;
    public static final String UNDER_LINE;
    public static final String STRIKE_THROUGH;
    public static final String RESET;
    public static final String MAGIC;
    public static final String DARK_BLUE;
    public static final String DARK_AQUA;
    public static final String DARK_GRAY;
    public static final String DARK_GREEN;
    public static final String DARK_PURPLE;
    public static final String DARK_RED;
    public static final String PINK;
    public static final String MENU_BAR;
    public static final String CHAT_BAR;
    public static final String SB_BAR;
    public static final String TAB_BAR;
    public static final String FLOWER_BAR;
    public static final String FLOWER_BAR_LONG;
    public static final String FLOWER_BAR_VERY_LONG;
    private static final Map<String, ChatColor> MAP;

    static {
        MAP = new HashMap<>();
        MAP.put("pink", ChatColor.LIGHT_PURPLE);
        MAP.put("orange", ChatColor.GOLD);
        MAP.put("purple", ChatColor.DARK_PURPLE);

        for (ChatColor chatColor : ChatColor.values()) {
            MAP.put(chatColor.name().toLowerCase().replace("_", ""), chatColor);
        }

        BLUE = ChatColor.BLUE.toString();
        AQUA = ChatColor.AQUA.toString();
        YELLOW = ChatColor.YELLOW.toString();
        RED = ChatColor.RED.toString();
        GRAY = ChatColor.GRAY.toString();
        GOLD = ChatColor.GOLD.toString();
        GREEN = ChatColor.GREEN.toString();
        WHITE = ChatColor.WHITE.toString();
        BLACK = ChatColor.BLACK.toString();
        BOLD = ChatColor.BOLD.toString();
        ITALIC = ChatColor.ITALIC.toString();
        UNDER_LINE = ChatColor.UNDERLINE.toString();
        STRIKE_THROUGH = ChatColor.STRIKETHROUGH.toString();
        RESET = ChatColor.RESET.toString();
        MAGIC = ChatColor.MAGIC.toString();
        DARK_BLUE = ChatColor.DARK_BLUE.toString();
        DARK_AQUA = ChatColor.DARK_AQUA.toString();
        DARK_GRAY = ChatColor.DARK_GRAY.toString();
        DARK_GREEN = ChatColor.DARK_GREEN.toString();
        DARK_PURPLE = ChatColor.DARK_PURPLE.toString();
        DARK_RED = ChatColor.DARK_RED.toString();
        PINK = ChatColor.LIGHT_PURPLE.toString();
        MENU_BAR = ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------";
        CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------------------------------";
        SB_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------------";
        TAB_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------";

        FLOWER_BAR = translate("&b&lೋღ&b&l&m«-------&f&l&m-------&b&l&m-------»&r&b&lღೋ");
        FLOWER_BAR_LONG = translate("&b&lೋღ&b&l&m«-------&f&l&m-----------------&b&l&m-------»&r&b&lღೋ");
        FLOWER_BAR_VERY_LONG = translate("&b&lೋღ&b&l&m«-------&f&l&m----------------------------&b&l&m-------»&r&b&lღೋ");
    }

    public static void sendError(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(CC.translate("[CC Util sendError] &c" + message + "!"));  //In Player Manager class!
    }

    public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static List<String> translate(List<String> lines) {
        List<String> toReturn = new ArrayList<>();

        for (String line : lines) {
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        return toReturn;
    }

    public static List<String> translate(String[] lines) {
        List<String> toReturn = new ArrayList<>();

        for (String line : lines) {
            if (line != null) {
                toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
            }
        }

        return toReturn;
    }

    public static void listRanks() {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate("&fAll registered FlowerCore ranks:"));
        FileConfiguration ranksConfig = FlowerCore.getInstance().getConfig("ranks.yml");
        for (String rankName : ranksConfig.getConfigurationSection("ranks").getKeys(false)) {
            String displayName = ranksConfig.getString("ranks." + rankName + ".displayName", rankName);
            Bukkit.getConsoleSender().sendMessage(CC.translate(" &f- &b" + displayName));
        }
    }

    public static void on(long timeTaken) {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8&m-----------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Plugin: &b" + FlowerCore.getInstance().getDescription().getName()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Authors: &b" + FlowerCore.getInstance().getDescription().getAuthors().toString().replace("[", "").replace("]", "")));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" "));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Version: &b" + FlowerCore.getInstance().getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Link: &b" + FlowerCore.getInstance().getDescription().getWebsite()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" "));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &7| Bukkit Server Name: &b" + Bukkit.getServerName()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Server Name: &b" + Locale.SERVER_NAME));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Spigot: &b" + Bukkit.getName()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" "));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Ranks: &b" + FlowerCore.getInstance().getConfig("ranks.yml").getConfigurationSection("ranks").getKeys(false).size()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Tags: &b" + "0"));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" "));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &f| Load time: &b" + (timeTaken) + " &bms"));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8&m-----------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(" ");
    }

    public static void off() {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(CC.translate("&8[&bFlowerCore&8] &fDisabled &bFlowerCore&f!"));
        Bukkit.getConsoleSender().sendMessage(" ");
    }
}