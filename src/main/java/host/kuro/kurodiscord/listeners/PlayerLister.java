package host.kuro.kurodiscord.listeners;

import host.kuro.kurodiscord.DiscordMessage;
import host.kuro.kurodiscord.KuroDiscord;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.RaidTriggerEvent;

import java.util.concurrent.Callable;

public class PlayerLister implements Listener {

	private KuroDiscord plugin;

	public PlayerLister(KuroDiscord plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onChat(final AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		String message = e.getMessage();
		if(e.isAsynchronous()) {
			plugin.getServer().getScheduler().callSyncMethod(plugin, new CallableOnChat(plugin, player, message));
		} else {
			DiscordMessage dm = KuroDiscord.getDiscordMessage();
			if (dm != null) {
				dm.SendDiscordMessage(player, message);
			}
		}
	}
}

class CallableOnChat implements Callable<Object>
{
	private KuroDiscord plugin;
	private Player player;
	private String message;

	CallableOnChat(KuroDiscord plugin, Player player, String message) {
		this.plugin = plugin;
		this.player = player;
		this.message = message;
	}

	@Override
	public Object call() throws Exception {
		DiscordMessage dm = KuroDiscord.getDiscordMessage();
		if (dm != null) {
			dm.SendDiscordMessage(player, message);
		}
		return null;
	}
}