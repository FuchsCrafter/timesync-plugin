package de.fuchscrafter.timesyncplugin

import de.fuchscrafter.timesyncplugin.Timesync
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class timesyncCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        val now = Calendar.getInstance()
        val timeNow = now[Calendar.HOUR_OF_DAY].toString() + ":" + now[Calendar.MINUTE]

        val mcTime = Timesync.timeToMcTime()

        val pluginTimeOffset = Timesync.getInstance().timeOffset;
        val timeWithOffset = (now[Calendar.HOUR_OF_DAY]+pluginTimeOffset).toString() + ":" + now[Calendar.MINUTE]

        val prefix = "[TimeSync] "
        val msg = "Time: $timeNow (system time), $mcTime (in-game-time)"
        val offsetMsg = "Offset: $pluginTimeOffset h -> $timeWithOffset"

        if (sender is Player) {
            sender.sendMessage(ChatColor.RESET.toString() + "" + ChatColor.BOLD + prefix + ChatColor.RESET + msg)
            sender.sendMessage(ChatColor.RESET.toString() + "" + ChatColor.BOLD + prefix + ChatColor.RESET + offsetMsg)
        } else {
            Bukkit.getLogger().info(prefix + msg)
        }
        return true

    }
}

/* Java code
package de.fuchscrafter.timesyncplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class timesyncCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Calendar now = Calendar.getInstance();
        String timeNow = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);

        int mcTime = Timesync.timeToMcTime();

        String prefix = "[TimeSync] ";
        String msg = "Zeit: " + timeNow + " (Echtzeit) " + mcTime + " (in-game-Zeit)";

        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RESET+""+ChatColor.BOLD+prefix+ChatColor.RESET+msg);
        } else {
            Bukkit.getLogger().info(prefix+msg);
        }
        return true;
    }
}
*/