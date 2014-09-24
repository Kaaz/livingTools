package novaz.mod.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import novaz.mod.PassiveEnchanting;
import novaz.mod.references.Names;

import java.util.List;
import java.util.Set;

/**
 * Created by kaaz on 22-9-2014.
 */
public class ItemLivingPickaxe extends PEItemTool {
	private static final Set worksAgainst = Sets.newHashSet(new Block[]{Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});
	private final int XP_PER_LEVEL = 5;
	private final int MAX_LEVEL = 100;
	public final String[] stats = {"speed","durability","damage","mininglevel","fortune"};

	public String[] getSpecialStats(){
		return stats;
	}

	public ItemLivingPickaxe() {
		super(1f, ToolMaterial.WOOD, worksAgainst);
		setUnlocalizedName(Names.Items.LIVING_PICKAXE);
		setNoRepair();
		setMaxDamage(10);
	}

	public boolean func_150897_b(Block p_150897_1_) {
		return p_150897_1_ == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() >= 3 : (p_150897_1_ != Blocks.diamond_block && p_150897_1_ != Blocks.diamond_ore ? (p_150897_1_ != Blocks.emerald_ore && p_150897_1_ != Blocks.emerald_block ? (p_150897_1_ != Blocks.gold_block && p_150897_1_ != Blocks.gold_ore ? (p_150897_1_ != Blocks.iron_block && p_150897_1_ != Blocks.iron_ore ? (p_150897_1_ != Blocks.lapis_block && p_150897_1_ != Blocks.lapis_ore ? (p_150897_1_ != Blocks.redstone_ore && p_150897_1_ != Blocks.lit_redstone_ore ? (p_150897_1_.getMaterial() == Material.rock ? true : (p_150897_1_.getMaterial() == Material.iron ? true : p_150897_1_.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
	}

	public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
		return p_150893_2_.getMaterial() != Material.iron && p_150893_2_.getMaterial() != Material.anvil && p_150893_2_.getMaterial() != Material.rock ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.efficiencyOnProperMaterial;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase player) {

		if((double)block.getBlockHardness(world, x, y, z) != 0.0D){
			if (itemStack.stackTagCompound == null) {
				initItem(itemStack,(EntityPlayer)player);
			}
			//p_150894_1_.damageItem(1, p_150894_7_);

			if (itemStack.stackTagCompound.getInteger("level") < MAX_LEVEL) {
				itemStack.stackTagCompound.setInteger("xp", itemStack.stackTagCompound.getInteger("xp") + 1);
			}

			checkLevelUp(itemStack, player);
		}
		//return super.onBlockDestroyed(p_150894_1_, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, p_150894_7_);
		return true;
	}
	public void checkLevelUp(ItemStack item, EntityLivingBase player){
		int level = item.stackTagCompound.getInteger("level");
		int xp = item.stackTagCompound.getInteger("xp");
		int xpToNext = XP_PER_LEVEL * (level + 1);
		if(xp>=xpToNext){

			item.stackTagCompound.setInteger("level", level + 1);
			item.stackTagCompound.setInteger("xp",xp-xpToNext);
			item.stackTagCompound.setInteger("points", item.stackTagCompound.getInteger("points")+1);
		}
	}
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		initItem(itemStack,player);
	}
	private void initItem(ItemStack itemStack, EntityPlayer player){
		itemStack.stackTagCompound = new NBTTagCompound();
		itemStack.stackTagCompound.setString("owner", player.getDisplayName());
		itemStack.stackTagCompound.setInteger("level", 0);
		itemStack.stackTagCompound.setInteger("xp", 0);
		itemStack.stackTagCompound.setInteger("points", 0);
		for(int i=0;i<stats.length;i++){
			itemStack.stackTagCompound.setInteger("stat_"+stats[i],0);
		}
	}
	public void addInformation(ItemStack itemStack, EntityPlayer player,
							   List list, boolean par4) {

		if (itemStack.stackTagCompound != null) {
			int level = itemStack.stackTagCompound.getInteger("level");
			int xp = itemStack.stackTagCompound.getInteger("xp");
			int points = itemStack.stackTagCompound.getInteger("points");
			int xpToNext = XP_PER_LEVEL * (level + 1);
			int progress = (int) (((float) xp / (float) xpToNext) * 100);
			String owner = itemStack.stackTagCompound.getString("owner");
			list.add("owner: " + colorfy(owner));
			list.add("level " + colorfy(level));
			list.add(String.format("Experience: %s %% [%s / %s]", colorfy(progress,EnumChatFormatting.AQUA), colorfy(xp), colorfy(xpToNext)));
			if(points>0){
				list.add(String.format("You have %s unspend point(s)!",colorfy(points)));
				list.add(""+EnumChatFormatting.ITALIC+" "+EnumChatFormatting.WHITE+" Rightclick to spend them ");
			}
		}
	}
	public String colorfy(Object o){
		return colorfy(o, EnumChatFormatting.AQUA);
	}
	public String colorfy(Object o, EnumChatFormatting col){
		return ""+col+ o + EnumChatFormatting.GRAY;
	}
	@Override
	public Set<String> getToolClasses(ItemStack itemStack) {
		return ImmutableSet.of("pickaxe");
	}

	@Override
	public float getDigSpeed(ItemStack itemStack, Block block, int meta) {
		return super.getDigSpeed(itemStack, block, meta);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World world, EntityPlayer player) {
		//rightclick spend points
		if (world.isRemote) {//client
			//PassiveEnchanting.proxy.openJournal(player);
			player.openGui(PassiveEnchanting.instance, PassiveEnchanting.GUI_ITEM_UPGRADE, world, (int) player.posX, (int) player.posY, (int) player.posZ);

		}
		return super.onItemRightClick(p_77659_1_, world, player);
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		return false;
	}

}
