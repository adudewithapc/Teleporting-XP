package thatmartinguy.teleportingxp.event;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.dto.RealmsServer.WorldType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class CommonEventHandler
{
	@SubscribeEvent
	public void worldTicked(WorldTickEvent event)
	{
		if(event.phase == TickEvent.Phase.START && event.type == TickEvent.Type.WORLD)
		{
			if(event.world.getGameRules().getBoolean("teleportXP"))
			{
				for(int i = 0; i < event.world.loadedEntityList.size(); i++)
				{
					if(event.world.loadedEntityList.get(i) instanceof EntityXPOrb)
					{
						EntityXPOrb xpOrb = (EntityXPOrb) event.world.loadedEntityList.get(i);
						EntityPlayer closestPlayer = event.world.getNearestPlayerNotCreative(xpOrb, Double.MAX_VALUE);
						if(closestPlayer != null)
						{
							xpOrb.setPosition(closestPlayer.posX, closestPlayer.posY, closestPlayer.posZ);
						}
						else
						{
							closestPlayer = event.world.getClosestPlayerToEntity(xpOrb, Double.MAX_VALUE);
							if(closestPlayer != null)
							{
								xpOrb.setPosition(closestPlayer.posX, closestPlayer.posY, closestPlayer.posZ);
							}
						}
					}
				}
			}
		}
	}
}