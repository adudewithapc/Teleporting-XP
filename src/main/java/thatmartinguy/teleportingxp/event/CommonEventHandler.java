package thatmartinguy.teleportingxp.event;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import thatmartinguy.teleportingxp.config.ModConfig;

public class CommonEventHandler
{
	@SubscribeEvent
	public void worldTicked(WorldTickEvent event)
	{
		if(event.phase == TickEvent.Phase.START && event.type == TickEvent.Type.WORLD)
		{
			if(event.world.getGameRules().getBoolean("teleportXP") || ModConfig.ignoreGamerule)
			{
				for(int i = 0; i < event.world.loadedEntityList.size(); i++)
				{
					if(event.world.loadedEntityList.get(i) instanceof EntityXPOrb)
					{
						EntityXPOrb xpOrb = (EntityXPOrb) event.world.loadedEntityList.get(i);
						EntityPlayer player;
						if (ModConfig.prioritizeNonCreative)
						{
							player = event.world.getNearestPlayerNotCreative(xpOrb, Double.MAX_VALUE);
							if(player != null)
							{
								xpOrb.setPosition(player.posX, player.posY, player.posZ);
								return;
							}
						}
						player = event.world.getClosestPlayerToEntity(xpOrb, Double.MAX_VALUE);
						if(player != null)
						{
							xpOrb.setPosition(player.posX, player.posY, player.posZ);
						}
					}
				}
			}
		}
	}
}