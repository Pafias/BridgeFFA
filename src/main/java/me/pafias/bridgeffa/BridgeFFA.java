package me.pafias.bridgeffa;

import me.pafias.bridgeffa.commands.BridgeFFACommand;
import me.pafias.bridgeffa.ffa.listeners.ArmorstandListener;
import me.pafias.bridgeffa.ffa.listeners.DeathListener;
import me.pafias.bridgeffa.ffa.listeners.ProtectionListener;
import me.pafias.bridgeffa.listeners.GameListener;
import me.pafias.bridgeffa.listeners.JoinQuitListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BridgeFFA extends JavaPlugin {

    private static BridgeFFA plugin;

    public static BridgeFFA get() {
        return plugin;
    }

    private ServicesManager servicesManager;

    public ServicesManager getSM() {
        return servicesManager;
    }

    @Override
    public void onEnable() {
        plugin = this;
        servicesManager = new ServicesManager(plugin);
        getServer().getOnlinePlayers().forEach(p -> servicesManager.getPlayerManager().addPlayer(p));
        register();
    }

    private void register() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new JoinQuitListener(plugin), plugin);
        pm.registerEvents(new GameListener(plugin), plugin);

        pm.registerEvents(new ArmorstandListener(plugin), plugin);
        pm.registerEvents(new DeathListener(plugin), plugin);
        pm.registerEvents(new ProtectionListener(plugin), plugin);

        getCommand("bridgeffa").setExecutor(new BridgeFFACommand(plugin));
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public double parseVersion() {
        String version = getServer().getBukkitVersion();
        String[] var = version.split("\\.", 2);
        String[] var2 = var[1].split("-");
        String var3 = var2[0];
        double d = Double.parseDouble(var3);
        return d;
    }

}
