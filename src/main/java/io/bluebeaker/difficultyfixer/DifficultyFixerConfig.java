package io.bluebeaker.difficultypatch;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = DifficultyPatch.MODID,type = Type.INSTANCE,category = "general")
public class DifficultyPatchConfig {
    @Comment("Example")
    @LangKey("config.difficultypatch.example.name")
    public static boolean example = true;
}