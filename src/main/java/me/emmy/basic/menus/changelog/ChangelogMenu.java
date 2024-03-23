package me.emmy.basic.menus.changelog;

import me.clip.placeholderapi.PlaceholderAPI;
import me.emmy.basic.Basic;
import me.emmy.basic.utils.chat.CC;
import me.emmy.basic.utils.menu.Button;
import me.emmy.basic.utils.menu.Menu;
import me.emmy.basic.utils.menu.button.RefillGlassButton;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Emmy
 * Project: FlowerHub
 */

public class ChangelogMenu extends Menu {

	private final RefillGlassButton refillGlassButton;

	public ChangelogMenu() {
		this.refillGlassButton = new RefillGlassButton(
				Material.STAINED_GLASS_PANE,
				Basic.getInstance().getConfig().getInt("changelog-menu.refill-glass.data", 15)
		);
	}

	@Override
	public String getTitle(Player player) {
		return CC.translate(Basic.getInstance().getConfig().getString("changelog-menu.title"));
	}

	@Override
	public Map<Integer, Button> getButtons(Player player) {
		Map<Integer, Button> buttons = new HashMap<>();

		ConfigurationSection serversSection = Basic.getInstance().getConfig().getConfigurationSection("changelog-menu.items");

		if (serversSection != null) {
			for (String serverKey : serversSection.getKeys(false)) {
				ConfigurationSection serverSection = serversSection.getConfigurationSection(serverKey);

				if (serverSection != null) {
					int slot = serverSection.getInt("slot", 0);
					Material materialType = Material.matchMaterial(serverSection.getString("material", "STONE"));
					String name = CC.translate(serverSection.getString("name", "&c&lInvalid"));
					List<String> lore = serverSection.getStringList("lore").stream()
							.map(line -> CC.translate(PlaceholderAPI.setPlaceholders(player, line)))
							.collect(Collectors.toList());
					int data = serverSection.getInt("data", 0);
					Material material = new MaterialData(materialType, (byte) data).toItemStack().getType();

					buttons.put(slot, new ChangelogButton(material, (short) data, name, lore, serverSection.getString("command")));
				}
			}
		}

		ConfigurationSection refillGlassSection = Basic.getInstance().getConfig().getConfigurationSection("changelog-menu.refill-glass");
		if (refillGlassSection != null && refillGlassSection.getBoolean("enabled", true)) {
			List<String> refillSlots = refillGlassSection.getStringList("slots");
			for (String refillSlot : refillSlots) {
				int slot = Integer.parseInt(refillSlot);
				buttons.put(slot, refillGlassButton);
			}
		}

		return buttons;
	}

	@Override
	public int getSize() {
		FileConfiguration newsConfig = Basic.getInstance().getConfig();
		if (newsConfig != null) {
			return newsConfig.getInt("changelog-menu.size", 9 * 3);
		}

		return 9 * 3;
	}
}
