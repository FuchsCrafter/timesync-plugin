package de.fuchscrafter.timesyncplugin;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public final class Timesync extends JavaPlugin {

    private static Timesync instance;
    public Integer timeOffset;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        BukkitScheduler scheduler = getServer().getScheduler();
        Objects.requireNonNull(
                this.getCommand("timesync")).setExecutor(new timesyncCommand()
        );
        instance.saveDefaultConfig();

        for (World w: Bukkit.getServer().getWorlds()) {
            w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        }



        Long timesyncDelay = new Long( instance.getConfig().getInt("sync-delay") * 20 );
        timeOffset = new Integer(instance.getConfig().getInt("time-offset"));

        Bukkit.getLogger().info("(Timesync) Delay: " + new String(String.valueOf(timesyncDelay)) + " ticks");
        Bukkit.getLogger().info("(Timesync) Offset: " + new String(String.valueOf(timeOffset)) + "h");

        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                int mcTime;
                mcTime = timeToMcTime();
                for (World w: Bukkit.getServer().getWorlds()) {
                    w.setTime(mcTime);
                }
            }
        }, 0L, timesyncDelay);
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

        int hours = date.getHours() + instance.timeOffset;
        int min   = date.getMinutes();
        return (int) (((hours*1000)-6000) + (min*16.6));
    }

    public static Timesync getInstance() {
        return instance;
    }
}

