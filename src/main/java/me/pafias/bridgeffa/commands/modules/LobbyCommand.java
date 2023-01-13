package me.pafias.bridgeffa.commands.modules;


import me.pafias.bridgeffa.BridgePlayer;
import me.pafias.bridgeffa.commands.ICommand;
import me.pafias.bridgeffa.util.CC;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LobbyCommand extends ICommand {

    public LobbyCommand() {
        super("lobby", "bridgeffa.lobby");
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
        return "Go to the lobby";
    }

    @Override
    public void execute(String mainCommand, CommandSender sender, String[] args) {
        if (args.length >= 2) {
            if (!sender.hasPermission("ffa.lobby.others")) {
                noPermission(sender);
                return;
            }
            String targetName = args[1];
            if (plugin.getServer().getPlayer(targetName) == null) {
                sender.sendMessage(CC.t("&cTarget not online."));
                return;
            }
            try {
                plugin.getServer().getPlayer(targetName).teleport(plugin.getSM().getVariables().lobby);
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                sender.sendMessage(CC.t("&cUnable to teleport: Lobby not set."));
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage(CC.t("&cOnly players."));
                return;
            }
            Player player = (Player) sender;
            BridgePlayer user = plugin.getSM().getPlayerManager().getPlayer(player);
            if (user.isInFFAWorld() && !user.isInSpawn()
                    && (!player.getGameMode().equals(GameMode.CREATIVE) && !player.getGameMode().equals(GameMode.SPECTATOR))) {
                sender.sendMessage(CC.t("&cYou cannot do that here."));
                return;
            }
            try {
                player.teleport(plugin.getSM().getVariables().lobby);
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                player.sendMessage(CC.t("&cUnable to teleport: Lobby not set."));
            }
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

}
