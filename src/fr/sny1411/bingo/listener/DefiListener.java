package fr.sny1411.bingo.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.StructureType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Turtle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.event.entity.EntityMountEvent;

import fr.sny1411.bingo.utils.Game;
import net.md_5.bungee.api.ChatColor;

public class DefiListener implements Listener {
	private Game game;
	private Hashtable<Player, Integer> numberWolfTame = new Hashtable<Player, Integer>();
	private int nbreDragonSpawn = 0; 
	
	public DefiListener(Game game) {
		this.game = game;
	}
	
	public void afficheValid(Player player,String message, boolean defiBonus, String niveauBonus) {
		message = message.substring(4);
		if (defiBonus) {
			String teamPlayer = game.teams.findTeamPlayer(player);
			 Bukkit.broadcastMessage("§7[§eBINGO§7] §fBonus: L'équipe " + game.teams.prefixeColorTeams.get(teamPlayer) + teamPlayer + " §fvient de réaliser le défi " + ChatColor.of("#FFBBFF") + message);
			 Bukkit.broadcastMessage("§7Elle remporte une §9récompense de §oniveau " + niveauBonus);
			 game.teams.defiDone.get(teamPlayer).put("§d§l" + message, false);
			 game.teams.defiValid.get(teamPlayer).put("§d§l" + message, false);
			 List<String> defiSup = new ArrayList<>();
			 for (List<String> defi : game.eventDefisBonus.defisEnCours) {
				 if (defi.get(0).equalsIgnoreCase("§d§l" + message)) {
					 defiSup = defi;
				 }
			 }
			 game.eventDefisBonus.defisEnCours.remove(defiSup);
			 int i = game.teams.nbreDefiValid.get(teamPlayer);
				game.teams.nbreDefiValid.put(teamPlayer, i - 1);
			
			game.eventDefisBonus.rewards(niveauBonus,player);
		} else {
			if (game.modeAffichage == "Chill") {
				Bukkit.broadcastMessage("§7[§eBINGO§7] §f§l" + game.teams.prefixeColorTeams.get(game.teams.findTeamPlayer(player)) + player.getDisplayName() + "§r a réalisé le défi §e§l" + message);
			} else {
				String teamPlayer = game.teams.findTeamPlayer(player);
				for (Player playerInTeam : game.teams.teamsHash.get(teamPlayer)) {
					if (playerInTeam.isOnline()) {
						playerInTeam.sendMessage("§7[§eBINGO§7] §f§l" + game.teams.prefixeColorTeams.get(game.teams.findTeamPlayer(player)) + player.getDisplayName() + "§r a réalisé le défi §e§l" + message);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void testAchievements(PlayerAdvancementDoneEvent e) {
		if (!game.gameLaunch) return;
		Player player = e.getPlayer();
		String teamPlayer = game.teams.findTeamPlayer(player);
		String advancement = e.getAdvancement().getKey().getKey();
		if (advancement.equals("story/enter_the_nether")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lBienvenue en Enfer", true);
		} else if (advancement.equals("story/follow_ender_eye")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lEn suivant les yeux...", true);
		} else if (advancement.equals("story/cure_zombie_villager")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lDoctor Strange", true);
		} else if (advancement.equals("nether/obtain_ancient_debris")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lAu fond des profondeurs", true);
		} else if (advancement.equals("nether/explore_nether")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lVoyage au bout de l'Enfer", true);
		} else if (advancement.equals("nether/obtain_crying_obsidian")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lSortez les mouchoirs", true);
	    } else if (advancement.equals("nether/get_wither_skull")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lUn bout de Cerbère", true);
	    } else if (advancement.equals("nether/find_bastion")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lLes mystérieuses cités d'or", true);
	    } else if (advancement.equals("nether/charge_respawn_anchor")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lChargé à bloc", true);
	    } else if (advancement.equals("nether/return_to_sender")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lRetour à l'envoyeur", true);
	    } else if (advancement.equals("end/root")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lC'est la fin?", true);
	    } else if (advancement.equals("adventure/sleep_in_bed")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lBonne nuit les petits", true);
	    } else if (advancement.equals("adventure/bullseye")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lDans le mille", true);
	    } else if (advancement.equals("adventure/spyglass_at_ghast")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lC'est un ballon?", true);
	    } else if (advancement.equals("nether/trade")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lStonks Industries", true);
	    } else if (advancement.equals("adventure/walk_on_powder_snow_with_leather_boots")) {
			game.teams.defiDone.get(teamPlayer).put("§d§lJésus des neiges", true);
	    } else if (advancement.equals("end/elytra")) {
	    	game.teams.defiDone.get(teamPlayer).put("§d§lJack je vole !", true);
	    }
	}
	
	@EventHandler
	private void BlockFormEvent(EntityBlockFormEvent e) {
		Entity entity = e.getEntity();
		if (e.getBlock().getType().equals(Material.WATER) && entity instanceof Player) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer((Player) entity)).get("§d§lAppelle moi Moïse") != true) {
				game.teams.defiDone.get(game.teams.findTeamPlayer((Player) entity)).put("§d§lAppelle moi Moïse", true);
			}
		}
	}
	
	@EventHandler
	private void mobSpawn(CreatureSpawnEvent e) {
		Entity entity = e.getEntity();
		if (entity.getType().equals(EntityType.ENDER_DRAGON)) {
			if (nbreDragonSpawn != 0) {
				World end = game.listWorld.get(2);
				Collection<Entity> listEntiteProche = Bukkit.getWorld(end.getName()).getNearbyEntities(new Location(end, 0, 65, 0), 150, 50, 150);
				List<Player> listPlayerProche = new ArrayList<>(); 
				System.out.println(listPlayerProche);
				for (Entity entityProche : listEntiteProche) {
					if (entityProche instanceof Player) {
						listPlayerProche.add((Player) entityProche);
					}
				}
				
				for (Player player : listPlayerProche) {
					String teamPlayer = game.teams.findTeamPlayer(player);
					game.teams.defiDone.get(teamPlayer).put("§d§lViens à moi Shenron", true);
				}
			}
			nbreDragonSpawn++;
		}
	}
	
	@EventHandler
	private void rideEvent (EntityMountEvent e) {
		if (!game.gameLaunch) return;
		if (e.getMount().getType().toString().equals("PIG")) {
			if (e.getMount().getLocation().getBlockY() >= 320) {
				game.teams.defiDone.get(game.teams.findTeamPlayer((Player) e.getEntity())).put("§d§lRedBull donne des ailes", true);
			}
		}
	}
	
	@EventHandler
	private void tntExplode(EntityExplodeEvent e) {
		if (!game.gameLaunch) return;
		Boolean craftingTable = false;
		for (Block block : e.blockList()) {
			if (block.getType().equals(Material.CRAFTING_TABLE)) {
				craftingTable = true;
			}
		}
		if (craftingTable) {
			List<Entity> proches = e.getEntity().getNearbyEntities(50, 50, 50);
			int lenListe = proches.size();
			for (int i=0; i<lenListe;i++) {
				if (proches.get(i).getType().toString().equals("PLAYER")) {
					Player player = (Player) proches.get(i);
					game.teams.defiDone.get(game.teams.findTeamPlayer(player)).put("§d§lOh no, my table is broken!", true);
					return;
				}
			}
		}
	}
	
	@EventHandler
	private void cauldronExtinguish(CauldronLevelChangeEvent e) {
		if (!game.gameLaunch) return;
		if (e.getReason().toString().equals("EXTINGUISH")) {
			if (e.getEntity().getWorld().toString().contains("nether")) {
				game.teams.defiDone.get(game.teams.findTeamPlayer((Player) e.getEntity())).put("§d§lSéance jacuzzi", true);
			}
		}
	}
	
	@EventHandler
	private void projectileHitkMob(ProjectileHitEvent e) {
		if (e.getHitEntity() == null) return;
		if (!game.gameLaunch) return;
		if (e.getHitEntity().getType().toString().equals("PIG")) {
			if (e.getEntity().getType().toString().equals("FIREWORK")) {
				e.getHitEntity().setCustomName("§e ");
			}
		}
		else if (e.getHitEntity().getType().toString().equals("SNOWMAN")) {
			if (e.getEntity().getType().toString().equals("SNOWBALL")) {
				game.teams.defiDone.get(game.teams.findTeamPlayer((Player) e.getEntity().getShooter())).put("§d§lCombat d'anthologie", true);
			}
		}
		else if (e.getHitEntity().getType().toString().equals("PLAYER")) {
			if (e.getEntity().getType().toString().equals("LLAMA_SPIT")) {
				game.teams.defiDone.get(game.teams.findTeamPlayer((Player) e.getHitEntity())).put("§d§lLa plus grosse racaille", true);
			}
		}
	}
	
	@EventHandler
	private void lightningStrike(EntityDamageByEntityEvent e) {
		if (!game.gameLaunch) return;
		if (e.getCause().toString().equals("LIGHTNING") && e.getEntity() instanceof Player) {
			game.teams.defiDone.get(game.teams.findTeamPlayer((Player) e.getEntity())).put("§d§lCoup de foudre", true);
		}
	}
	
	@EventHandler
	private void onPlayerDeath(PlayerDeathEvent e) {
		game.teams.defiDone.get(this.game.teams.findTeamPlayer((Player)e.getEntity())).put("§d§lVa te faire foutre!", true);
	}
	
	@EventHandler
	private void mobKill(EntityDeathEvent e) {
		if (!game.gameLaunch) return;
		if (!(e.getEntity().getKiller() instanceof Player)) return;
		Player killer = e.getEntity().getKiller();
		if (e.getEntity().getType().toString().equals("WITCH")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lAurevoir Sabrina !", Boolean.valueOf(true));
		}
		else if (e.getEntity().getType().toString().equals("SLIME")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lSlime Rancher", Boolean.valueOf(true));
		}
		else if (e.getEntity().getType().toString().equals("DOLPHIN")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lFaut pas Flipper", Boolean.valueOf(true));
		}
		else if (e.getEntity().getType().toString().equals("FOX")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lWhat does the fox say?", Boolean.valueOf(true));
		}
		else if (e.getEntity().getType().toString().equals("STRIDER")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lDestrier des Enfers", Boolean.valueOf(true));
		}
		else if (e.getEntity().getType().toString().equals("CAVE_SPIDER")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lArachnophobe", Boolean.valueOf(true));
		}
		else if (e.getEntity().getType().toString().equals("ELDER_GUARDIAN")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lIl est bon mon poisson", Boolean.valueOf(true));
		}
		else if (e.getEntity().getType().toString().equals("IRON_GOLEM")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lLe géant de fer", Boolean.valueOf(true));
		}
		else if (e.getEntity().getType().toString().equals("PIG")) {
			if (e.getEntity().getName().toString().equals("§e ")) {
				game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lC'est la fête de trop", Boolean.valueOf(true));
			}
		}
		else if (e.getEntity().getType().toString().equals("SILVERFISH")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lTéma la taille du rat", Boolean.valueOf(true));
		} else if (e.getEntity().getType().equals(EntityType.TURTLE)) {
			World worldPlayer = killer.getWorld();
			int i = 0;
			for (World world : game.listWorld) {
				if (worldPlayer.equals(world)) {
					if (i == 1) {
						game.teams.defiDone.get(this.game.teams.findTeamPlayer(killer)).put("§d§lMario contre Bowser", Boolean.valueOf(true));
					}
				}
				i++;
			}
		}
	}
	
	@EventHandler
	private void tameMob(EntityTameEvent e) {
		if (!game.gameLaunch) return;
		if (e.getEntityType().toString().equals("WOLF")){
			Player player = (Player) e.getOwner();
			if (numberWolfTame.containsKey(player)) {
				game.teams.defiDone.get(this.game.teams.findTeamPlayer((Player)e.getOwner())).put("§d§lWolf gang", Boolean.valueOf(true));
			} else {
				numberWolfTame.put(player, 1);
			}
		}
		else if (e.getEntityType().toString().equals("CAT")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer((Player)e.getOwner())).put("§d§lNyan Cat", Boolean.valueOf(true));
		}
		else if (e.getEntityType().toString().equals("HORSE")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer((Player)e.getOwner())).put("§d§lLe cheval c'est trop génial", Boolean.valueOf(true));
		}
	}
	
