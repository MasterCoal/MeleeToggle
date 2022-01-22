package walmartmc.net.meleetoggle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MeleeToggle extends JavaPlugin implements Listener, CommandExecutor {

    public Boolean meleestatus = true;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage( ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "MeleeToggle" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Enabled!");
        MeleeToggle.getPlugin(MeleeToggle.class).getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(this.getCommand("togglemelee")).setExecutor(this);


    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage( ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "MeleeToggle" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Shutting Down...");

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("togglemelee")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("walmartmc.melee")) {
                    if (meleestatus.equals(true)) {
                        meleestatus = false;
                        player.sendMessage(ChatColor.RED + "Melee has been disabled");
                    } else {
                        meleestatus = true;
                        player.sendMessage(ChatColor.GREEN + "Melee has been enabled");
                    }
                }
            }
        }
        return true;
    }

    @EventHandler
    public void playerHit(EntityDamageByEntityEvent e) {
        if (meleestatus.equals(false)) {
            if (e.getDamager() instanceof Player) {
                if (e.getEntity() instanceof Player) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
