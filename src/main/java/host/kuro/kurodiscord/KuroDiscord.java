package host.kuro.kurodiscord;

import host.kuro.kurodiscord.lang.Language;
import host.kuro.kurodiscord.listeners.DiscordChatListener;
import host.kuro.kurodiscord.listeners.PlayerLister;
import host.kuro.kurodiscord.utils.ErrorUtils;
import host.kuro.kurodiscord.utils.StringUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public class KuroDiscord extends JavaPlugin {

    public static boolean DEBUG;
    private static JDA jda;
    private static String channelId;
    private static DiscordMessage discordMessage = null;

    public static JDA getJDA() { return jda; }
    public static String getChannelId() { return channelId; }
    public static DiscordMessage getDiscordMessage() { return discordMessage; }

    @Override
    public void onEnable() {
        // language setup
        Language.load("UTF-8");
        getLogger().info(Language.translate("plugin.setup.language"));

        // directory setup
        getLogger().info(Language.translate("plugin.setup.directory"));
        if(!getDataFolder().exists()) { getDataFolder().mkdir(); }

        // load settings
        getLogger().info(Language.translate("plugin.setup.settings"));
        this.saveDefaultConfig();
        DEBUG = this.getConfig().getBoolean("debug", false);

        // regist event listener
        getLogger().info(Language.translate("plugin.setup.event"));
        this.getServer().getPluginManager().registerEvents(new PlayerLister(this), this);

        // regist discord api
        if (!RegistDiscord()) {
            getLogger().warning(Language.translate("plugin.shutdown"));
            Bukkit.getServer().shutdown();
            return;
        }
        Bukkit.getServicesManager().register(KuroDiscord.class, this, this, ServicePriority.High);
        getLogger().info(Language.translate("plugin.loaded"));
    }

    private boolean RegistDiscord() {
        try {
            String bottoken = this.getConfig().getString("Discord.BotToken", "");
            if (bottoken.length() <= 0) return false;

            channelId = this.getConfig().getString("Discord.ChannelId", "");
            if (channelId.length() <= 0) return false;

            String botstatus = this.getConfig().getString("Discord.BotStatus", "");
            if (botstatus.length() <= 0) return false;

            jda = new JDABuilder(bottoken).build();
            jda.awaitReady();
            jda.addEventListener(new DiscordChatListener(this));
            jda.getPresence().setActivity(Activity.of(Activity.ActivityType.DEFAULT, botstatus));

            discordMessage = new DiscordMessage(this);

        } catch (Exception ex) {
            getLogger().warning(ErrorUtils.GetErrorMessage(ex));
            return false;
        }
        return true;
    }

    @Override
    public void onDisable() {
        getLogger().info(Language.translate("plugin.unloaded"));
    }
}
