package me.marc3308.kmsgoettersystem;

import me.marc3308.kmsgoettersystem.commands.gottcomand;
import me.marc3308.kmsgoettersystem.objekte.gott;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class goettersystem extends JavaPlugin {

    public static ArrayList<gott> Gotterliste = new ArrayList<>();

    public static goettersystem plugin;

    @Override
    public void onEnable() {

        plugin=this;
        File file = new File("plugins/KMS Plugins/Göttersystem","Götter.yml");
        FileConfiguration con= YamlConfiguration.loadConfiguration(file);

        if(con.get("Götterliste")==null){
            con.set("Götterliste"+".1"+".Name","Blutgott");
            con.set("Götterliste"+".1"+".Farbe","§4");
            //save it
            try {
                con.save(file);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        int i=1;
        while (con.get("Götterliste"+"."+i+".Name")!=null){
            Gotterliste.add(new gott(con.getString("Götterliste"+"."+i+".Name"),con.getString("Götterliste"+"."+i+".Farbe")));
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
