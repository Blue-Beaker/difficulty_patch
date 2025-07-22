package io.bluebeaker.difficultypatch.mixin;

import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.bluebeaker.difficultypatch.DifficultyPatch;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.EnumDifficulty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.net.Proxy;

@Mixin(IntegratedServer.class)
public abstract class MixinIntegratedServer extends MinecraftServer {
    public MixinIntegratedServer(File anvilFileIn, Proxy proxyIn, DataFixer dataFixerIn, YggdrasilAuthenticationService authServiceIn, MinecraftSessionService sessionServiceIn, GameProfileRepository profileRepoIn, PlayerProfileCache profileCacheIn) {
        super(anvilFileIn, proxyIn, dataFixerIn, authServiceIn, sessionServiceIn, profileRepoIn, profileCacheIn);
    }
    @Final
    @Shadow
    private Minecraft mc;

    @Inject(method = "getDifficulty",at = @At(value= "HEAD"),cancellable = true)
    public void getDifficulty(CallbackInfoReturnable<EnumDifficulty> cir){
        if (this.mc.world == null && this.worlds.length>0){
            EnumDifficulty difficulty = this.worlds[0].getDifficulty();
            cir.setReturnValue(difficulty);
            DifficultyPatch.getLogger().info("Get difficulty: {} from world, client settings has {}", difficulty,this.mc.gameSettings.difficulty);
        }
    }
}
