package cc.co.evenprime.bukkit.nocheat.checks.chat;

import org.bukkit.entity.Player;

import cc.co.evenprime.bukkit.nocheat.NoCheat;
import cc.co.evenprime.bukkit.nocheat.config.Permissions;
import cc.co.evenprime.bukkit.nocheat.config.cache.ConfigurationCache;
import cc.co.evenprime.bukkit.nocheat.data.ChatData;
import cc.co.evenprime.bukkit.nocheat.data.LogData;

/**
 * 
 * @author Evenprime
 * 
 */
public class ChatCheck {

    private final NoCheat plugin;

    public ChatCheck(NoCheat plugin) {

        this.plugin = plugin;
    }

    public boolean check(Player player, String message, ChatData data, ConfigurationCache cc) {

        boolean cancel = false;

        boolean spamCheck = cc.chat.spamCheck && !player.hasPermission(Permissions.CHAT_SPAM);

        if(spamCheck) {

            int time = plugin.getIngameSeconds();

            if(data.spamLasttime + cc.chat.spamTimeframe <= time) {
                data.spamLasttime = time;
                data.messageCount = 0;
            }

            data.messageCount++;

            if(data.messageCount > cc.chat.spamLimit) {

                // Prepare some event-specific values for logging and custom
                // actions
                LogData ldata = plugin.getDataManager().getData(player).log;

                ldata.check = "chat.spam";
                ldata.text = message;

                cancel = plugin.getActionManager().executeActions(player, cc.chat.spamActions, data.messageCount - cc.chat.spamLimit, ldata, data.history, cc);
            }
        }

        return cancel;
    }

}
