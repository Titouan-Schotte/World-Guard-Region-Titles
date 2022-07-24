package fr.titoune.epickregions.epickregions;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import static fr.titoune.epickregions.epickregions.FileManagement.*;


public final class Epickregions extends JavaPlugin implements Listener {
    public static String globalPath = "plugins/EpickRegionsTitles/";

    @Override
    public void onEnable() {
        getCommand("epickwgreload").setExecutor(new CommandReload());
        getServer().getPluginManager().registerEvents(this, this);
        createFile(globalPath+"regions.txt", "# You can register titles like that ...\n# region-id=TITLE$SUBTITLE\n# Minecraft color codes are enabled !\n# You can use {playerName} and {regionId}\nexample-region=&4Example Region&r$&oThis is an example region ...&r");
        createFile(globalPath+"config.json", "{\n\"missing-registry-region-ignore\":false,\n\"default-title\":\"&bWelcome, {playerName}\",\n\"default-subtitle\":\"&oin {regionId}\"\n}");
        readConfig();
        try {
            refreshDicoRegions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("EpickRegionsTitles enabled !");
    }




    @EventHandler
    public void onRegionEnter(RegionEnterEvent e)
    {
        if(!e.getRegion().getId().equals("__global__")){
            String[] regionInfos = getRegionInfosInJson(e.getRegion().getId());
            e.getPlayer().sendTitle(regionInfos[0].replaceAll("\\{regionId}", e.getRegion().getId()).replaceAll("\\{playerName}", e.getPlayer().getDisplayName()), regionInfos[1].replaceAll("\\{regionId}", e.getRegion().getId()).replaceAll("\\{playerName}", e.getPlayer().getDisplayName()));
        }
    }


}
