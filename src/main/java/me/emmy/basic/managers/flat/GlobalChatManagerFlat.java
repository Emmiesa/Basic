package me.emmy.basic.managers.flat;

import me.emmy.basic.Basic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Emmy
 * Project: Basic
 * Date: 27/03/2024 - 22:36
 */

public class GlobalChatManagerFlat {
    private final Map<UUID, Boolean> globalChatSettings;
    private final FileConfiguration config;

    public GlobalChatManagerFlat() {
        this.globalChatSettings = new HashMap<>();
        this.config = Basic.getInstance().getConfig();
    }

    public void setGlobalChatStatus(Player player, boolean enabled) {
        globalChatSettings.put(player.getUniqueId(), enabled);
        config.set("player-settings." + player.getUniqueId() + ".global-chat", !enabled);
    }

    public boolean getGlobalChatStatus(Player player) {
        return globalChatSettings.getOrDefault(player.getUniqueId(), true);
    }

    public void loadFromConfig() {
        if (config.contains("player-settings")) {
            config.getConfigurationSection("player-settings").getKeys(false).forEach(playerId -> {
                UUID uuid = UUID.fromString(playerId);
                boolean globalChatEnabled = config.getBoolean("player-settings." + playerId + ".global-chat", true);
                globalChatSettings.put(uuid, globalChatEnabled);
            });
        }
    }

    public void saveToConfig() {
        for (Map.Entry<UUID, Boolean> entry : globalChatSettings.entrySet()) {
            config.set("player-settings." + entry.getKey() + ".global-chat", entry.getValue());
        }
        Basic.getInstance().saveConfig();
    }
}