	@EventHandler
	private void parrotDismount(CreatureSpawnEvent e) {
		if (!game.gameLaunch) return;
		if (e.getSpawnReason().toString().equals("SHOULDER_ENTITY")) {
			List<Entity> proches = e.getEntity().getNearbyEntities(5, 5, 5);
			int lenListe = proches.size();
			for (int i=0; i<lenListe; i++) {
				if (proches.get(i).getType().toString().equals("PLAYER")) {
					game.teams.defiDone.get(this.game.teams.findTeamPlayer((Player) proches.get(i))).put("§d§lPirate des Caraïbes", Boolean.valueOf(true));
				}
			}
		}
	}
	
	@EventHandler
	private void raidTrigger(RaidTriggerEvent e) {
		if (!game.gameLaunch) return;
		game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lNous sommes en guerre", Boolean.valueOf(true));
	}
	
	@EventHandler
	private void breakBlock(BlockBreakEvent e) {
		if (!game.gameLaunch) return;
		Biome biome = e.getBlock().getBiome();
		if ((biome.equals(Biome.ICE_SPIKES) || biome.equals(Biome.FROZEN_OCEAN)) || biome.equals(Biome.DEEP_FROZEN_OCEAN)) {
			if (e.getBlock().getType().equals(Material.CHAIN)) {
				game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lLibérée, Délivrée", Boolean.valueOf(true));
			}
		}
		else if (e.getBlock().getType().equals(Material.SPAWNER)){
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lMonster Hunter", Boolean.valueOf(true));
		}
	}
	
