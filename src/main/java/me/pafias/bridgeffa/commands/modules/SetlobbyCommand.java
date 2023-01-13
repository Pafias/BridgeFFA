package me.pafias.bridgeffa.commands.modules;

import me.pafias.bridgeffa.commands.ICommand;
import me.pafias.bridgeffa.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SetlobbyCommand extends ICommand {

    public SetlobbyCommand() {
        super("setlobby", "bridgeffa.setlobby");
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission(getPermission());
    }

    @NotNull
    @Override
    public String getArgs() {
        return "";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Set the lobby (spawn)location";
    }

    @Override
    public void execute(String mainCommand, CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.t("&cOnly players."));
            return;
        }
        Player player = (Player) sender;
        plugin.getSM().getVariables().lobby = player.getLocation();
        plugin.getConfig().set("lobby.world", player.getLocation().getWorld().getName());
        plugin.getConfig().set("lobby.x", player.getLocation().getBlockX() + 0.5);
        plugin.getConfig().set("lobby.y", player.getLocation().getBlockY() + 0.1);
        plugin.getConfig().set("lobby.z", player.getLocation().getBlockZ() + 0.5);
        plugin.getConfig().set("lobby.yaw", player.getLocation().getYaw());
        plugin.getConfig().set("lobby.pitch", player.getLocation().getPitch());
        plugin.saveConfig();
        sender.sendMessage(CC.t("&aLobby set."));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

}
