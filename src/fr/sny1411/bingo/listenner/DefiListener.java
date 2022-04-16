package fr.sny1411.bingo.listenner;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.inventory.ItemStack;

public class DefiListener implements Listener {
	@EventHandler
	public void testAchievements(PlayerAdvancementDoneEvent e) {
		String player = e.getPlayer().getName();
		String advancement = e.getAdvancement().getKey().getKey();
		if (advancement.equals("story/enter_the_nether")) {
			Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nBienvenue en Enfer");
		} else if (advancement.equals("story/follow_ender_eye")) {
			Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nEn suivant les yeux...");
		} else if (advancement.equals("story/cure_zombie_villager")) {
			Bukkit.broadcastMessage("§7[§eBINGO§7]" + player + " §ra terminé le défi §d§nDoctor Strange");
		} else if (advancement.equals("nether/obtain_ancient_debris")) {
			Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nAu fond des profondeurs");
		} else if (advancement.equals("nether/explore_nether")) {
			Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nVoyage au bout de l'Enfer");
		} else if (advancement.equals("nether/obtain_crying_obsidian")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nSortez les mouchoirs");
	    } else if (advancement.equals("nether/get_wither_skull")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nUn bout de Cerb");
	    } else if (advancement.equals("nether/find_bastion")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nLes mystérieuses cités d'or");
	    } else if (advancement.equals("nether/charge_respawn_anchor")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nChargé à bloc");
	    } else if (advancement.equals("nether/return_to_sender")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nRetour à l'envoyeur");
	    } else if (advancement.equals("end/root")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nC'est la fin?");
	    } else if (advancement.equals("adventure/sleep_in_bed")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nBonne nuit les petits");
	    } else if (advancement.equals("adventure/bullseye")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nDans le mille");
	    } else if (advancement.equals("adventure/spyglass_at_ghast")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nC'est un ballon?");
	    } else if (advancement.equals("nether/trade")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nStonks Industries");
	    } else if (advancement.equals("adventure/walk_on_powder_snow_with_leather_boots")) {
	    	Bukkit.broadcastMessage("§7[§eBINGO§7] " + player + " §ra terminé le défi §d§nJésus des neiges");
	    }
	}
	@EventHandler
	public void fireworkMob(ProjectileHitEvent e) {
		if (e.getHitEntity().getType().toString().equals("PIG")) {
			if (e.getEntity().getType().toString().equals("FIREWORK")) {
				e.getHitEntity().setCustomName("§e ");
			}
		}
		else if (e.getHitEntity().getType().toString().equals("SNOWMAN")) {
			if (e.getEntity().getType().toString().equals("SNOWBALL")) {
				Bukkit.broadcastMessage("owo");
			}
		}
		else if (e.getHitEntity().getType().toString().equals("PLAYER")) {
			if (e.getEntity().getType().toString().equals("LLAMA_SPIT")) {
				Bukkit.broadcastMessage("iwi");
			}
		}
	}
	
	@EventHandler
	public void lightningStrike(EntityDamageByEntityEvent e) {
		if (e.getCause().toString().equals("LIGHTNING")) {
			Bukkit.broadcastMessage("");
		}
	}
	
	@EventHandler
	public void mobKill(EntityDeathEvent e) {
		if (e.getEntity().getType().toString().equals("PLAYER")) {
			Bukkit.broadcastMessage("");
		}
		else if (e.getEntity().getType().toString().equals("WITCH")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("SLIME")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("DOLPHIN")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("FOX")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("STRIDER")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("CAVE_SPIDER")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("ZOMBIE")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("GLOW_SQUID")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("ELDER_GUARDIAN")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("IRON_GOLEM")) {
			Bukkit.broadcastMessage("");
		}
		if (e.getEntity().getType().toString().equals("PIG")) {
			if (e.getEntity().getName().toString().equals("§e ")) {
				Bukkit.broadcastMessage("ptn");
			}
		}
		if (e.getEntity().getType().toString().equals("SILVERFISH")) {
			Bukkit.broadcastMessage("");
		}
	}
	
	@EventHandler
	public void tameMob(EntityTameEvent e) {
		if (e.getEntityType().toString().equals("WOLF")){
			Bukkit.broadcastMessage("");
		}
		else if (e.getEntityType().toString().equals("CAT")) {
			Bukkit.broadcastMessage("");
		}
		else if (e.getEntityType().toString().equals("HORSE")) {
			Bukkit.broadcastMessage("");
		}
	}
	
	@EventHandler
	public void parrotDismount(CreatureSpawnEvent e) {
		if (e.getSpawnReason().toString().equals("SHOULDER_ENTITY")) {
			Bukkit.broadcastMessage("");
		}
	}
	
	@EventHandler
	public void raidTrigger(RaidTriggerEvent e) {
		Bukkit.broadcastMessage(e.getPlayer().getName().toString());
	}
	
	@EventHandler
	public void breakBlock(BlockBreakEvent e) {
		if (e.getBlock().getBiome().toString().equals("ICE_SPIKES")) {
			if (e.getBlock().getType().toString().equals("CHAIN")) {
				Bukkit.broadcastMessage("");
			}
		}
	}
	
