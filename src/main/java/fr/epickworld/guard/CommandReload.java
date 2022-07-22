package fr.epickworld.guard;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

import static fr.epickworld.guard.FileManagement.*;
import static fr.epickworld.guard.Main.*;

public class CommandReload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        try {
            refreshDicoRegions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readConfig();
        sender.sendMessage("§2Success !");
        return true;
    }
}