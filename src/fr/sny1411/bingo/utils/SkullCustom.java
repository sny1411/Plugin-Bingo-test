package fr.sny1411.bingo.utils;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class SkullCustom {
	
	public ItemStack getCustomSkull(String url) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
	    SkullMeta headMeta = (SkullMeta) head.getItemMeta();
	    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	    profile.getProperties().put("textures", new Property("textures", url));
	    Field profileField;
	    try {
	        profileField = headMeta.getClass().getDeclaredField("profile");
	        profileField.setAccessible(true);
	        profileField.set(headMeta, profile);
	    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ignored) {
	    	ignored.printStackTrace();
	    }
	    head.setItemMeta(headMeta);
	    return head;
	}
}
