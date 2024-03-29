package me.emmy.basic.listener;

import me.emmy.basic.Basic;
import me.emmy.basic.managers.flat.ChatFilterManagerFlat;
import me.emmy.basic.managers.flat.GlobalChatManagerFlat;
import me.emmy.basic.utils.chat.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChatListener implements Listener {
    private final GlobalChatManagerFlat globalChatManager;
    private final ChatFilterManagerFlat filterManager;

    public ChatListener(GlobalChatManagerFlat globalChatManager, ChatFilterManagerFlat filterManager) {
        this.globalChatManager = globalChatManager;
        this.filterManager = filterManager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!globalChatManager.getGlobalChatStatus(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(Basic.getInstance().getConfig().getString("global-chat.disabled")));
        } else {
            List<Player> recipients = new ArrayList<>(event.getRecipients());
            for (Player recipient : recipients) {
                if (!globalChatManager.getGlobalChatStatus(recipient)) {
                    event.getRecipients().remove(recipient);
                }
            }
        }

        if (filterManager.getChatFilterStatus(player)) {
            String message = event.getMessage();
            String[] words = message.split("\\s+");

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (filterManager.isWordBanned(word)) {
                    words[i] = censorWord(word);
                }
            }

            String censoredMessage = Arrays.stream(words).collect(Collectors.joining(" "));
            event.setMessage(censoredMessage);
        }
    }

    private String censorWord(String word) {
        StringBuilder censoredWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            censoredWord.append('*');
        }
        return censoredWord.toString();
    }
}
