package host.kuro.kurodiscord;

import host.kuro.kurodiscord.utils.ErrorUtils;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiscordMessage {

    private KuroDiscord plugin;
    private SimpleDateFormat sdf_hms = new SimpleDateFormat("HH:mm:ss");
    private final String GameName;

    public DiscordMessage(KuroDiscord plugin) {
        this.plugin = plugin;
        this.GameName = plugin.getConfig().getString("Discord.GameName", "GAME");
    }

    public void SendDiscordMessage(Player player, String message) {
        try {
            if (plugin.getJDA() == null) return;
            TextChannel channel = plugin.getJDA().getTextChannelById(plugin.getChannelId());
            if (channel == null) return;

            String chat_time = sdf_hms.format(new Date());
            StringBuilder sb = new StringBuilder();
            sb.append("```diff\n");
            sb.append("  ");
            sb.append(chat_time);
            sb.append(" [" + GameName + "] <");
            sb.append(player.getDisplayName());
            sb.append("> ");
            sb.append(CutSection(message));
            sb.append("\n```");
            channel.sendMessage(new String(sb)).queue();

        } catch (Exception ex) {
            plugin.getLogger().warning(ErrorUtils.GetErrorMessage(ex));
        }
    }
    public void SendDiscordRedMessage(String message) {
        try {
            if (plugin.getJDA() == null) return;
            TextChannel channel = plugin.getJDA().getTextChannelById(plugin.getChannelId());
            if (channel == null) return;

            String chat_time = sdf_hms.format(new Date());
            StringBuilder sb = new StringBuilder();
            sb.append("```diff\n");
            sb.append("- ");
            sb.append(chat_time);
            sb.append(" [" + GameName + "] ");
            sb.append(CutSection(message));
            sb.append("\n```");
            channel.sendMessage(new String(sb)).queue();

        } catch (Exception ex) {
            plugin.getLogger().warning(ErrorUtils.GetErrorMessage(ex));
        }
    }
    public void SendDiscordBlueMessage(String message) {
        try {
            if (plugin.getJDA() == null) return;
            TextChannel channel = plugin.getJDA().getTextChannelById(plugin.getChannelId());
            if (channel == null) return;

            String chat_time = sdf_hms.format(new Date());
            StringBuilder sb = new StringBuilder();
            sb.append("```md\n");
            sb.append("# ");
            sb.append(chat_time);
            sb.append(" [" + GameName + "] ");
            sb.append(CutSection(message));
            sb.append("\n```");
            channel.sendMessage(new String(sb)).queue();

        } catch (Exception ex) {
            plugin.getLogger().warning(ErrorUtils.GetErrorMessage(ex));
        }
    }
    public void SendDiscordGreenMessage(String message) {
        try {
            if (plugin.getJDA() == null) return;
            TextChannel channel = plugin.getJDA().getTextChannelById(plugin.getChannelId());
            if (channel == null) return;

            String chat_time = sdf_hms.format(new Date());
            StringBuilder sb = new StringBuilder();
            sb.append("```xl\n");
            sb.append("' ");
            sb.append(chat_time);
            sb.append(" [" + GameName + "] ");
            sb.append(CutSection(message));
            sb.append("\n```");
            channel.sendMessage(new String(sb)).queue();

        } catch (Exception ex) {
            plugin.getLogger().warning(ErrorUtils.GetErrorMessage(ex));
        }
    }
    public void SendDiscordYellowMessage(String message) {
        try {
            if (plugin.getJDA() == null) return;
            TextChannel channel = plugin.getJDA().getTextChannelById(plugin.getChannelId());
            if (channel == null) return;

            String chat_time = sdf_hms.format(new Date());
            StringBuilder sb = new StringBuilder();
            sb.append("```diff\n");
            sb.append("+ ");
            sb.append(chat_time);
            sb.append(" [" + GameName + "] ");
            sb.append(CutSection(message));
            sb.append("\n```");
            channel.sendMessage(new String(sb)).queue();

        } catch (Exception ex) {
            plugin.getLogger().warning(ErrorUtils.GetErrorMessage(ex));
        }
    }
    public void SendDiscordGrayMessage(String message) {
        try {
            if (plugin.getJDA() == null) return;
            TextChannel channel = plugin.getJDA().getTextChannelById(plugin.getChannelId());
            if (channel == null) return;

            String chat_time = sdf_hms.format(new Date());
            StringBuilder sb = new StringBuilder();
            sb.append("```py\n");
            sb.append("# ");
            sb.append(chat_time);
            sb.append(" [" + GameName + "] ");
            sb.append(CutSection(message));
            sb.append("\n```");
            channel.sendMessage(new String(sb)).queue();

        } catch (Exception ex) {
            plugin.getLogger().warning(ErrorUtils.GetErrorMessage(ex));
        }
    }
    public void SendDiscordBanMessage(String message) {
        try {
            if (plugin.getJDA() == null) return;
            TextChannel channel = plugin.getJDA().getTextChannelById(plugin.getChannelId());
            if (channel == null) return;

            String chat_time = sdf_hms.format(new Date());
            StringBuilder sb = new StringBuilder();
            sb.append("```diff\n");
            sb.append("- ");
            sb.append(chat_time);
            sb.append(" [" + GameName + "] ");
            sb.append(CutSection(message));
            sb.append("\n```");
            channel.sendMessage(new String(sb)).queue();

        } catch (Exception ex) {
            plugin.getLogger().warning(ErrorUtils.GetErrorMessage(ex));
        }
    }
    private String CutSection(String message) {
        String ret = message;
        ret = ret.replace(""+ ChatColor.BLACK, "");
        ret = ret.replace(""+ChatColor.DARK_BLUE, "");
        ret = ret.replace(""+ChatColor.DARK_GREEN, "");
        ret = ret.replace(""+ChatColor.DARK_AQUA, "");
        ret = ret.replace(""+ChatColor.DARK_RED, "");
        ret = ret.replace(""+ChatColor.DARK_PURPLE, "");
        ret = ret.replace(""+ChatColor.GOLD, "");
        ret = ret.replace(""+ChatColor.GRAY, "");
        ret = ret.replace(""+ChatColor.DARK_GRAY, "");
        ret = ret.replace(""+ChatColor.BLUE, "");
        ret = ret.replace(""+ChatColor.GREEN, "");
        ret = ret.replace(""+ChatColor.AQUA, "");
        ret = ret.replace(""+ChatColor.RED, "");
        ret = ret.replace(""+ChatColor.LIGHT_PURPLE, "");
        ret = ret.replace(""+ChatColor.YELLOW, "");
        ret = ret.replace(""+ChatColor.WHITE, "");
        ret = ret.replace(""+ChatColor.BOLD, "");
        ret = ret.replace(""+ChatColor.STRIKETHROUGH, "");
        ret = ret.replace(""+ChatColor.UNDERLINE, "");
        ret = ret.replace(""+ChatColor.ITALIC, "");
        ret = ret.replace(""+ChatColor.RESET, "");
        return ret;
    }

}
