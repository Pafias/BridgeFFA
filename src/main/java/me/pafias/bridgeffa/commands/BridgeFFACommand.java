package me.pafias.bridgeffa.commands;

import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.commands.modules.*;
import me.pafias.bridgeffa.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BridgeFFACommand implements CommandExecutor, TabExecutor {

    private final BridgeFFA plugin;

    public BridgeFFACommand(BridgeFFA plugin) {
        this.plugin = plugin;
        commands.add(new SetlobbyCommand());
        commands.add(new LobbyCommand());
        commands.add(new me.pafias.bridgeffa.commands.modules.KillCommand());
        commands.add(new SavekitCommand());
        commands.add(new KitCommand());
        commands.add(new SavespawnCommand());
        commands.add(new SpawnCommand());
        commands.add(new ArmorstandCommand());
    }

    private final Set<ICommand> commands = new HashSet<>();

    private boolean help(CommandSender sender, String label) {
        sender.sendMessage(CC.t("&f-------------------- &bBridgeFFA &f--------------------"));
        commands.forEach(command -> {
            if (sender.hasPermission(command.getPermission()))
                sender.sendMessage(CC.t(String.format("&3/%s %s %s &9- %s", label, command.getName(), command.getArgs(), command.getDescription())));
        });
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return help(sender, label);
        else {
            ICommand cmd = commands.stream().filter(c -> c.getName().equalsIgnoreCase(args[0]) || c.getAliases().contains(args[0])).findFirst().orElse(null);
            if (cmd == null) return help(sender, label);
            if (!cmd.hasPermission(sender)) {
                cmd.noPermission(sender);
                return true;
            } else
                cmd.execute(label, sender, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1)
            return commands.stream().map(ICommand::getName).filter(n -> n.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        else {
            ICommand cmd = commands.stream().filter(c -> c.getName().equalsIgnoreCase(args[0]) || c.getAliases().contains(args[0])).findFirst().orElse(null);
            if (cmd == null) return Collections.emptyList();
            return cmd.tabComplete(sender, command, alias, args);
        }
    }

}
