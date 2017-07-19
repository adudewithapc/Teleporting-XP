package thatmartinguy.teleportingxp.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thatmartinguy.teleportingxp.util.Reference;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("teleportingxp.config.title")
public class ModConfig
{
    @Config.Comment("Should XP teleporting prioritize non-creative players?")
    public static boolean prioritizeNonCreative = true;

    @Config.Comment("Should XP orbs teleport even if teleportXP is false?")
    public static boolean ignoreGamerule = true;

    @Mod.EventBusSubscriber
    private static class EventHandler
    {
        @SubscribeEvent
        public static void configChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if(event.getModID().equals(Reference.MOD_ID))
            {
                ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
