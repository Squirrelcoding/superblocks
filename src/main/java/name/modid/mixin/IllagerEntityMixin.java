package name.modid.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.monster.illager.AbstractIllager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractIllager.class)
public class IllagerEntityMixin {
    @Inject(method = "canAttack", at=@At("HEAD"), cancellable = true)
    public void canAttack(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
        if (livingEntity instanceof AbstractVillager && livingEntity.isBaby())
            cir.setReturnValue(true);
    }
}
