package me.emmy.basic.utils.others;

import me.emmy.basic.Basic;
import me.emmy.basic.utils.chat.CC;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Emmy
 * Project: FlowerCore
 * Date: 24/03/2024 - 12:17
 */

public class JoinCommandUtil {

    public static void sendPlayer(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
        }
        player.sendMessage(CC.translate(Basic.getInstance().getConfig().getString("join.joining").replace("{server}", server)));
        player.sendPluginMessage(Basic.getInstance(), "BungeeCord", b.toByteArray());
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage(CC.translate(Basic.getInstance().getConfig().getString("join.failed").replace("{server}", server)));
            }
        };
        task.runTaskLater(Basic.getInstance(), 20);
    }
}