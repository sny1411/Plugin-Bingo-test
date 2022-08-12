package fr.sny1411.bingo.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.sny1411.bingo.Plugin;
import fr.sny1411.bingo.listener.DefiListener;
import fr.sny1411.bingo.utils.Game;

public class Bonus implements CommandExecutor {
	
	private Game game;
	private DefiListener defiListener;
	private Plugin plugin;

	public Bonus(Game game, DefiListener defiListener, Plugin plugin) {
		this.game = game;
		this.defiListener = defiListener;
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					String defi = "§d§l";
					boolean firstWord = true;
					for (String mot : args) {
						if (firstWord) {
							defi += (mot);
							firstWord = false;
						} else {
							defi += (" " + mot);
						}
					}
					
					Boolean defiDansList = false;
					String niveauDefi = "";
					for (List<String> defiBonus : game.eventDefisBonus.defisEnCours) {
						System.out.println(defiBonus.get(0) + " - " + defi);
						if (defiBonus.get(0).equalsIgnoreCase(defi)) {
							defiDansList = true;
							niveauDefi = defiBonus.get(2);
						}
					}
					
					System.out.println("test bonus");
					if (defiDansList) {
						System.out.println("appel verif");
						switch (niveauDefi) {
						case "easy":
							niveauDefi = "I";
							break;
						case "medium":
							niveauDefi = "II";
							break;
						case "hard":
							niveauDefi = "III";
							break;
						default:
							break;
						}
						defiListener.testItems(player, defi, true, niveauDefi);
					} else  {
						player.sendMessage("§8[§c⚠§8] §fErreur de syntaxe");
					}
				}
			}
		});
		
		return false;
	}

}
