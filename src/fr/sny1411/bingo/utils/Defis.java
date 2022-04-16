package fr.sny1411.bingo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Defis {
	private SkullCustom skull = new SkullCustom();
	public int nbreEasy = 0;
	public int nbreMedium = 0;
	public int nbreHard = 0;
	public int nbreExtreme = 0;
	public int easy = 6;
	public int medium = 13;
	public int hard = 6;
	public int extreme = 0;
	public List<List<String>> defi;
	public Hashtable<List<String>, ItemStack> grilleDisplay;
	
	public boolean verifNbMaxDefi() {
		if ((easy + medium + hard + extreme) < 25) {
			return true;
		} else {
			return false;
		}
	}
	
	public void presetOnePlayer () {
		this.easy = 13;
		this.medium = 8;
		this.hard = 4;
		this.extreme = 0;
	}
	
	public void presetTwoPlayers() {
		this.easy = 10;
		this.medium = 10;
		this.hard = 5;
		this.extreme = 0;
	}
	
	public void presetThreePlayers() {
		this.easy = 6;
		this.medium = 13;
		this.hard = 6;
		this.extreme = 0;
	}
	
	public void presetFourPlayers() {
		this.easy = 2;
		this.medium = 10;
		this.hard = 12;
		this.extreme = 1;
	}
	
	public void resetDefi() {
		this.easy = 6;
		this.medium = 13;
		this.hard = 6;
		this.extreme = 0;
	}
	public Defis() {
		defi = new ArrayList<List<String>>();
		defi.add(new ArrayList<String>(Arrays.asList("§d§lVa te faire foutre!","§e§oMeurs.","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lBoulets de canon","§e§oRécupérer 6 fire charges","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lDéforestation","§e§oRécupérer 64 bûches d'acacia","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lAllahu Akbar","§e§oPosséder 5 TNT","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lTop Chef","§e§oPosséder un gâteau","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lForgeron","§e§oPosséder une enclume","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lLa dame du CDI","§e§oPosséder 16 livres","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lVers l'infini et au-delà","§e§oSe trouver à la couche la plus élevée","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lIngénieur informaticien","§e§oPosséder 16 blocks de redstone","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lAlgoculteur","§e§oPosséder 16 blocks d'algues séchées","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lAurevoir Sabrina !","§e§oTuer une sorcière","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lHalloween","§e§oPosséder une jack'o'lantern","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lCa colle...","§e§oRécupérer une fiole de miel","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lEtrange pomme d'amour","§e§oPosséder une pomme en or","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lMcDonald's","§e§oTrouver une patate empoisonnée","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lFarming Simulator","§e§oPosséder 32 blocks de foin","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lCauchemar en cuisine","§e§oTrouver une soupe suspecte","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lSlime Rancher","§e§oTuer un slime","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lFaut pas Flipper","§e§oTuer un dauphin","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lFée clocharde","§e§oRécuper 31 plumes","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lAddict des seaux","§e§oPosséder 1 seau d'eau, de lave et de lait","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lWolf gang","§e§oApprivoiser 2 loups","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lNyan Cat","§e§oApprivoiser 1 chat","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lWhat does the fox say?","§e§oTuer 1 renard","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lLe cheval c'est trop génial","§e§oApprivoiser 1 cheval","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lBonne nuit les petits","§e§oS'allonger dans un lit","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lTout est bon dans le cochon","§e§oRécupérer 22 côtelettes de porc crues","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lTu es grosse Mélissandre","§e§oPosséder une tarte à la citrouille","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lDestrier des Enfers","§e§oTuer un strider","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lY'a du bambou là !","§e§oRécupérer 64 bambous","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lCollectionneur","§e§oPosséder un block de chaque minerai, sauf la Netherite","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lTu es un sorcier Harry !","§e§oPosséder une potion de speed II et une baguette","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lBienvenue en Enfer","§e§oPénétrer dans le Nether","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lArachnophobe","§e§oTuer une cave spider","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lVoyage au bout de l'Enfer","§e§oTrouver tous les biomes du Nether","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lCoffre du néant","§e§oPosséder un ender chest","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lTrésor enfoui","§e§oTrouver un coeur de l'océan","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lIndiana Jones","§e§oTrouver une jungle","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lMerlin l'enchanteur","§e§oPosséder une table d'enchantement","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lJ'ai le bâton en feu !","§e§oRécupérer 2 bâtons de blaze","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lSortez les mouchoirs","§e§oRécupérer une crying obsidian","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lThe Walking Dead","§e§oTuer 29 zombies","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lSOS Fantômes","§e§oRécupérer une membrane de phantom","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lJe veux tes yeux","§e§oRécupérer 3 ender pearls","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lChâteau rouge","§e§oPosséder 17 briques du nether rouges","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lOld Town Road","§e§oRécupérer une selle","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lTerre colorée","§e§oRécupérer 10 terracotta de couleurs différentes","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lBob l'éponge cubique","§e§oTrouver une éponge","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lMayo l'abeille","§e§oPosséder un block de miel","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lSous l'océan","§e§oAttraper un poisson tropical avec un seau","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lAu fond des profondeurs","§e§oMiner un ancient debris","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lEn suivant les yeux...","§e§oTrouver le stronghold","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lAssurance vie","§e§oRécupérer un totem d'immortalité","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lBienvenue au pays des Schtroumpfs","§e§oTrouver un biome champignon","extreme")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lQui dit mieux?","§e§oPosséder un minerai de netherite","extreme")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lC'est la fin?","§e§oAller dans l'end","extreme")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lLa plus grosse racaille","§e§oSe faire cracher dessus par un lama","easy")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lMon précieux","§e§oMiner 16 blocks d'améthyste","medium")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lUn bout de Cerbère","§e§oRécupérer 1 tête de wither squelette","hard")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lDes paillettes dans ma vie Kévin","§e§oTuer 3 glow squids","easy")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lAxo-loto","§e§oCapturer dans des seaux les 4 espèces d'axolotl (sauf la bleue)","medium")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lBoules scintillantes","§e§oRécupérer 5 glow berries","medium")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lLes mystérieuses cités d'or","§e§oTrouver un bastion","hard")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lCa pique...","§e§oRécupérer 20 pointed dripstone","easy")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lDans le mille","§e§oTirer une flèche dans le centre d'un target block à plus de 30 blocks","easy")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lCoup de foudre","§e§oSe faire frapper par la foudre","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lPirate des Caraïbes","§e§oFaire monter et descendre un perroquet de son épaule","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lManoir hanté","§e§oTrouver un manoir","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lPoséidon","§e§oPosséder un trident","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lNous sommes en guerre","§e§oDémarrer un raid dans un village","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lLibérée, Délivrée","§e§oPoser puis casser une chaîne dans un biome Ice Spikes","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lCombat d'anthologie","§e§oTirer une boule de neige sur un bonhomme de neige","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lT'es pas net Baptiste?","§e§oAllumer une bougie","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lJésus des neiges","§e§oMarcher sur de la poudreuse avec des bottes en cuir","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lChargé à bloc","§e§oAlimenter un respawn anchor au maximum","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lUne affaire en or","§e§oEchanger avec un piglin","medium"))); //
		defi.add(new ArrayList<String>(Arrays.asList("§d§lPlutôt Krokmou ou Spyro?","§e§oPorter une tête de dragon","extreme")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lSéance jacuzzi","§e§oS'éteindre dans le nether avec un chaudron","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lAux armes citoyens","§e§oAppliquer sur un bouclier une bannière de la France (partir d'une bannière blanche)","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lRetour à l'envoyeur","§e§oTuer un ghast en lui renvoyant son projectile","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lHallucinogènes","§e§oPosséder les 4 types de champignons","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lNouvelle énergie","§e§oPosséder un daylight sensor","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lLady Gaga","§e§oEquiper une full armure en or","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lSac à dos, sac à dos","§e§oPosséder une shulker box","extreme")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lExpérimenté","§e§oAtteindre les 30 niveaux","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lMichelangelo?","§e§oReproduire deux tortues","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lStonks Industries","§e§oFaire un échange avec un villageois","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lIl est gros le poisson","§e§oTuer un Elder Guardian","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lTricot","§e§oTondre un mouton violet","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lLe géant de fer","§e§oTuer un golem de fer","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lRecyclage","§e§oFaire de la bone meal depuis un composteur","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lBatman","§e§oUtiliser un nametag sur une chauve-souris","medium")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lRemède magique","§e§oAvoir l'effet régenération II","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lArmure étincelante","§e§oPorter une armure full diams","hard")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lJusqu'aux cieux","§e§oPosséder un beacon","extreme")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lRails de coke","§e§oPosséder 32 rails et 32 sucres","easy")));
		defi.add(new ArrayList<String>(Arrays.asList("§d§lC'est la fête de trop","§e§oTuer un cochon avec un feu d'artifice et une arbalète","easy")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lDrôle de porte bonheur","§e§oRécupérer 1 patte de lapin","medium")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lTéma la taille du rat","§e§oTuer 1 silverfish","hard")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lRéparation express !","§e§oPosséder un livre mending","medium")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lCookie Monster","§e§oManger un cookie","medium")));		
		defi.add(new ArrayList<String>(Arrays.asList("§d§lFishing Planet","§e§oPosséder les 4 types de poissons crus","medium")));		
		defi.add(new ArrayList<String>(Arrays.asList("§d§lDuel de regard","§e§oRegarder un enderman dans les yeux","easy")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lMonster Hunter","§e§oCasser un spawner","medium")));	
		defi.add(new ArrayList<String>(Arrays.asList("§d§lDoctor Strange","§e§oGuérir un zombie villageois","hard")));	
		
		for (List<String> def : defi) {
			if (def.get(2) == "easy") {
				nbreEasy++;
			} else if (def.get(2) == "medium") {
				nbreMedium++;
			} else if (def.get(2) == "hard") {
				nbreHard++;
			} else {
				nbreExtreme++;
			}
		}
		
		grilleDisplay = new Hashtable<>();
		grilleDisplay.put(defi.get(0), new ItemStack(Material.SKELETON_SKULL));
		grilleDisplay.put(defi.get(1), new ItemStack(Material.FIRE_CHARGE));
		grilleDisplay.put(defi.get(2), new ItemStack(Material.IRON_AXE));
		grilleDisplay.put(defi.get(3), new ItemStack(Material.TNT));
		grilleDisplay.put(defi.get(4), new ItemStack(Material.CAKE));
		grilleDisplay.put(defi.get(5), new ItemStack(Material.ANVIL));
		grilleDisplay.put(defi.get(6), new ItemStack(Material.BOOK));
		grilleDisplay.put(defi.get(7), new ItemStack(Material.LADDER));
		grilleDisplay.put(defi.get(8), new ItemStack(Material.REDSTONE_BLOCK));
		grilleDisplay.put(defi.get(9), new ItemStack(Material.DRIED_KELP_BLOCK));
		ItemStack harming = new ItemStack(Material.POTION);
		PotionMeta harmingMeta = (PotionMeta)harming.getItemMeta();
		harmingMeta.addCustomEffect(new PotionEffect(PotionEffectType.HARM,40,2),true);
		harming.setItemMeta(harmingMeta);
		grilleDisplay.put(defi.get(10), harming); //harming
		grilleDisplay.put(defi.get(11), new ItemStack(Material.JACK_O_LANTERN));
		grilleDisplay.put(defi.get(12), new ItemStack(Material.HONEY_BOTTLE));
		grilleDisplay.put(defi.get(13), new ItemStack(Material.GOLDEN_APPLE));
		grilleDisplay.put(defi.get(14), new ItemStack(Material.POISONOUS_POTATO));
		grilleDisplay.put(defi.get(15), new ItemStack(Material.HAY_BLOCK));
		grilleDisplay.put(defi.get(16), new ItemStack(Material.SUSPICIOUS_STEW));
		grilleDisplay.put(defi.get(17), new ItemStack(Material.SLIME_BLOCK));
		ItemStack dolphin = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU5Njg4Yjk1MGQ4ODBiNTViN2FhMmNmY2Q3NmU1YTBmYTk0YWFjNmQxNmY3OGU4MzNmNzQ0M2VhMjlmZWQzIn19fQ==");
		grilleDisplay.put(defi.get(18), dolphin);
		grilleDisplay.put(defi.get(19), new ItemStack(Material.FEATHER));
		grilleDisplay.put(defi.get(20), new ItemStack(Material.BUCKET));
		grilleDisplay.put(defi.get(21), new ItemStack(Material.BONE));
		grilleDisplay.put(defi.get(22), new ItemStack(Material.COD));
		ItemStack fox = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDg5NTRhNDJlNjllMDg4MWFlNmQyNGQ0MjgxNDU5YzE0NGEwZDVhOTY4YWVkMzVkNmQzZDczYTNjNjVkMjZhIn19fQ==");
		grilleDisplay.put(defi.get(23), fox);
		ItemStack horse = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGM0OGZkYTYyZWMwZDA4MzVlMTIzZDA4MmI1Yzk2MDRkZGIyZjE4MjI0ZjExNTQ5NDllYzdhYWNhMzQ1Mjc2OCJ9fX0=");
		grilleDisplay.put(defi.get(24), horse);
		grilleDisplay.put(defi.get(25), new ItemStack(Material.RED_BED));
		grilleDisplay.put(defi.get(26), new ItemStack(Material.PORKCHOP));
		grilleDisplay.put(defi.get(27), new ItemStack(Material.PUMPKIN_PIE));
		ItemStack strider = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMThhOWFkZjc4MGVjN2RkNDYyNWM5YzA3NzkwNTJlNmExNWE0NTE4NjY2MjM1MTFlNGM4MmU5NjU1NzE0YjNjMSJ9fX0=");
		grilleDisplay.put(defi.get(28), strider);
		grilleDisplay.put(defi.get(29), new ItemStack(Material.BAMBOO));
		grilleDisplay.put(defi.get(30), new ItemStack(Material.DIAMOND_BLOCK));
		grilleDisplay.put(defi.get(31), new ItemStack(Material.BREAD));
		grilleDisplay.put(defi.get(32), new ItemStack(Material.OBSIDIAN));
		ItemStack cavespider = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U4NTUzODBiNTQyZGQyMjI2NTk5NzhhOTY3Nzc2NGY5NTM3ZTc5YTZjNTA1MDU4MTEyMDFiODU3MDRlYjcwNSJ9fX0=");
		grilleDisplay.put(defi.get(33), cavespider);
		grilleDisplay.put(defi.get(34), new ItemStack(Material.CRIMSON_HYPHAE));
		grilleDisplay.put(defi.get(35), new ItemStack(Material.ENDER_CHEST));
		grilleDisplay.put(defi.get(36), new ItemStack(Material.HEART_OF_THE_SEA));
		grilleDisplay.put(defi.get(37), new ItemStack(Material.VINE));
		grilleDisplay.put(defi.get(38), new ItemStack(Material.ENCHANTING_TABLE));
		grilleDisplay.put(defi.get(39), new ItemStack(Material.BLAZE_ROD));
		grilleDisplay.put(defi.get(40), new ItemStack(Material.CRYING_OBSIDIAN));
		grilleDisplay.put(defi.get(41), new ItemStack(Material.ZOMBIE_HEAD));
		grilleDisplay.put(defi.get(42), new ItemStack(Material.PHANTOM_MEMBRANE));
		grilleDisplay.put(defi.get(43), new ItemStack(Material.ENDER_PEARL));
		grilleDisplay.put(defi.get(44), new ItemStack(Material.RED_NETHER_BRICKS));
		grilleDisplay.put(defi.get(45), new ItemStack(Material.SADDLE));
		grilleDisplay.put(defi.get(46), new ItemStack(Material.WHITE_TERRACOTTA));
		grilleDisplay.put(defi.get(47), new ItemStack(Material.SPONGE));
		grilleDisplay.put(defi.get(48), new ItemStack(Material.HONEY_BLOCK));
		grilleDisplay.put(defi.get(49), new ItemStack(Material.TROPICAL_FISH_BUCKET));
		grilleDisplay.put(defi.get(50), new ItemStack(Material.ANCIENT_DEBRIS));
		grilleDisplay.put(defi.get(51), new ItemStack(Material.MOSSY_STONE_BRICKS));
		grilleDisplay.put(defi.get(52), new ItemStack(Material.TOTEM_OF_UNDYING));
		grilleDisplay.put(defi.get(53), new ItemStack(Material.RED_MUSHROOM_BLOCK));
		grilleDisplay.put(defi.get(54), new ItemStack(Material.NETHERITE_INGOT));
		grilleDisplay.put(defi.get(55), new ItemStack(Material.END_PORTAL_FRAME));
		ItemStack llama = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFlMjVkZGMyZDI1MzljNTY1ZGZmMmFhNTAwNjAzM2YxNGNjMDYzNzlmZTI4YjA3MzFjN2JkYzY1YmEwZTAxNiJ9fX0=");
		grilleDisplay.put(defi.get(56), llama);
		grilleDisplay.put(defi.get(57), new ItemStack(Material.AMETHYST_BLOCK));
		grilleDisplay.put(defi.get(58), new ItemStack(Material.WITHER_SKELETON_SKULL));
		grilleDisplay.put(defi.get(59), new ItemStack(Material.GLOW_INK_SAC));
		grilleDisplay.put(defi.get(60), new ItemStack(Material.AXOLOTL_BUCKET));
		grilleDisplay.put(defi.get(61), new ItemStack(Material.GLOW_BERRIES));
		grilleDisplay.put(defi.get(62), new ItemStack(Material.GILDED_BLACKSTONE));
		grilleDisplay.put(defi.get(63), new ItemStack(Material.POINTED_DRIPSTONE));
		grilleDisplay.put(defi.get(64), new ItemStack(Material.TARGET));
		grilleDisplay.put(defi.get(65), new ItemStack(Material.LIGHTNING_ROD));
		ItemStack parrot = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM2NDcxZjIzNTQ3YjJkYmRmNjAzNDdlYTEyOGY4ZWIyYmFhNmE3OWIwNDAxNzI0ZjIzYmQ0ZTI1NjRhMmI2MSJ9fX0=");
		grilleDisplay.put(defi.get(66), parrot);
		grilleDisplay.put(defi.get(67), new ItemStack(Material.DARK_OAK_PLANKS));
		grilleDisplay.put(defi.get(68), new ItemStack(Material.TRIDENT));
		ItemStack pillager = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmUzOWI0NmY4MWZkNmUxMjYxZTE0ODU4OGNkOWEzOWYxZjA5OWQzMDA1YTExODljN2JmN2IzZjc4MGNkMGU3NyJ9fX0=");
		grilleDisplay.put(defi.get(69), pillager);
		grilleDisplay.put(defi.get(70), new ItemStack(Material.CHAIN));
		grilleDisplay.put(defi.get(71), new ItemStack(Material.SNOWBALL));
		grilleDisplay.put(defi.get(72), new ItemStack(Material.CANDLE));
		grilleDisplay.put(defi.get(73), new ItemStack(Material.LEATHER_BOOTS));
		grilleDisplay.put(defi.get(74), new ItemStack(Material.RESPAWN_ANCHOR));
		ItemStack piglin = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBiYzlkYmI0NDA0YjgwMGY4Y2YwMjU2MjIwZmY3NGIwYjcxZGJhOGI2NjYwMGI2NzM0ZjRkNjMzNjE2MThmNSJ9fX0=");
		grilleDisplay.put(defi.get(75), piglin);
		grilleDisplay.put(defi.get(76), new ItemStack(Material.DRAGON_HEAD));
		grilleDisplay.put(defi.get(77), new ItemStack(Material.CAULDRON));
		grilleDisplay.put(defi.get(78), new ItemStack(Material.SHIELD));
		ItemStack ghast = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGI2YTcyMTM4ZDY5ZmJiZDJmZWEzZmEyNTFjYWJkODcxNTJlNGYxYzk3ZTVmOTg2YmY2ODU1NzFkYjNjYzAifX19");
		grilleDisplay.put(defi.get(79), ghast);
		grilleDisplay.put(defi.get(80), new ItemStack(Material.BROWN_MUSHROOM));
		grilleDisplay.put(defi.get(81), new ItemStack(Material.DAYLIGHT_DETECTOR));
		grilleDisplay.put(defi.get(82), new ItemStack(Material.GOLDEN_HELMET));
		grilleDisplay.put(defi.get(83), new ItemStack(Material.SHULKER_BOX));
		grilleDisplay.put(defi.get(84), new ItemStack(Material.EXPERIENCE_BOTTLE));
		grilleDisplay.put(defi.get(85), new ItemStack(Material.TURTLE_EGG));
		grilleDisplay.put(defi.get(86), new ItemStack(Material.EMERALD));
		ItemStack elderGuardian = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWM3OTc0ODJhMTRiZmNiODc3MjU3Y2IyY2ZmMWI2ZTZhOGI4NDEzMzM2ZmZiNGMyOWE2MTM5Mjc4YjQzNmIifX19");
		grilleDisplay.put(defi.get(87), elderGuardian);
		grilleDisplay.put(defi.get(88), new ItemStack(Material.PURPLE_WOOL));
		ItemStack ironGolem = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODkwOTFkNzllYTBmNTllZjdlZjk0ZDdiYmE2ZTVmMTdmMmY3ZDQ1NzJjNDRmOTBmNzZjNDgxOWE3MTQifX19");
		grilleDisplay.put(defi.get(89), ironGolem);
		grilleDisplay.put(defi.get(90), new ItemStack(Material.COMPOSTER));
		grilleDisplay.put(defi.get(91), new ItemStack(Material.NAME_TAG));
		ItemStack regen = new ItemStack(Material.POTION);
		PotionMeta regenMeta = (PotionMeta)regen.getItemMeta();
		regenMeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION,40,1),true);
		regen.setItemMeta(regenMeta);
		grilleDisplay.put(defi.get(92), regen); // regen 
		grilleDisplay.put(defi.get(93), new ItemStack(Material.DIAMOND_CHESTPLATE));
		grilleDisplay.put(defi.get(94), new ItemStack(Material.BEACON));
		grilleDisplay.put(defi.get(95), new ItemStack(Material.RAIL));
		grilleDisplay.put(defi.get(96), new ItemStack(Material.FIREWORK_ROCKET));
		grilleDisplay.put(defi.get(97), new ItemStack(Material.RABBIT_FOOT));
		ItemStack silverfish = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGE5MWRhYjgzOTFhZjVmZGE1NGFjZDJjMGIxOGZiZDgxOWI4NjVlMWE4ZjFkNjIzODEzZmE3NjFlOTI0NTQwIn19fQ==");
		grilleDisplay.put(defi.get(98), silverfish);
		grilleDisplay.put(defi.get(99), new ItemStack(Material.ENCHANTED_BOOK));
		grilleDisplay.put(defi.get(100), new ItemStack(Material.COOKIE));
		grilleDisplay.put(defi.get(101), new ItemStack(Material.SALMON));
		ItemStack enderman = skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E1OWJiMGE3YTMyOTY1YjNkOTBkOGVhZmE4OTlkMTgzNWY0MjQ1MDllYWRkNGU2YjcwOWFkYTUwYjljZiJ9fX0=");
		grilleDisplay.put(defi.get(102), enderman);
		grilleDisplay.put(defi.get(103), new ItemStack(Material.SPAWNER));
		grilleDisplay.put(defi.get(104), new ItemStack(Material.ROTTEN_FLESH));
		melangeDefis();
	}
	
	public void melangeDefis() {
		Collections.shuffle(defi);
	}
}
