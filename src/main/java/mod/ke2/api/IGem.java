package mod.ke2.api;

import net.minecraft.entity.INpc;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;

public interface IGem extends INpc {
	
	int generateSkinColor();

	int generateHairColor();

	int generateOutfitColor();

	int generateVisorColor();

	int generateGemstoneColor();

	int generateGemstoneCut();

	ItemStack generateGemstoneItem();

	String generateOutfitVariant();

	String generateHairVariant();

	String generateSkinVariant();

	String generateNameVariant();
	
	SoundEvent getGemSound();
}
