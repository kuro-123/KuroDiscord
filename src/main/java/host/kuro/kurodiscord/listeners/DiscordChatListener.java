package host.kuro.kurodiscord.listeners;

import host.kuro.kurodiscord.KuroDiscord;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DiscordChatListener extends ListenerAdapter {

    private final KuroDiscord plugin;
    private final int MessageMaxLength;
    private final String TitleName;
    private final String GameName;

    public DiscordChatListener(KuroDiscord plugin) {
        this.plugin = plugin;
        this.MessageMaxLength = plugin.getConfig().getInt("Discord.MessageMaxLength", 128);
        this.TitleName = plugin.getConfig().getString("Discord.TitleName", "DISCORD");
        this.GameName = plugin.getConfig().getString("Discord.GameName", "GAME");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        // JDA
        if (plugin.getJDA() == null) return;
        if (plugin.getJDA().getSelfUser() == null) return;
        if (plugin.getJDA().getSelfUser().getId() == null) return;
        String selfname = plugin.getJDA().getSelfUser().getName();
        // Author
        if (e.getAuthor() == null) return;
        if (e.getAuthor().getId() == null) return;
        String author = e.getAuthor().getName();
        // OWN
        if (e.getAuthor().equals(plugin.getJDA().getSelfUser())) return;
        // Member
        if (e.getMember() == null) return;
        // check channel
        if (!e.getChannel().getId().equals(plugin.getChannelId())) return;
        // get message
        String message = e.getMessage().getContentStripped();
        if (message.isEmpty() && e.getMessage().getAttachments().isEmpty()) return;
        // long message split
        if (message.length() > MessageMaxLength) message = message.substring(0, MessageMaxLength);
        // avoid loop
        if (message.indexOf("[" + GameName + "]") >= 0) return;

        if (message.startsWith("/")) {
            // command
            CommnadExecuteFromDiscord(message, author);
        } else {
            // chat
            StringBuilder sb = new StringBuilder();
            sb.append(ChatColor.LIGHT_PURPLE);
            sb.append("[");
            sb.append(TitleName);
            sb.append("] <");
            sb.append(ChatColor.WHITE);
            sb.append(author);
            sb.append(ChatColor.LIGHT_PURPLE);
            sb.append("> ");
            sb.append(ChatColor.WHITE);
            sb.append(message);
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + new String(sb));
        }
    }

    private void CommnadExecuteFromDiscord(String message, String author) {
        if (message.startsWith("/list")) {
            StringBuilder sb = new StringBuilder();
            int cnt=0;
            for (Player player : Bukkit.getOnlinePlayers()) {
                sb.append(String.format("\n[ %s ワールド: %s 位置: %d,%d,%d ]", player.getName(), player.getLocation().getWorld().getName(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ()));
                cnt++;
            }
            sb.append(String.format("\nオンライン : %d人\n", cnt));
            plugin.getDiscordMessage().SendDiscordBlueMessage(new String(sb));
        }
    }
}