package novaz.mod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import novaz.mod.PassiveEnchanting;
import novaz.mod.item.special.StatType;
import novaz.mod.references.Names;

import java.util.HashMap;
import java.util.Set;

public abstract class PEItemTool extends ItemTool {

	protected final String statsPrefix = "stat_";
	protected int XP_PER_LEVEL = 5;
	protected int MAX_LEVEL = 100;
	protected HashMap<String, StatType> itemStats = new HashMap<String, StatType>();

	protected PEItemTool(float damage, ToolMaterial toolMaterial, Set goodAgainst) {
		super(damage, toolMaterial, goodAgainst);
		setCreativeTab(PassiveEnchanting.TAB);
		this.setNoRepair();
		itemStats.clear();
		setMaxDamage(1000);
		addItemStat("durability", "Durability", 50, 1, 0);
		addItemStat("regen", "Regeneration", 50, 1, 0);

	}

	protected void addItemStat(String name, String description, int maxLevel, int baseCost, double costPerLevel) {
		if (!itemStats.containsKey(name)) {
			itemStats.put(name, new StatType(name, description, maxLevel, baseCost, costPerLevel));
		}
	}

	public StatType[] getSpecialStats() {
		return itemStats.values().toArray(new StatType[0]);
	}

	public void upgradeStat(ItemStack itemStack, String statName) {
		if (itemStack.stackTagCompound != null && itemStats.containsKey(statName)) {
			StatType upgradeStat = itemStats.get(statName);
			float oldLevel = getItemStat(itemStack,statName);
			int points = getItemStat(itemStack, "points");
			int cost = upgradeStat.baseCost +(int)(upgradeStat.costPerLevel*oldLevel);

			if (points >= cost && oldLevel < upgradeStat.maxLevel ) {
				addToItemStat(itemStack,statName,1);
				itemStack.stackTagCompound.setInteger("points", points - cost);
				System.out.println("upgraded " + statName);
			}
		}
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		initItem(itemStack);
	}

	protected void initItem(ItemStack itemStack) {
		if (itemStack.stackTagCompound == null) {
			itemStack.stackTagCompound = new NBTTagCompound();
			itemStack.stackTagCompound.setInteger("level", 0);
			itemStack.stackTagCompound.setInteger("xp", 0);
			itemStack.stackTagCompound.setInteger("points", 0);
			for (StatType st : itemStats.values()) {
				itemStack.stackTagCompound.setInteger(statsPrefix + st.name, 0);
			}
		}
	}

	protected void addToItemStat(ItemStack item, String statName, int toAdd) {
		initItem(item);
		item.stackTagCompound.setInteger(statsPrefix + statName, item.stackTagCompound.getInteger(statsPrefix + statName) + toAdd);
	}

	protected void setItemStat(ItemStack item, String statName, int value) {
		initItem(item);
		item.stackTagCompound.setInteger(statsPrefix + statName, value);
	}

	protected int getItemStat(ItemStack item, String statName) {
		initItem(item);
		return item.stackTagCompound.getInteger(statsPrefix + statName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", Names.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
