package de.fuchscrafter.timesyncplugin;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandException;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public final class Timesync extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        BukkitScheduler scheduler = getServer().getScheduler();
        Objects.requireNonNull(
                this.getCommand("timesync")).setExecutor(new timesyncCommand()
        );

        try {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gamerule doDaylightCycle false"); // fixme: fix unknown command error


        } catch (CommandException e) {
            Bukkit.getLogger().info("(TimeSync) Day/Night cycle couldn't be disabled! (continuing plugin)");
        }


        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                int mcTime;
                mcTime = timeToMcTime();
                for (World w: Bukkit.getServer().getWorlds()) {
                    w.setTime(mcTime);
                }
            }
        }, 0L, 400L); // todo: add config option for tick delay
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // pass
    }

    public static int timeToMcTime() {
        Calendar now = Calendar.getInstance();
        String timeNow = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE); // todo: use 'now.get()' directly when declaring hours and min

        SimpleDateFormat format = new SimpleDateFormat("H:m");
        Date date = null;

        try {
            date = format.parse(timeNow);
        } catch (ParseException e) {
            Bukkit.getLogger().severe("(TimeSync) A RuntimeExeption occured whilst setting the time!");
            throw new RuntimeException(e);
        }

        int hours = date.getHours();
        int min   = date.getMinutes();
        return (int) (((hours*1000)-6000) + (min*16.6));
    }
}

