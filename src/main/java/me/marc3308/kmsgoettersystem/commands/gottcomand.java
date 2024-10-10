package me.marc3308.kmsgoettersystem.commands;

import me.marc3308.kmsgoettersystem.objekte.gott;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static me.marc3308.kmsgoettersystem.goettersystem.*;

public class gottcomand implements CommandExecutor , TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player p=(Player) sender;
            if(!p.hasPermission("gott"))return false;
            if(args.length>1){
                for (gott g : Gotterliste){
                    if (g.getName().equals(args[0])){
                        ArmorStand ar=p.getWorld().spawn(p.getLocation().add(0,0,0),ArmorStand.class);
                        String newname=g.getFarbe()+args[1];
                        for(int i=2;i<args.length;i++)newname+=" "+args[i];
                        ar.setInvulnerable(true);
                        ar.setCustomName(newname);
                        ar.setCustomNameVisible(true);
                        ar.setGravity(false);
                        ar.setVisible(false);
                        ar.setSmall(true);
                        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> ar.remove(),20*conmap.get(1).getInt("Despawnzeit"));

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        ArrayList<String> list =new ArrayList<>();
        if(!(commandSender instanceof Player))return list;
        Player p=(Player) commandSender;
        try {
            if(!p.hasPermission("gott"))return list;
            if(args.length == 0)return list;
            if(args.length == 1)for (gott g : Gotterliste)list.add(g.getName());
            if(args.length>=2)return list;

            //autocompetion
            ArrayList<String> commpleteList = new ArrayList<>();
            String currentarg = args[args.length-1].toLowerCase();
            for (String s : list){
                if(s==null)return list;
                String s1 =s.toLowerCase();
                if(s1.startsWith(currentarg)){
                    commpleteList.add(s);
                }
            }

            return commpleteList;
        } catch (CommandException e){
            return list;
        }
    }
}
