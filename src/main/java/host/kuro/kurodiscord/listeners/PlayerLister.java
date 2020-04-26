package host.kuro.kurodiscord.listeners;

import host.kuro.kurodiscord.DiscordMessage;
import host.kuro.kurodiscord.KuroDiscord;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidTriggerEvent;

public class PlayerLister implements Listener {

	@EventHandler
	public void onChat(PlayerChatEvent e) {
		Player player = e.getPlayer();
		String message = e.getMessage();
		DiscordMessage dm = KuroDiscord.getDiscordMessage();
		if (dm != null) {
			dm.SendDiscordMessage(player, message);
		}
	}
}