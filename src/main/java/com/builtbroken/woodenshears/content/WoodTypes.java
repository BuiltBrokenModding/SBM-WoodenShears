package com.builtbroken.woodenshears.content;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 9/20/2016.
 */
public enum WoodTypes
{
    OAK("oak", true),
    ACACIA("acacia", true),
    BIRCH("birch", true),
    JUNGLE("jungle", true),
    SPRUCE("spruce", true),
    BIG_OAK("big_oak", true),
    CHARRED("charred", true);

    /** Name to use for textures and unlocalizations */
    public final String name;
    /** Is the item enabled, normally only used when adding modding materials */
    public boolean enabled;

    @SideOnly(Side.CLIENT)
    public IIcon icon;

    WoodTypes(String name, boolean enabled)
    {
        this.name = name;
        this.enabled = enabled;
    }
}
