package me.marc3308.kmsgoettersystem;

import me.marc3308.kmsgoettersystem.commands.gottcomand;
import me.marc3308.kmsgoettersystem.commands.smitecommand;
import me.marc3308.kmsgoettersystem.objekte.gott;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static me.marc3308.kmsgoettersystem.utility.Gotterliste;
import static me.marc3308.kmsgoettersystem.utility.conmap;

public final class goettersystem extends JavaPlugin {


    public static goettersystem plugin;

    @Override
    public void onEnable() {

        plugin=this;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                File file1 = new File("plugins/KMS Plugins/Göttersystem","Götter.yml");
                FileConfiguration con1= YamlConfiguration.loadConfiguration(file1);
                conmap.put(1,con1);
            }
        },0,20*10);

        File file1 = new File("plugins/KMS Plugins/Göttersystem","Götter.yml");
        FileConfiguration con1= YamlConfiguration.loadConfiguration(file1);


        if(con1.get("Götterliste")==null){
            con1.set("smitedamage",3);
            con1.set("Despawnzeit",10);
            con1.set("Götterliste"+".1"+".Name","Blutgott");
            con1.set("Götterliste"+".1"+".Farbe","§4");
            //save it
            File file = new File("plugins/KMS Plugins/Göttersystem","Götter.yml");
            try {
                con1.save(file);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        int i=1;
        while (con1.get("Götterliste"+"."+i+".Name")!=null){
            Gotterliste.add(new gott(con1.getString("Götterliste"+"."+i+".Name"),con1.getString("Götterliste"+"."+i+".Farbe")));
            i++;
        }

        getCommand("gott").setExecutor(new gottcomand());
        getCommand("smite").setExecutor(new smitecommand());




    }

    public static goettersystem getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
