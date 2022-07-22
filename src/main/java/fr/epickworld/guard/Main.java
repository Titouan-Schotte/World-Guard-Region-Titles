package fr.epickworld.guard;
import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static fr.epickworld.guard.FileManagement.*;

public final class Main extends JavaPlugin implements Listener {
    public static String globalPath = "plugins/EpickRegionsTitles/";

    @Override
    public void onEnable() {
        this.getCommand("epickwgreload").setExecutor(new CommandReload());
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
