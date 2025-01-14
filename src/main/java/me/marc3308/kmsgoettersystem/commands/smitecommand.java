package me.marc3308.kmsgoettersystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.marc3308.kmsgoettersystem.utility.conmap;

public class smitecommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player))return false;
        Player p=(Player) sender;
        if(!p.hasPermission("gott"))return false;
        if(args.length<1)return false;
        if(Bukkit.getPlayer(args[0])==null)return false;
        Player smite=Bukkit.getPlayer(args[0]);
        smite.damage(conmap.get(1).getInt("smitedamage"));
        smite.getWorld().strikeLightningEffect(smite.getLocation());
        return false;
    }
}