	@EventHandler
	private void candleIgnite(BlockIgniteEvent e) {
		if (!game.gameLaunch) return;
		if (e.getBlock().getType().toString().contains("CANDLE")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lT'es pas net Baptiste?", Boolean.valueOf(true));
		}
	}
	
	@EventHandler
	private void playerConsume(PlayerItemConsumeEvent e) {
		if (!game.gameLaunch) return;
		if (e.getItem().getType().toString().equals("COOKIE")) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lCookie Monster", Boolean.valueOf(true));
		}
		else if (e.getItem().getType().toString().equals("POTION")) {
	    	if (e.getItem().getItemMeta().toString().contains("strong_swiftness")) {
	    		if (e.getPlayer().getInventory().getItemInOffHand().getType().toString().equals("BREAD")) {
	    			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lTu es un sorcier Harry !", Boolean.valueOf(true));
	    		}
	    	}
		}
	}
	
	@EventHandler
	private void piglinTrade(PiglinBarterEvent e) {
		if (!game.gameLaunch) return;
		List<Entity> proches = e.getEntity().getNearbyEntities(50, 50, 50);
		int lenListe = proches.size();
		for (int i=0; i<lenListe;i++) {
			if (proches.get(i).getType().toString().equals("PLAYER")) {
				game.teams.defiDone.get(this.game.teams.findTeamPlayer((Player) proches.get(i))).put("§d§lUne affaire en or", Boolean.valueOf(true));
			}
		}
	}
	
	@EventHandler
	private void expChange(PlayerLevelChangeEvent e) {
		if (!game.gameLaunch) return;
		if (e.getPlayer().getLevel() >= 30) {
			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lExpérimenté", Boolean.valueOf(true));
		}
	}
	
	@EventHandler
	private void sheepShear(PlayerShearEntityEvent e) {
		if (!game.gameLaunch) return;
		Sheep sheep = (Sheep) e.getEntity();
		if (sheep.getType().toString().equals("SHEEP")) {
			if (sheep.getColor().equals(DyeColor.PURPLE)) {
    			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lTricot", Boolean.valueOf(true));
			}
		}
	}
	
	@EventHandler
	private void endermanLook(EntityTargetLivingEntityEvent e) {
		if (!game.gameLaunch) return;
		if (e.getEntityType().toString().equals("ENDERMAN")) {
			if (e.getTarget() instanceof Player) {
				if (e.getReason().toString().equals("CLOSEST_PLAYER")) {
	    			game.teams.defiDone.get(this.game.teams.findTeamPlayer((Player) e.getTarget())).put("§d§lDuel de regard", Boolean.valueOf(true));
				}
			}
		}
	}
	
	@EventHandler
	private void playerInteractMob(PlayerInteractEntityEvent e) {
		if (!game.gameLaunch) return;
		if (e.getRightClicked().getType().toString().equals("BAT")) {
			if (e.getPlayer().getInventory().getItemInMainHand().getType().toString().equals("NAME_TAG")) {
				if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().toString().equals("Batman")) {
	    			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lBatman", Boolean.valueOf(true));
				}
			}
			else if (e.getPlayer().getInventory().getItemInOffHand().getType().toString().equals("NAME_TAG")) {
				if (e.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().toString().equals("Batman")) {
	    			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lBatman", Boolean.valueOf(true));
				}
			}
		}
		if (e.getRightClicked().getType().toString().equals("TURTLE")) {
			Turtle turtle = (Turtle) e.getRightClicked();
			if (e.getPlayer().getInventory().getItemInMainHand().getType().toString().equals("SEAGRASS") || e.getPlayer().getInventory().getItemInOffHand().getType().toString().equals("SEAGRASS")) {
				List<Entity> proches = turtle.getNearbyEntities(50, 50, 50);
				int lenListe = proches.size();
				for (int i=0; i<lenListe; i++) {
					if (proches.get(i).getType().toString().equals("TURTLE")) {
						if (((Turtle) proches.get(i)).isLoveMode()) {
			    			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lMichelangelo?", Boolean.valueOf(true));
						}
					}	
				}
			}
		}
	}
	
	@EventHandler
	private void boneMealComposter(PlayerInteractEvent e) {
		if (!game.gameLaunch) return;
		if (e.getClickedBlock() == null) {
			return;
		}
		if (e.getClickedBlock().getType().toString().equals("COMPOSTER")) {
			Block block = e.getClickedBlock();
			BlockData data = block.getBlockData();
			Levelled lev = (Levelled)data;
			if (lev.getLevel() == lev.getMaximumLevel()) {
    			game.teams.defiDone.get(this.game.teams.findTeamPlayer(e.getPlayer())).put("§d§lRecyclage", Boolean.valueOf(true));
			}
		}
	}
	
	private void setDefiDoneAndValid(Player p,String defi) {
		game.teams.defiDone.get(game.teams.findTeamPlayer(p)).put(defi,true);
		game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(defi,true);
	}
	
	public void testItems(Player p,String name, boolean defiBonus, String niveauDefi) {
		Boolean defiAdd = false; // si le défi est validé
		String teamPLayer = game.teams.findTeamPlayer(p);
		if (name.equalsIgnoreCase("§d§lVa te faire foutre!")) {
			if (game.teams.defiDone.get(teamPLayer).get(name) == true) {
				game.teams.defiValid.get(teamPLayer).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
		else if (name.equalsIgnoreCase("§d§lBoulets de canon")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.FIRE_CHARGE), 6)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lDéforestation")) {
		    if (p.getInventory().containsAtLeast(new ItemStack(Material.ACACIA_LOG), 64)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
		    }
	    }
	    else if (name.equalsIgnoreCase("§d§lAllahu Akbar")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.TNT), 5)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lTop Chef")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.CAKE), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lForgeron")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.ANVIL), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lLa dame du CDI")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BOOK), 16)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lVers l'infini et au-delà")) {
	    	if (p.getLocation().getY() >= 320) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lIngénieur informaticien")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.REDSTONE_BLOCK), 16)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lAlgoculteur")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.DRIED_KELP_BLOCK), 16)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lAurevoir Sabrina !")){
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lHalloween")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.JACK_O_LANTERN), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lCa colle...")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.HONEY_BOTTLE), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lEtrange pomme d'amour")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLDEN_APPLE), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lMcDonald's")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.POISONOUS_POTATO), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lFarming Simulator")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.HAY_BLOCK), 32)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lCauchemar en cuisine")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SUSPICIOUS_STEW), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lSlime Rancher")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lFaut pas Flipper")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lFée Clocharde")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.FEATHER), 31)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lAddict des seaux")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.WATER_BUCKET), 1)) {
	      		if (p.getInventory().containsAtLeast(new ItemStack(Material.LAVA_BUCKET), 1)) {
	        		if (p.getInventory().containsAtLeast(new ItemStack(Material.MILK_BUCKET), 1)) {
			    		setDefiDoneAndValid(p, name.toString());
			    		afficheValid(p, name, defiBonus, niveauDefi);
			    		int i = game.teams.nbreDefiValid.get(teamPLayer);
						game.teams.nbreDefiValid.put(teamPLayer, i + 1);
						defiAdd = true;
	        		}
	      		}
	    	} 
	    } 
	    else if (name.equalsIgnoreCase("§d§lWolf Gang")) {
	    	if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
	    		game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lNyan Cat")) {
	    	if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
	    		game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lWhat does the fox say?")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lLe cheval c'est trop génial")) {
	    	if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lBonne nuit les petits")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lTout est bon dans le cochon")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.PORKCHOP), 22)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lTu es grosse Mélissandre")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.PUMPKIN_PIE), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lDestrier des Enfers")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lY'a du bambou là !")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BAMBOO), 64)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lCollectionneur")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.COAL_BLOCK), 1)) {
	    		if (p.getInventory().containsAtLeast(new ItemStack(Material.REDSTONE_BLOCK), 1)) {
	        		if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK), 1)) {
	          			if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_BLOCK), 1)) {
	            			if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_BLOCK), 1)) {
	              				if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND_BLOCK), 1)) {
	                				if (p.getInventory().containsAtLeast(new ItemStack(Material.COPPER_BLOCK), 1)) {
	                  					if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD_BLOCK), 1)) {
	                    					if (p.getInventory().containsAtLeast(new ItemStack(Material.QUARTZ_BLOCK), 1)) {
	                    			    		setDefiDoneAndValid(p, name.toString());
	                    			    		afficheValid(p, name, defiBonus, niveauDefi);
	                    			    		int i = game.teams.nbreDefiValid.get(teamPLayer);
	                    						game.teams.nbreDefiValid.put(teamPLayer, i + 1);
	                    						defiAdd = true;
	                    					}
	                  					} 
	                				} 
	              				} 
	            			} 
	          			} 
	        		} 
	    		} 
	    	} 
		}
	    else if (name.equalsIgnoreCase("§d§lTu es un sorcier Harry !")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lBienvenue en Enfer")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lArachnophobe")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lVoyage au bout de l'Enfer")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lCoffre du néant")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.ENDER_CHEST), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lTrésor enfoui")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.HEART_OF_THE_SEA), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lIndiana Jones")) {
	    	if (p.getLocation().getBlock().getBiome().toString().contains("JUNGLE")) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lMerlin l'enchanteur")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.ENCHANTING_TABLE), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lJ'ai le bâton en feu !")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BLAZE_ROD), 2)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lSortez les mouchoirs")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lThe Walking Dead")) {
	    	if (p.getStatistic(Statistic.KILL_ENTITY, EntityType.ZOMBIE)>=29){
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lSOS Fantômes")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.PHANTOM_MEMBRANE), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lJe veux tes yeux")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.ENDER_PEARL), 3)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lChâteau rouge")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.RED_NETHER_BRICKS), 17)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lOld Town Road")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SADDLE), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lTerre colorée")) {
	    	int terracotta = 0;
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.WHITE_TERRACOTTA), 1)) {
	    		terracotta++;
	    	}
		    if (p.getInventory().containsAtLeast(new ItemStack(Material.ORANGE_TERRACOTTA), 1)) {
		    	terracotta++;
		    }
			if (p.getInventory().containsAtLeast(new ItemStack(Material.PINK_TERRACOTTA), 1)) {
			    terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.MAGENTA_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.LIGHT_BLUE_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.YELLOW_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.LIME_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.GRAY_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.LIGHT_GRAY_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.CYAN_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.PURPLE_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.BLUE_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.BROWN_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.RED_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.BLACK_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (p.getInventory().containsAtLeast(new ItemStack(Material.GREEN_TERRACOTTA), 1)) {
				terracotta++;
			}
			if (terracotta >= 8) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
		else if (name.equalsIgnoreCase("§d§lBob l'éponge cubique")) {
		    if (p.getInventory().containsAtLeast(new ItemStack(Material.SPONGE), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
		    }
		}
		else if (name.equalsIgnoreCase("§d§lMayo l'abeille")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.HONEY_BLOCK), 1)) {
	    		setDefiDoneAndValid(p, name.toString()); 
		    	afficheValid(p, name, defiBonus, niveauDefi);
		    	int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}

		}
		else if (name.equalsIgnoreCase("§d§lSous l'océan")) {
			for (ItemStack itemInventory : p.getInventory().getContents()) {
	    		if (itemInventory != null) {
	    			if (itemInventory.getType().equals(Material.TROPICAL_FISH_BUCKET)) {
		    			game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
						afficheValid(p, name, defiBonus, niveauDefi);
						int i = game.teams.nbreDefiValid.get(teamPLayer);
						game.teams.nbreDefiValid.put(teamPLayer, i + 1);
						defiAdd = true;
		    		}
	    		}
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lAu fond des profondeurs")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lEn suivant les yeux...")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
		    }
	    }
	    else if (name.equalsIgnoreCase("§d§lAssurance vie")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.TOTEM_OF_UNDYING), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
		else if (name.equalsIgnoreCase("§d§lBienvenue au pays des Schtroumpfs")) {
	    	if (p.getLocation().getBlock().getBiome().toString().contains("MUSHROOM")) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lQui dit mieux?")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.NETHERITE_INGOT), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lC'est la fin?")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lLa plus grosse racaille")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
		}
	    else if (name.equalsIgnoreCase("§d§lMon précieux")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.AMETHYST_BLOCK), 16)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lUn bout de Cerbère")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lDes paillettes dans ma vie Kévin")) {
	    	if (p.getStatistic(Statistic.KILL_ENTITY, EntityType.GLOW_SQUID)>=3){
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lAxo-loto")) {
	    /*	ItemStack axolotl = new ItemStack(Material.AXOLOTL_BUCKET);
	    	axolotlMeta = axolotl.getItemMeta();
	    	axolotlMeta.
	    	if (p.getInventory().containsAtLeast(new ItemStack(axolotl), 1)) {
	    		Bukkit.broadcastMessage(""); 
	    	}*/
	    }
	    else if (name.equalsIgnoreCase("§d§lBoules scintillantes")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.GLOW_BERRIES), 5)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lLes mystérieuses cités d'or")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lCa pique...")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.POINTED_DRIPSTONE), 20)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lDans le mille")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lCoup de foudre")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lPirate des Caraïbes")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lManoir hanté")) { // DESACTIVER (PROBLEME AVEC LOCATE)
            Location manoir = p.getWorld().locateNearestStructure(p.getLocation(), StructureType.WOODLAND_MANSION, 10, false);
            if (manoir != null) {
                Integer distanceX = (int) (p.getLocation().getX() - manoir.getX());
                Integer distanceZ = (int) (p.getLocation().getZ() - manoir.getZ());
                if (Math.sqrt((Math.pow(distanceX, 2) + Math.pow(distanceZ, 2))) <= 80) {
                    setDefiDoneAndValid(p, name.toString()); 
                    afficheValid(p, name, defiBonus, niveauDefi);
                    int i = game.teams.nbreDefiValid.get(teamPLayer);
                    game.teams.nbreDefiValid.put(teamPLayer, i + 1);
                    defiAdd = true;
                }
            }
        }
	    else if (name.equalsIgnoreCase("§d§lPoséidon")) {
	    	if (p.getInventory().getContents() != null) {
		    	for (ItemStack itemInventory : p.getInventory().getContents()) {
		    		if (itemInventory != null) {
		    			if (itemInventory.getType().equals(Material.TRIDENT)) {
			    			game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
							afficheValid(p, name, defiBonus, niveauDefi);
							int i = game.teams.nbreDefiValid.get(teamPLayer);
							game.teams.nbreDefiValid.put(teamPLayer, i + 1);
							defiAdd = true;
			    		}
		    		}
		    	}
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lNous sommes en guerre")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lLibérée, Délivrée")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lCombat d'anthologie")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lT'es pas net Baptiste?")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lJésus des neiges")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lChargé à bloc")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lUne affaire en or")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lPlutôt Krokmou ou Spyro?")) {
	    	if (p.getInventory().getHelmet() == null) return;
	    	if (p.getInventory().getHelmet().equals(new ItemStack(Material.DRAGON_HEAD))) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lSéance jacuzzi")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lAux armes citoyens")) { // désactivé probleme detection
			for (int i=0; i<p.getInventory().getSize(); i++) {
				if (p.getInventory().getItem(i) != null && p.getInventory().getItem(i).toString().contains("SHIELD")) {
					if (p.getInventory().getItem(i).getItemMeta().toString().equals("TILE_ENTITY_META:{meta-type=TILE_ENTITY, internal=H4sIAAAAAAAA/+NiYOBi4HPKyU/Ods0rySypDElMZ2ZgcUosTmUAAk4GjoDEkpLUorxiLiCXiYOBHcpnYMopZmZgdc7PyS8CynAzIEsVIUvxAaWYMlMYBHIz81KTixLTSqySEvPyUoFyAG4OTLd+AAAA, blockMaterial=SHIELD}")) {
			    		setDefiDoneAndValid(p, name.toString());
			    		afficheValid(p, name, defiBonus, niveauDefi);
			    		int i2 = game.teams.nbreDefiValid.get(teamPLayer);
						game.teams.nbreDefiValid.put(teamPLayer, i2 + 1);
						defiAdd = true;
					}
					else if (p.getInventory().getItem(i).getItemMeta().toString().equals("TILE_ENTITY_META:{meta-type=TILE_ENTITY, internal=H4sIAAAAAAAA/+NiYOBi4HPKyU/Ods0rySypDElMZ2ZgcUosTmUAAk4GjoDEkpLUorxiLiCXiYOBHcpnYCoqZmZgdc7PyS8CyvAxIEvlIEtxA6WYMlMYBHIz81KTixLTSqySEvPyUoFyAMyYM/V+AAAA, blockMaterial=SHIELD}")) {
			    		setDefiDoneAndValid(p, name.toString());
			    		afficheValid(p, name, defiBonus, niveauDefi);
			    		int i2 = game.teams.nbreDefiValid.get(teamPLayer);
						game.teams.nbreDefiValid.put(teamPLayer, i2 + 1);
						defiAdd = true;
					}
				}
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lRetour à l'envoyeur")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lHallucinogènes")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BROWN_MUSHROOM), 1)) {
	    		if (p.getInventory().containsAtLeast(new ItemStack(Material.RED_MUSHROOM), 1)) {
	    			if (p.getInventory().containsAtLeast(new ItemStack(Material.CRIMSON_FUNGUS), 1)) {
	    				if (p.getInventory().containsAtLeast(new ItemStack(Material.WARPED_FUNGUS), 1)) {
	    		    		setDefiDoneAndValid(p, name.toString());
	    		    		afficheValid(p, name, defiBonus, niveauDefi);
	    		    		int i = game.teams.nbreDefiValid.get(teamPLayer);
	    					game.teams.nbreDefiValid.put(teamPLayer, i + 1);
	    					defiAdd = true;
	    				}
	    			}
	    		}
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lNouvelle énergie")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.DAYLIGHT_DETECTOR), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lLady Gaga")) {
	    	if (p.getInventory().getHelmet() == null && p.getInventory().getBoots() == null && p.getInventory().getLeggings() == null && p.getInventory().getChestplate() == null) {
	    		return;
	    	}
	    	if (p.getInventory().getHelmet().equals(new ItemStack(Material.GOLDEN_HELMET))) {
		    	if (p.getInventory().getChestplate().equals(new ItemStack(Material.GOLDEN_CHESTPLATE))) {
			    	if (p.getInventory().getLeggings().equals(new ItemStack(Material.GOLDEN_LEGGINGS))) {
				    	if (p.getInventory().getBoots().equals(new ItemStack(Material.GOLDEN_BOOTS))) {
				    		setDefiDoneAndValid(p, name.toString());
				    		afficheValid(p, name, defiBonus, niveauDefi);
				    		int i = game.teams.nbreDefiValid.get(teamPLayer);
							game.teams.nbreDefiValid.put(teamPLayer, i + 1);
							defiAdd = true;
				    	}
			    	}
		    	}
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lSac à dos, sac à dos")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SHULKER_BOX), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lExpérimenté")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lMichelangelo?")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lStonks Industries")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lIl est bon mon poisson")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lTricot")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lLe géant de fer")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lRecyclage")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lBatman")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lRemède magique")) {
	    	PotionEffect effect = p.getPotionEffect(PotionEffectType.REGENERATION);
	    	if (effect != null && effect.getAmplifier()==1) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lArmure étincelante")) {
	    	if (p.getInventory().getHelmet() == null && p.getInventory().getBoots() == null && p.getInventory().getLeggings() == null && p.getInventory().getChestplate() == null) {
	    		return;
	    	}
	    	if (p.getInventory().getHelmet().equals(new ItemStack(Material.DIAMOND_HELMET))) {
		    	if (p.getInventory().getChestplate().equals(new ItemStack(Material.DIAMOND_CHESTPLATE))) {
			    	if (p.getInventory().getLeggings().equals(new ItemStack(Material.DIAMOND_LEGGINGS))) {
				    	if (p.getInventory().getBoots().equals(new ItemStack(Material.DIAMOND_BOOTS))) {
				    		setDefiDoneAndValid(p, name.toString());
				    		afficheValid(p, name, defiBonus, niveauDefi);
				    		int i = game.teams.nbreDefiValid.get(teamPLayer);
							game.teams.nbreDefiValid.put(teamPLayer, i + 1);
							defiAdd = true;
				    	}
			    	}
		    	}
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lJusqu'aux cieux")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BEACON), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lRails de coke")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SUGAR), 32)) {
	    		if (p.getInventory().containsAtLeast(new ItemStack(Material.RAIL), 32)) {
		    		setDefiDoneAndValid(p, name.toString());
		    		afficheValid(p, name, defiBonus, niveauDefi);
		    		int i = game.teams.nbreDefiValid.get(teamPLayer);
					game.teams.nbreDefiValid.put(teamPLayer, i + 1);
					defiAdd = true;
	    		}
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lC'est la fête de trop")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lDrôle de porte bonheur")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.RABBIT_FOOT), 1)) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lTéma la taille du rat")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lRéparation express !")) {
	    	if (!(p.getInventory().getHelmet() == null)) {
		    	ItemStack helmetPlayer = p.getInventory().getHelmet();
		    	if (helmetPlayer.getType().equals(Material.IRON_HELMET)) {
		    		if (p.getInventory().getHelmet().getEnchantments().toString().contains("minecraft:mending")) {
			    		setDefiDoneAndValid(p, name.toString());
			    		afficheValid(p, name, defiBonus, niveauDefi);
			    		int i = game.teams.nbreDefiValid.get(teamPLayer);
						game.teams.nbreDefiValid.put(teamPLayer, i + 1);
						defiAdd = true;
		    		}
		    	}
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lCookie Monster")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lFishing Planet")) {
	    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SALMON), 1)) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.COD), 1)) {
			    	if (p.getInventory().containsAtLeast(new ItemStack(Material.PUFFERFISH), 1)) {
				    	if (p.getInventory().containsAtLeast(new ItemStack(Material.TROPICAL_FISH), 1)) {
				    		setDefiDoneAndValid(p, name.toString());
				    		afficheValid(p, name, defiBonus, niveauDefi);
				    		int i = game.teams.nbreDefiValid.get(teamPLayer);
							game.teams.nbreDefiValid.put(teamPLayer, i + 1);
							defiAdd = true;
				    	}
			    	}
		    	}
	    	}
	    }
	    else if (name.equalsIgnoreCase("§d§lDuel de regard")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lMonster Hunter")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lDoctor Strange")) {
			if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    }
	    else if (name.equalsIgnoreCase("§d§lAffamé")) {
	    	PotionEffect effect = p.getPotionEffect(PotionEffectType.HUNGER);
	    	if (effect != null && effect.getAmplifier()==0) {
	    		setDefiDoneAndValid(p, name.toString());
	    		afficheValid(p, name, defiBonus, niveauDefi);
	    		int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    } else if (name.equalsIgnoreCase("§d§lRedBull donne des ailes")) {
	    	if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    } else if (name.equalsIgnoreCase("§d§lOh no, my table is broken!")) {
	    	if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
				game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
			}
	    } else if (name.equalsIgnoreCase("§d§lViens à moi Shenron")) {
	    	if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
	    		game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    } else if (name.equalsIgnoreCase("§d§lAppelle moi Moïse")) {
	    	if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
	    		game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    } else if (name.equalsIgnoreCase("§d§lMario contre Bowser")) {
	    	if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
	    		game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    } else if (name.equalsIgnoreCase("§d§lJack je vole !")) {
	    	if (game.teams.defiDone.get(game.teams.findTeamPlayer(p)).get(name) == true) {
	    		game.teams.defiValid.get(game.teams.findTeamPlayer(p)).put(name,true);
				afficheValid(p, name, defiBonus, niveauDefi);
				int i = game.teams.nbreDefiValid.get(teamPLayer);
				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
				defiAdd = true;
	    	}
	    } else if (name.equalsIgnoreCase("§d§lEcris l’histoire")) {
	    	for (ItemStack item : p.getInventory()) {
	    		if (item != null) {
	    			if (item.getType().equals(Material.WRITTEN_BOOK)) {
		    			BookMeta bookMeta = (BookMeta) item.getItemMeta();
		    			if (bookMeta.getAuthor().equals(p.getName())) {
		    				setDefiDoneAndValid(p, name);
		    				afficheValid(p, name, defiBonus, niveauDefi);
		    				int i = game.teams.nbreDefiValid.get(teamPLayer);
		    				game.teams.nbreDefiValid.put(teamPLayer, i + 1);
		    				defiAdd = true;
		    			}
		    		}
	    		}
	    	}
	    }
		
		if (!defiAdd) {
			if (defiBonus) {
				p.sendMessage("§8[§c⚠§8] §fVous n'avez pas encore réalisé ce défi...");
			}
		}
	}
	
	@EventHandler
	public void testItems(InventoryClickEvent e) {
		if (!game.gameLaunch) return;
		if (e.getView().getTitle().equalsIgnoreCase("§3§lBINGO")) {
			ItemStack item = e.getCurrentItem();
			if (item == null) {
				return;
			}
			Player p = (Player) e.getWhoClicked();
			String teamPlayer = game.teams.findTeamPlayer(p);
			if (!teamPlayer.equalsIgnoreCase("Spectator") && !(p.getGameMode() == GameMode.SPECTATOR)) {
				testItems(p, item.getItemMeta().getDisplayName(), false, "0");
				for (Player playerGui : game.teams.playersOnBingoGui.get(teamPlayer)) {
					game.bingoGui.openGui(playerGui, teamPlayer);
				}
				game.bingoGui.openGui(p, teamPlayer);
				if (game.modeVictoire.equalsIgnoreCase("Bingo")) {
					game.verifGrilleBingo(p);
				} else {
					if (game.teams.nbreDefiValid.get(teamPlayer) == 25) {
						game.teams.teamCanSpectator.put(teamPlayer, true);
						for (Player player1 : Bukkit.getOnlinePlayers()) {
							player1.setGameMode(GameMode.SPECTATOR);
							player1.sendMessage("§7[§eBINGO§7] §fL'équipe " + game.teams.prefixeColorTeams.get(teamPlayer) + teamPlayer + " §fa fini sa partie");
							player1.sendMessage("Elle peut continuer de jouer ou devenir spectatrice");
							if (game.teams.teamsHash.get(teamPlayer).contains(player1)) {
								player1.sendMessage("§7§oUtilisez la commande §e§o/spec §7§opour devenir spectateur");
							}
						}
					}
				}
			} else {
				switch (item.getType()) {
				case ORANGE_CONCRETE:
					game.spectatorInBingoTeams.put(p, "Orange");
					break;
				
				case RED_CONCRETE:
					game.spectatorInBingoTeams.put(p, "Rouge");
					break;
					
				case PINK_CONCRETE:
					game.spectatorInBingoTeams.put(p, "Rose");
					break;
					
				case PURPLE_CONCRETE:
					game.spectatorInBingoTeams.put(p, "Violet");
					break;
					
				case LIME_CONCRETE:
					game.spectatorInBingoTeams.put(p, "Vert");
					break;
					
				case LIGHT_BLUE_CONCRETE:
					game.spectatorInBingoTeams.put(p, "Bleu");
					break;

				default:
					break;
				}
				game.bingoGui.openGui(p, teamPlayer);
			}
			
			
			
			e.setCancelled(true);
		}
	}
}