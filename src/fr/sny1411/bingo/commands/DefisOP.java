package fr.sny1411.bingo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.sny1411.bingo.utils.Game;

public class DefisOP implements CommandExecutor {
	private Game game;
	public DefisOP(Game game) {
		this.game = game;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			String modeDefis = args[0];
			String team = args[1].replace("_", " ");
			String item = args[2];
			Boolean modeDefisBool = null;
			if (modeDefis.equalsIgnoreCase("add")) {
				modeDefisBool = true;
			} else if (modeDefis.equalsIgnoreCase("remove")) {
				modeDefisBool = false;
			} else {
				sender.sendMessage("§8[§c⚠§8] §fErreur de syntaxe");
			}
			
			if (modeDefisBool != null) {
				game.teams.defiDone.get(team).put("§d§l" + item,modeDefisBool);
				game.teams.defiValid.get(team).put("§d§l" + item,modeDefisBool);
				int i = game.teams.nbreDefiValid.get(team);
				game.teams.nbreDefiValid.put(team, i + 1);
				for (Player playerGui : game.teams.playersOnBingoGui.get(team)) {
					game.bingoGui.openGui(playerGui, team);
				}
				
				if (modeDefisBool) {
					afficheValidForceAdd(team, item);
				}
			}
		}
		return false;
	}
	
	public void afficheValidForceAdd(String equipe,String message) {
		if (game.modeAffichage == "Chill") {
			Bukkit.broadcastMessage("§7[§eBINGO§7] §fL'équipe " + game.teams.prefixeColorTeams.get(equipe) + equipe + "§r a réalisé le défi §e§l" + message);
		} else {
			for (Player playerInTeam : game.teams.teamsHash.get(equipe)) {
				if (playerInTeam.isOnline()) {
					playerInTeam.sendMessage("§7[§eBINGO§7] §fVotre équipe a réalisé le défi §e§l" + message);
				}
			}
		}
	}

}
