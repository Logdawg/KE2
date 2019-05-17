package mod.ke2.init;

import mod.ke2.KAGIC;
import mod.ke2.items.ItemGem;
import mod.ke2.items.ItemGemDestabilizer;
import mod.ke2.items.ItemGemDust;
import mod.ke2.items.ItemGemShard;
import mod.ke2.items.ItemGemStaff;
import mod.ke2.items.ItemManual;
import mod.ke2.items.ItemWarpWhistle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

public class Ke2Items {
	public static final ToolMaterial DESTABILIZER_MATERIAL = EnumHelper.addToolMaterial("DESTABILIZER_MATERIAL", 0, 24, 0.1f, 0.0f, 1);
	
	public static final ItemManual MANUAL = new ItemManual();
	public static final ItemGemStaff GEM_STAFF = new ItemGemStaff();
	public static final ItemWarpWhistle WARP_WHISTLE = new ItemWarpWhistle();
	public static final ItemGemDestabilizer GEM_DESTABILIZER = new ItemGemDestabilizer();
	public static final ItemGemShard GEM_SHARD = new ItemGemShard();
	public static final ItemGemDust GEM_DUST = new ItemGemDust();
	
	
	public static void register(RegistryEvent.Register<Item> event) {
		registerItem(MANUAL, new ResourceLocation("ke2:manual"), event);
		registerItem(GEM_STAFF, new ResourceLocation("ke2:gem_staff"), event);
		registerItem(WARP_WHISTLE, new ResourceLocation("ke2:warp_whistle"), event);
		registerItem(GEM_DESTABILIZER, new ResourceLocation("ke2:gem_destabilizer"), event);
		registerItem(GEM_SHARD, new ResourceLocation("ke2:gem_shard"), event);
		registerItem(GEM_DUST, new ResourceLocation("ke2:gem_dust"), event);
		
		for (Item item : Ke2Blocks.ITEMS) {
			registerItem(item, null, event);
		}
	}
	public static void registerGemItem(ItemGem normal, ItemGem cracked, ResourceLocation name, RegistryEvent.Register<Item> event) {
		if (!Ke2Gems.NORMAL_TO_CRACKED.containsKey(normal)) {
			Ke2Items.registerItem(cracked, new ResourceLocation(name.getResourcePath(), "cracked_" + name.getResourceDomain()), event, Ke2CreativeTabs.GEMSTONES);
			Ke2Items.registerItem(normal, name, event, Ke2CreativeTabs.GEMSTONES);
			Ke2Gems.NORMAL_TO_CRACKED.put(normal, cracked);
			Ke2Gems.CRACKED_TO_NORMAL.put(cracked, normal);
		}
		else {
			KAGIC.LOGGER.warn("Gem '%s' already exists! Skipping!", name);
			KAGIC.LOGGER.warn("Report this to addon or mod author!");
		}
	}
	public static void registerItem(Item item, ResourceLocation name, RegistryEvent.Register<Item> event, CreativeTabs tab) {
		event.getRegistry().register(item.getRegistryName() == null ? item.setCreativeTab(tab).setRegistryName(name) : item.setCreativeTab(tab));
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
	public static void registerItem(Item item, ResourceLocation name, RegistryEvent.Register<Item> event) {
		registerItem(item, name, event, Ke2CreativeTabs.GEM_TECH);
	}
}