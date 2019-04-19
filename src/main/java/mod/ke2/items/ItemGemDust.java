package mod.ke2.items;

import java.util.ArrayList;

import mod.ke2.init.Ke2CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;

public class ItemGemDust extends Item {
	public static final ArrayList<Item> DUST_COLORS = new ArrayList<Item>();
	public int color;
	public ItemGemDust(int index) {
		String name = EnumDyeColor.byMetadata(index).toString().toLowerCase();
		this.setUnlocalizedName(name + "_gem_dust");
		this.setMaxStackSize(64);
		this.setCreativeTab(Ke2CreativeTabs.GEM_TECH);
		ItemGemDust.DUST_COLORS.add(this);
		this.color = index;
	}
}
