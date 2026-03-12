package name.modid.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.ai.village.ReputationEventType;

import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public class VillagerMixin {
    @WrapMethod(method = "onReputationEventFrom")
    public void onReputationEventFrom(ReputationEventType reputationEventType, Entity entity, Operation<Void> original) {
        System.out.print("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (reputationEventType == ReputationEventType.ZOMBIE_VILLAGER_CURED) {
           ((Villager) (Object) this).getGossips().add(entity.getUUID(), GossipType.MAJOR_POSITIVE, 20);
           ((Villager) (Object) this).getGossips().add(entity.getUUID(), GossipType.MINOR_POSITIVE, 25);
        } else if (reputationEventType == ReputationEventType.TRADE) {
            ((Villager) (Object) this).getGossips().add(entity.getUUID(), GossipType.TRADING, 2);
        } else if (reputationEventType == ReputationEventType.VILLAGER_HURT) {
            ((Villager) (Object) this).getGossips().add(entity.getUUID(), GossipType.MINOR_POSITIVE, 25);
        } else if (reputationEventType == ReputationEventType.VILLAGER_KILLED) {
            ((Villager) (Object) this).getGossips().add(entity.getUUID(), GossipType.MAJOR_POSITIVE, 25);
        }
    }

    @WrapMethod(method = "getPlayerReputation")
    public int getPlayerReputation(Player player, Operation<Integer> original) {
        return ((Villager) (Object) this).getGossips().getReputation(player.getUUID(), (gossipType) -> true);
    }
}
