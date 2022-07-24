package fr.titoune.epickregions.epickregions;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public final class Events implements Listener {


    @EventHandler
    public void onRegionEnter(RegionEnterEvent e)
    {
        System.out.println(e.getRegion().getId());
        e.getPlayer().sendTitle("Plaines de Lividus", e.getRegion().getId());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void highLogin(PlayerLoginEvent event) {
        // Some code here
        event.getPlayer().sendTitle("HELLO", "nice");
    }
}