package me.emmy.basic.managers.flat;

import me.emmy.basic.Basic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ChatFilterManagerFlat {
    private final Map<UUID, Boolean> chatFilterSettings;
    private final Set<String> bannedWords;
    private final FileConfiguration config;

    public ChatFilterManagerFlat() {
        this.chatFilterSettings = new HashMap<>();
        this.bannedWords = new HashSet<>();
        this.config = Basic.getInstance().getConfig();
    }

    public void setChatFilterStatus(Player player, boolean enabled) {
        chatFilterSettings.put(player.getUniqueId(), enabled);
        config.set("player-settings." + player.getUniqueId() + ".chat-filter", enabled);
    }

    public boolean getChatFilterStatus(Player player) {
        return chatFilterSettings.getOrDefault(player.getUniqueId(), true);
    }

    public void loadFromConfig() {
        if (config.contains("player-settings")) {
            config.getConfigurationSection("player-settings").getKeys(false).forEach(playerId -> {
                UUID uuid = UUID.fromString(playerId);
                boolean chatFilterEnabled = config.getBoolean("player-settings." + playerId + ".chat-filter", true);
                chatFilterSettings.put(uuid, chatFilterEnabled);
            });
        }

        loadBannedWords();
    }

    public void saveToConfig() {
        for (Map.Entry<UUID, Boolean> entry : chatFilterSettings.entrySet()) {
            config.set("player-settings." + entry.getKey() + ".chat-filter", entry.getValue());
        }
        Basic.getInstance().saveConfig();
    }

    public void loadBannedWords() {
        if (config.contains("chat-filter.banned-words")) {
            bannedWords.addAll(config.getStringList("chat-filter.banned-words"));
        }
    }

    public boolean isWordBanned(String word) {
        return bannedWords.contains(word.toLowerCase());
    }

    /*public void addBannedWord(String word) {
        bannedWords.add(word.toLowerCase());
        saveBannedWords();
    }

    public void removeBannedWord(String word) {
        bannedWords.remove(word.toLowerCase());
        saveBannedWords();
    }

    private void saveBannedWords() {
        config.set("chat-filter.banned-words", new HashSet<>(bannedWords));
        Basic.getInstance().saveConfig();
    }*/
}
