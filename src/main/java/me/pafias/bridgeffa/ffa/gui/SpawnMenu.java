package me.pafias.bridgeffa.ffa.gui;

import me.pafias.bridgeffa.ffa.Kit;
import me.pafias.bridgeffa.ffa.Spawn;
import me.pafias.bridgeffa.gui.GuiMenu;
import me.pafias.bridgeffa.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpawnMenu extends GuiMenu {

    private final Kit kit;

    public SpawnMenu(Player player, int size, Kit kitToGive) {
        super(player, CC.t("&6Spawn selection"), size);
        this.kit = kitToGive;
        plugin.getSM().getSpawnManager().getSpawns().forEach(spawn -> getInventory().addItem(spawn.getGUIItem()));
    }

    @Override
    public void clickHandler(ItemStack item, int slot) {
        Spawn spawn = plugin.getSM().getSpawnManager().getSpawn(item);
        if (spawn == null) {
            setCloseOnClick(false);
            return;
        }
        spawn.teleport(player);
        if (kit != null)
            kit.give(player);
    }

    @Override
    public void onClose() {

    }

}
