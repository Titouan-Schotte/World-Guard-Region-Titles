package fr.titoune.epickregions.epickregions;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static fr.titoune.epickregions.epickregions.Epickregions.globalPath;


public class FileManagement {
    public static Map<String, String> dicoRegions = new HashMap<String, String>();
    public static boolean isMissingRegistryIgnore = false;
    public static String defaultTitle = "Welcome to {playerName}";
    public static String defaultSubtitle = "{regionId}";
    public static void createFile(String pathIn, String content) {
        Path path = Paths.get(pathIn);
        boolean needToWrite=false;
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!Files.exists(path)) {
            try {
                Files.createFile(path);
                needToWrite=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(needToWrite){
            try (FileWriter file = new FileWriter(pathIn)) {
                file.write(content);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readConfig(){
        JSONObject configParse = loadConfig();
        isMissingRegistryIgnore = (boolean) configParse.get("missing-registry-region-ignore");
        defaultTitle = ((String) configParse.get("default-title")).replaceAll("&", "§");
        defaultSubtitle = ((String) configParse.get("default-subtitle")).replaceAll("&", "§");
    }

    public static JSONObject loadConfig() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(globalPath+"config.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String[] getRegionInfosInJson(String id) {
        if(dicoRegions.get(id) == null){
            if(!isMissingRegistryIgnore){
                return new String[]{defaultTitle, defaultSubtitle};
            }
            return new String[]{"",""};
        } else if(dicoRegions.get(id).split("\\$").length!=2){
            return new String[]{"§b"+id, dicoRegions.get(id)};
        } else {
            return new String[]{dicoRegions.get(id).split("\\$")[0], dicoRegions.get(id).split("\\$")[1]};
        }
    }

    public static void refreshDicoRegions() throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("plugins/EpickRegionsTitles/regions.txt"), "windows-1252"))) {
            for(String line; (line = br.readLine()) != null; ) {
                if(!line.equals("") && !line.split("")[0].equals("#")){
                    String name = line.split("=")[0];
                    String regionInfos = line.split("=")[1].replaceAll("&", "§");
                    dicoRegions.put(name, regionInfos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
