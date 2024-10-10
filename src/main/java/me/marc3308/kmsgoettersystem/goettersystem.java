package me.marc3308.kmsgoettersystem;

import me.marc3308.kmsgoettersystem.commands.gottcomand;
import me.marc3308.kmsgoettersystem.objekte.gott;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class goettersystem extends JavaPlugin {

    public static ArrayList<gott> Gotterliste = new ArrayList<>();
    public static FileConfiguration gotterconfig;

    public static goettersystem plugin;

    @Override
    public void onEnable() {

        plugin=this;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                File file = new File("plugins/KMS Plugins/Göttersystem","Götter.yml");
                FileConfiguration con= YamlConfiguration.loadConfiguration(file);
                gotterconfig=con;
            }
        },0,20*20);

        if(gotterconfig.get("Götterliste")==null){
            gotterconfig.set("Despawnzeit",10);
            gotterconfig.set("Götterliste"+".1"+".Name","Blutgott");
            gotterconfig.set("Götterliste"+".1"+".Farbe","§4");
            //save it
            File file = new File("plugins/KMS Plugins/Göttersystem","Götter.yml");
            try {
                gotterconfig.save(file);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        int i=1;
        while (gotterconfig.get("Götterliste"+"."+i+".Name")!=null){
            Gotterliste.add(new gott(gotterconfig.getString("Götterliste"+"."+i+".Name"),gotterconfig.getString("Götterliste"+"."+i+".Farbe")));
            i++;
        }

        getCommand("gott").setExecutor(new gottcomand());




    }

    public static goettersystem getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
