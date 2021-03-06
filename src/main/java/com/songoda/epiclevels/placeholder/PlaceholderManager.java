package com.songoda.epiclevels.placeholder;

import com.songoda.epiclevels.EpicLevels;
import com.songoda.epiclevels.players.EPlayer;
import com.songoda.epiclevels.utils.Methods;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class PlaceholderManager extends PlaceholderExpansion {

    private final EpicLevels plugin;

    public PlaceholderManager(EpicLevels plugin) {
        this.plugin = plugin;
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayer(player);

        switch (identifier) {
            case "level":
                return Methods.formatDecimal(ePlayer.getLevel());
            case "experience":
                return Methods.formatDecimal(ePlayer.getExperience());
            case "kills":
                return Methods.formatDecimal(ePlayer.getKills());
            case "playerkills":
                return Methods.formatDecimal(ePlayer.getPlayerKills());
            case "mobkills":
                return Methods.formatDecimal(ePlayer.getMobKills());
            case "deaths":
                return Methods.formatDecimal(ePlayer.getDeaths());
            case "killstreak":
                return Methods.formatDecimal(ePlayer.getKillstreak());
            case "bestkillstreak":
                return Methods.formatDecimal(ePlayer.getBestKillstreak());
            case "kdr":
                return Methods.formatDecimal(ePlayer.getDeaths() == 0 ? ePlayer.getPlayerKills() : (double) ePlayer.getPlayerKills() / (double) ePlayer.getDeaths());
            case "nextlevel":
                return Methods.formatDecimal(ePlayer.getLevel() + 1);
            case "neededfornextlevel":
                return Methods.formatDecimal(EPlayer.experience(ePlayer.getLevel() + 1) - ePlayer.getExperience());
            case "boosterenabled":
                return plugin.getBoostManager().getBoost(ePlayer.getUniqueId()) == null
                        ? plugin.getLocale().getMessage("general.word.enabled").getMessage()
                        : plugin.getLocale().getMessage("general.word.disabled").getMessage();
            case "booster":
                if (plugin.getBoostManager().getBoost(ePlayer.getUniqueId()) == null) return "1";
                return Methods.formatDecimal(plugin.getBoostManager().getBoost(ePlayer.getUniqueId()).getMultiplier());
            case "globalboosterenabled":
                return plugin.getBoostManager().getGlobalBoost() == null
                        ? plugin.getLocale().getMessage("general.word.enabled").getMessage()
                        : plugin.getLocale().getMessage("general.word.disabled").getMessage();
            case "globalbooster":
                if (plugin.getBoostManager().getGlobalBoost() == null) return "1";
                return Methods.formatDecimal(plugin.getBoostManager().getGlobalBoost().getMultiplier());
            case "progressbar":
                double exp = ePlayer.getExperience() - EPlayer.experience(ePlayer.getLevel());
                double nextLevel = EPlayer.experience(ePlayer.getLevel() + 1) - EPlayer.experience(ePlayer.getLevel());
                return Methods.generateProgressBar(exp, nextLevel, true);
            default:
                return null;
        }
    }

    @Override
    public String getIdentifier() {
        return "epiclevels";
    }

    @Override
    public String getAuthor() {
        return "Songoda";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }
}
