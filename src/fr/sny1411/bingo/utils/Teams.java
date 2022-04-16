package fr.sny1411.bingo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import fr.sny1411.bingo.listenner.TeamsGui;

public class Teams {
	public int nombreTeams = 4;
	public int nombreJoueursParTeams = 2;
	public List<List<Player>> teamsList;
	public Hashtable<String, List<Player>> teamsHash = new Hashtable<String, List<Player>>();
	public List<String> colorTeams = new ArrayList<String>(Arrays.asList("Orange","Rouge","Violet","Rose","Vert","Bleu"));
	public Hashtable<String, String> prefixeColorTeams = new Hashtable<String, String>();
	public List<Player> playerInGui = new ArrayList<Player>();
	private TeamsGui teamsGui;
	public Hashtable<String, List<List<String>>> defiDone = new Hashtable<String, List<List<String>>>(); // a terminer
	public Hashtable<String, Integer> nbreDefiDone = new Hashtable<String, Integer>();
	public List<Player> listSpectator = new ArrayList<Player>();
	public Teams(TeamsGui teamsGui2) {
		this.teamsGui = teamsGui2;
	}

	public void createTeams() {
		prefixeColorTeams.put("","");
		prefixeColorTeams.put("Orange", "§6");
		prefixeColorTeams.put("Rouge", "§c");
		prefixeColorTeams.put("Violet", "§5");
		prefixeColorTeams.put("Rose", "§d");
		prefixeColorTeams.put("Vert", "§a");
		prefixeColorTeams.put("Bleu", "§b");
		prefixeColorTeams.put("Spectator","§8[SPEC] §7§o");

		for (int i = 0; i < nombreTeams; i++) {
			teamsHash.put(colorTeams.get(i), new ArrayList<Player>());
			nbreDefiDone.put(colorTeams.get(i), 0);
		}
	}
	
	public void changeNumberOfTeams(int n) {
		this.nombreTeams = n;
		teamsGui.updateGui(playerInGui);
		this.createTeams();
	}
	
	public void changeNumberJoueursParTeams(int n) {
		this.nombreJoueursParTeams = n;
		teamsGui.updateGui(playerInGui);
		this.createTeams();
	}
	
	public void removePlayer(String nameTeams, Player player) {
		if (nameTeams.equalsIgnoreCase("Spectator")) {
			listSpectator.remove(player);
		} else {
			List<Player> teamPlayers = teamsHash.get(nameTeams);
			teamPlayers.remove(player);
		}
	}
	
	public String findTeamPlayer(Player player) {
		for (Player lplayer : listSpectator) {
			if (player.equals(lplayer)) {
				return "Spectator";
			}
		}
		Set<String> SetOfKeys = teamsHash.keySet();
		for (String nameTeam : SetOfKeys) {
			for (Player playerTeam :teamsHash.get(nameTeam)) {
				if (playerTeam.equals(player)) {
					return nameTeam;
				}
			}
		}
		return "";
	}
	
	public void addPlayer(String nameTeams, Player player) {
		List<Player> teamPlayers = teamsHash.get(nameTeams);
		if (teamPlayers.size()<nombreJoueursParTeams) {
			teamPlayers.add(player);
			teamsHash.put(nameTeams, teamPlayers);
			player.setPlayerListName(prefixeColorTeams.get(nameTeams) + player.getName());
		} else {
			player.sendMessage("§aCette équipe est pleine");
		}
	}
	
	public void resetTeam() {
		this.playerInGui = new ArrayList<Player>();
		this.nombreTeams = 4;
		this.nombreJoueursParTeams = 2;
	}
	
}
