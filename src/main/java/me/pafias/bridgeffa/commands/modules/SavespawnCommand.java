package me.pafias.bridgeffa.commands.modules;

import me.pafias.bridgeffa.commands.ICommand;
import me.pafias.bridgeffa.ffa.services.SpawnManager;
import me.pafias.bridgeffa.util.CC;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class SavespawnCommand extends ICommand {

    public SavespawnCommand() {
        super("savespawn", "bridgeffa.savespawn", "spawnsave", "addspawn", "spawnadd");
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return sender.hasPermission(getPermission());
    }

    @NotNull
    @Override
    public String getArgs() {
        return "<name>";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Save a spawnpoint";
    }

    @Override
    public void execute(String mainCommand, CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.t("&cOnly players."));
            return;
        }
        if (args.length < 2) {
            sender.sendMessage(CC.t("&c/" + mainCommand + " " + getName() + " " + getArgs()));
            return;
        }
        Player player = (Player) sender;
        String name = args[1];
        SpawnManager spawnManager = plugin.getSM().getSpawnManager();
        if (spawnManager.exists(name)) {
            sender.sendMessage(CC.t("&cA spawn with that name already exists!"));
            return;
        }
        if (player.getItemInHand() == null || player.getItemInHand().getType().equals(Material.AIR)) {
            sender.sendMessage(CC.t("&cYou have to have an item in your hand (will be the gui item)"));
            return;
        }
        try {
            spawnManager.saveNewSpawn(player, name);
        } catch (IOException e) {
            e.printStackTrace();
            sender.sendMessage(CC.t("&cFailed to save spawn."));
            return;
        }
        sender.sendMessage(CC.t("&aSpawn saved."));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

}
