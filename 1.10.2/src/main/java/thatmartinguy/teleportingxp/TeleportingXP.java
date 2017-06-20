package thatmartinguy.teleportingxp;

import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import thatmartinguy.teleportingxp.event.CommonEventHandler;
import thatmartinguy.teleportingxp.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class TeleportingXP
{
	public static boolean ignoreGamerule;
	public static boolean prioritizeNonCreative;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();

		ignoreGamerule = config.getBoolean("ignoreGamerule", Configuration.CATEGORY_GENERAL, false,"Should XP orbs teleport regardless of the gamerule value?");
		prioritizeNonCreative = config.getBoolean("prioritizeNonCreative", Configuration.CATEGORY_GENERAL, true, "Should XP orbs prioritize teleporting to non-creative players?");

		config.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
	}
	
	@Instance
	public TeleportingXP instance;
	
	@EventHandler
	public void serverStarting(FMLServerStartedEvent event)
	{
		World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
		
		world.getGameRules().setOrCreateGameRule("teleportXP", "false");
	}
}
