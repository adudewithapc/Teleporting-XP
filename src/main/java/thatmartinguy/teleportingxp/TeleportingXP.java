package thatmartinguy.teleportingxp;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import thatmartinguy.teleportingxp.event.CommonEventHandler;
import thatmartinguy.teleportingxp.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class TeleportingXP
{
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
		
		world.getGameRules().setOrCreateGameRule("teleportXP", "false");
	}
}
