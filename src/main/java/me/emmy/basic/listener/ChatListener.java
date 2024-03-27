package me.emmy.basic.listener;

import me.emmy.basic.Basic;
import me.emmy.basic.player.PlayerSettingsManager;
import me.emmy.basic.utils.chat.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emmy
 * Project: Basic
 * Date: 27/03/2024 - 22:48
 */

public class ChatListener implements Listener {
    private final PlayerSettingsManager settingsManager;

    public ChatListener(PlayerSettingsManager settingsManager) {
        this.settingsManager = settingsManager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!settingsManager.getGlobalChatStatus(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(Basic.getInstance().getConfig().getString("chat.disabled")));
        } else {
            List<Player> recipients = new ArrayList<>(event.getRecipients());
            for (Player recipient : recipients) {
                if (!settingsManager.getGlobalChatStatus(recipient)) {
                    event.getRecipients().remove(recipient);
                }
            }
        }
    }
}