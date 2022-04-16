package fr.sny1411.bingo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.sny1411.bingo.commands.Bingo;
import fr.sny1411.bingo.commands.NewGame;
import fr.sny1411.bingo.commands.Start;
import fr.sny1411.bingo.commands.statsGame;
import fr.sny1411.bingo.listenner.BingoGui;
import fr.sny1411.bingo.listenner.DefiListener;
import fr.sny1411.bingo.listenner.EventsListener;
import fr.sny1411.bingo.listenner.SettingsGui;
import fr.sny1411.bingo.listenner.TeamsGui;
import fr.sny1411.bingo.utils.Defis;
import fr.sny1411.bingo.utils.Game;
import fr.sny1411.bingo.utils.Teams;



public class Plugin extends JavaPlugin{
	private Game game = new Game();
	private TeamsGui teamsGui = new TeamsGui(game);
	private Teams teams = new Teams(teamsGui);
	private Defis defis = new Defis();
	@Override
	public void onEnable() {
		game.setClassTeams(teams);
		game.setClassDefis(defis);
		getCommand("bingo").setExecutor(new Bingo(game));
		getCommand("newGame").setExecutor(new NewGame(game));
		getCommand("statsGame").setExecutor(new statsGame(game));
		getCommand("start").setExecutor(new Start(game));
		Bukkit.getServer().getPluginManager().registerEvents(new EventsListener(game), this);
		Bukkit.getServer().getPluginManager().registerEvents(teamsGui, this);
		Bukkit.getServer().getPluginManager().registerEvents(new BingoGui(game), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DefiListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SettingsGui(game), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DefiListener(), this);
		super.onEnable();
	}
}
