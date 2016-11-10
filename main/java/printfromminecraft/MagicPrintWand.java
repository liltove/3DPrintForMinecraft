package printfromminecraft;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MagicPrintWand extends Item {
    
    static List<Integer> pos1 = new ArrayList();
    static List<Integer> pos2 = new ArrayList();
    private final String name = "magicprintwand";
    
    public MagicPrintWand(){
        setRegistryName(name);
        setCreativeTab(CreativeTabs.TOOLS);
        GameRegistry.register(this);
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote){
            BlockPos b = new BlockPos(getBlockCoords(world, player));
            if (pos1.isEmpty()){
                pos1.add(b.getX());
                pos1.add(b.getY());
                pos1.add(b.getZ());

                player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 1 set to " + player.posX + ", " + player.posY + ", " + player.posZ + "."));
            } else if (!pos1.isEmpty() && pos2.isEmpty()){
                pos2.add(b.getX());
                pos2.add(b.getY());
                pos2.add(b.getZ());

                player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 2 set to " + player.posX + ", " + player.posY + ", " + player.posZ + "."));
            } else {
                pos1.clear();
                pos2.clear();

                pos1.add(b.getX());
                pos1.add(b.getY());
                pos1.add(b.getZ());
        
                player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Position 1 set to " + player.posX + ", " + player.posY + ", " + player.posZ + "."));
            }
            
        }
        
        
        

        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }
    
    public String getName() {
        return name;
    }
    
    public BlockPos getBlockCoords(World world, EntityPlayer player){
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        
        BlockPos block = new BlockPos((int)x, (int)y, (int)z);
        
        player.addChatMessage(new TextComponentString(world.getBlockState(block).getBlock().getLocalizedName()));
        return block; 
    }
}