	@EventHandler
	public void candleIgnite(BlockIgniteEvent e) {
		if (e.getBlock().getType().toString().contains("CANDLE")) {
			Bukkit.broadcastMessage("");
		}
	}
	
	@EventHandler
	public void piglinTrade(PiglinBarterEvent e) {
		List<Entity> proches = e.getEntity().getNearbyEntities(50, 50, 50);
		int lenListe = proches.size();
		for (int i=0; i<lenListe;i++) {
			if (proches.get(i).getType().toString().equals("PLAYER")) {
				Bukkit.broadcastMessage(proches.get(i).getName());
			}
		}
	}
	
	@EventHandler
	public void testItems(InventoryClickEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase("§3§lBINGO")) {
			ItemStack item = e.getCurrentItem();
			Player p = (Player) e.getWhoClicked();
			if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lVa te faire foutre")) {
		    /*	if () {
		    		
		    	}*/
		    }
			else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lBoulets de canon")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.FIRE_CHARGE), 6)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lDéforestation")) {
			    if (p.getInventory().containsAtLeast(new ItemStack(Material.ACACIA_LOG), 64)) {
			      Bukkit.broadcastMessage("d"); 
			    }
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lAllahu Akbar")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.TNT), 5)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lTop Chef")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.CAKE), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lForgeron")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.ANVIL), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lLa dame du CDI")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BOOK), 16)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lVers l'infini et au-delà")) {
		    	if (p.getLocation().getY() >= 320) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lIngénieur informaticien")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.REDSTONE_BLOCK), 16)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lAlgoculteur")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.DRIED_KELP_BLOCK), 16)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lAurevoir Sabrina !")){
		    /*	if (){
		     
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lHalloween")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.JACK_O_LANTERN), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCa colle...")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.HONEY_BOTTLE), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lEtrange pomme d'amour")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLDEN_APPLE), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lMcDonald's")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.POISONOUS_POTATO), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lFarming Simulator")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.HAY_BLOCK), 32)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCauchemar en cuisine")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SUSPICIOUS_STEW), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lSlime Rancher")) {
		    /* if () {
		     
		     	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lFaut pas Flipper")) {
		    /*	if () {
		    	
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lFée Clocharde")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.FEATHER), 31)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lAddict des seaux")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.WATER_BUCKET), 1)) {
		      		if (p.getInventory().containsAtLeast(new ItemStack(Material.LAVA_BUCKET), 1)) {
		        		if (p.getInventory().containsAtLeast(new ItemStack(Material.MILK_BUCKET), 1)) {
		        			Bukkit.broadcastMessage("d"); 
		        		}
		      		}
		    	} 
		    } 
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lWolf Gang")) {
		    /*	if (){
		     
		     	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lNyan Cat")) {
		    /*	if (){
		     
		     	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lWhat does the fox say?")) {
		    /*	if (){
		     
		     	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lLe cheval c'est trop génial")) {
		    /*	if (){
		     
		     	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lBonne nuit les petits")) {
		    /*	if (){
		     
		     	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lTout est bon dans le cochon")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.PORKCHOP), 22)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lTu es grosse Mélissandre")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.PUMPKIN_PIE), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lDestrier des Enfers")) {
		    /*	if (){
		     
		     	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lY'a du bambou là !")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BAMBOO), 64)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCollectionneur")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.COAL_BLOCK), 1)) {
		    		if (p.getInventory().containsAtLeast(new ItemStack(Material.REDSTONE_BLOCK), 1)) {
		        		if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK), 1)) {
		          			if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_BLOCK), 1)) {
		            			if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_BLOCK), 1)) {
		              				if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND_BLOCK), 1)) {
		                				if (p.getInventory().containsAtLeast(new ItemStack(Material.COPPER_BLOCK), 1)) {
		                  					if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD_BLOCK), 1)) {
		                    					if (p.getInventory().containsAtLeast(new ItemStack(Material.QUARTZ_BLOCK), 1)) {
		                    						Bukkit.broadcastMessage("d"); 
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
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lTu es un sorcier Harry !")) {
		    /*	if (p.getInventory().containsAtLeast(new ItemStack(Material.POTION), 1)) {
		    		if (p.getInventory().containsAtLeast(new ItemStack(Material.BREAD), 1)) {
		    			Bukkit.broadcastMessage("d");
		    		}
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lBienvenue en Enfer")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lArachnophobe")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lVoyage au bout de l'Enfer")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCoffre du néant")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.ENDER_CHEST), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lTrésor enfoui")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.HEART_OF_THE_SEA), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lIndiana Jones")) {
		    	if (p.getLocation().getBlock().getBiome().toString().contains("JUNGLE")) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lMerlin l'enchanteur")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.ENCHANTING_TABLE), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lJ'ai le bâton en feu !")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BLAZE_ROD), 2)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lSortez les mouchoirs")) {
		    /*	if (){
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lThe Walking Dead")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lSOS Fantômes")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.PHANTOM_MEMBRANE), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lJe veux tes yeux")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.ENDER_PEARL), 3)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lChâteau rouge")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.RED_NETHER_BRICKS), 17)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lOld Town Road")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SADDLE), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lTerre colorée")) {
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
				if (terracotta >= 10) {
		    		Bukkit.broadcastMessage(""); 
				}
		    }
			else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lBob l'éponge cubique")) {
			    if (p.getInventory().containsAtLeast(new ItemStack(Material.SPONGE), 1)) {
			    	Bukkit.broadcastMessage(""); 
			    }
			}
			else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lMayo l'éponge")) {
				if (p.getInventory().containsAtLeast(new ItemStack(Material.HONEY_BLOCK), 1)) {
			    	Bukkit.broadcastMessage(""); 
				}
			}
			else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lSous l'océan")) {
			    if (p.getInventory().containsAtLeast(new ItemStack(Material.TROPICAL_FISH_BUCKET), 1)) {
			    	Bukkit.broadcastMessage(""); 
			    }
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lAu fond des profondeurs")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lEn suivant les yeux...")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
			    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lAssurance vie")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.TOTEM_OF_UNDYING), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
			    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lBienvenue au pays des Schtroumpfs")) {
		    	if (p.getLocation().getBlock().getBiome().toString().contains("MUSHROOM")) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lQui dit mieux?")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.NETHERITE_INGOT), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lC'est la fin?")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lLa plus grosse racaille")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
	    		}*/
			}
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lMon précieux")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.AMETHYST_BLOCK), 16)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lUn bout de Cerbère")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lDes paillettes dans ma vie Kévin")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lAxo-loto")) {
		    /*	ItemStack axolotl = new ItemStack(Material.AXOLOTL_BUCKET);
		    	axolotlMeta = axolotl.getItemMeta();
		    	axolotlMeta.
		    	if (p.getInventory().containsAtLeast(new ItemStack(axolotl), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lBoules scintillantes")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.GLOW_BERRIES), 5)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lLes mystérieuses cités d'or")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCa pique...")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.POINTED_DRIPSTONE), 20)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lDans le mille")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCoup de foudre")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lPirate des Caraïbes")) {
			/*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lManoir hanté")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lPoséidon")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.TRIDENT), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lNous sommes en guerre")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lLibérée, Délivrée")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCombat d'anthologie")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lT'es pas net Baptiste?")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lJésus des neiges")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lChargé à bloc")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lUne affaire en or")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lPlutôt Kroukmou ou Spyro?")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lSéance jacuzzi")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lAux armes citoyens")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lRetour à l'envoyeur")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lHallucinogènes")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BROWN_MUSHROOM), 1)) {
		    		if (p.getInventory().containsAtLeast(new ItemStack(Material.RED_MUSHROOM), 1)) {
		    			if (p.getInventory().containsAtLeast(new ItemStack(Material.CRIMSON_FUNGUS), 1)) {
		    				if (p.getInventory().containsAtLeast(new ItemStack(Material.WARPED_FUNGUS), 1)) {
		    					Bukkit.broadcastMessage(""); 
		    				}
		    			}
		    		}
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lNouvelle énergie")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.DAYLIGHT_DETECTOR), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lLady Gaga")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lSac à dos, sac à dos")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SHULKER_BOX), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lExpérimenté")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lMichelangelo?")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lStonks Industries")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lIl est gros le poisson")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lTricot")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lLe géant de fer")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lRecyclage")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lBatman")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lRemède magique")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lArmure étincelante")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lJusqu'aux cieux")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.BEACON), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lRails de coke")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SUGAR), 32)) {
		    		if (p.getInventory().containsAtLeast(new ItemStack(Material.RAIL), 32)) {
		    		Bukkit.broadcastMessage(""); 
		    		}
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lC'est la fête de trop")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lDrôle de porte bonheur")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.RABBIT_FOOT), 1)) {
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lTéma la taille du rat")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lRéparation express !")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.ENCHANTED_BOOK), 1)) { //ICI VERIFIER QUE CEST UN LIVRE MENDING !!!!
		    		Bukkit.broadcastMessage(""); 
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lCookie Monster")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lFishin Planet")) {
		    	if (p.getInventory().containsAtLeast(new ItemStack(Material.SALMON), 1)) {
			    	if (p.getInventory().containsAtLeast(new ItemStack(Material.COD), 1)) {
				    	if (p.getInventory().containsAtLeast(new ItemStack(Material.PUFFERFISH), 1)) {
					    	if (p.getInventory().containsAtLeast(new ItemStack(Material.TROPICAL_FISH), 1)) {
					    		Bukkit.broadcastMessage(""); 
					    	}
				    	}
			    	}
		    	}
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lDuel de regard")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lMonster Hunter")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
		    else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§d§lDoctor Strange")) {
		    /*	if () {
		    		Bukkit.broadcastMessage(""); 
		    	}*/
		    }
	    }
	}
}
