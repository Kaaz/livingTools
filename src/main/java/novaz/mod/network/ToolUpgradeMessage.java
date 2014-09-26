package novaz.mod.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import novaz.mod.item.PEItemTool;

public class ToolUpgradeMessage implements IMessage {

	private String stat;

	public ToolUpgradeMessage() { }

	public ToolUpgradeMessage(String stat) {
		this.stat = stat;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		stat = ByteBufUtils.readUTF8String(buf); // this class is very useful in general for writing more complex objects
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, stat);
	}

	public static class Handler implements IMessageHandler<ToolUpgradeMessage, IMessage> {

		@Override
		public IMessage onMessage(ToolUpgradeMessage message, MessageContext ctx) {

			System.out.println(String.format("Received %s from %s", message.stat, ctx.getServerHandler().playerEntity.getDisplayName()));
			ItemStack equippedItem = ctx.getServerHandler().playerEntity.getCurrentEquippedItem();
			if(equippedItem.getItem() instanceof PEItemTool){
				PEItemTool pit = (PEItemTool)equippedItem.getItem();
				pit.upgradeStat(equippedItem,message.stat);
			}
			return null; // no response in this case
		}
	}
}