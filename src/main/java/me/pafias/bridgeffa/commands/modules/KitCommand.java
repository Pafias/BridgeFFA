package me.pafias.bridgeffa.commands.modules;

import me.pafias.bridgeffa.commands.ICommand;
import me.pafias.bridgeffa.ffa.Kit;
import me.pafias.bridgeffa.ffa.services.KitManager;
import me.pafias.bridgeffa.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KitCommand extends ICommand {

    public KitCommand() {
        super("kit", "bridgeffa.kit");
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
        return "Get a kit";
    }

    @Override
    public void execute(String mainCommand, CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.t("&cOnly players."));
            return;
        }
        if (args.length < 2) {
            sender.sendMessage(CC.t("&c/" + mainCommand + " " + getName() + " " + getArgs() + (sender.hasPermission("ffa.kit.others") ? " [player]" : "")));
            return;
        }
        Player player = (Player) sender;
        String name = args[1];
        Player target = player;
        if (args.length >= 3)
            target = plugin.getServer().getPlayer(args[2]);
        if (target == null) {
            sender.sendMessage(CC.t("&cPlayer not found."));
            return;
        }
        KitManager kitManager = plugin.getSM().getKitManager();
        if (!kitManager.exists(name)) {
            sender.sendMessage(CC.t("&cThat kit does not exist!"));
            return;
        }
        Kit kit = kitManager.getKit(name);
        kit.give(target);
        sender.sendMessage(CC.t("&aKit given" + (sender != target ? " to " + target.getName() : "")));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 2)
            return plugin.getSM().getKitManager().getKits()
                    .stream()
                    .map(Kit::getName)
                    .filter(n -> n.toLowerCase().startsWith(args[1].toLowerCase()))
                    .collect(Collectors.toList());
        return Collections.emptyList();
    }

}
