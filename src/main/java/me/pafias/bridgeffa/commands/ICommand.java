package me.pafias.bridgeffa.commands;

import me.pafias.bridgeffa.BridgeFFA;
import me.pafias.bridgeffa.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ICommand {

    public BridgeFFA plugin = BridgeFFA.get();

    String name;
    Set<String> aliases;
    String permission;
    String args;
    String description;

    public ICommand(String name) {
        this.name = name;
        this.aliases = new HashSet<>();
        this.permission = null;
    }

    public ICommand(String name, String permission, String... aliases) {
        this.name = name;
        this.aliases = new HashSet<>(Arrays.asList(aliases));
        this.permission = permission;
    }

    public abstract boolean hasPermission(CommandSender sender);

    public String getName() {
        return name;
    }

    public Set<String> getAliases() {
        return aliases;
    }

    public String getPermission() {
        return permission;
    }

    public abstract String getArgs();

    public abstract String getDescription();

    public abstract void execute(String mainCommand, CommandSender sender, String[] args);

    public abstract List<String> tabComplete(CommandSender sender, Command command, String alias, String[] args);

    public void noPermission(CommandSender sender) {
        sender.sendMessage(CC.t("&cNo permission."));
    }

}
