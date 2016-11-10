package printfromminecraft;

//import java.util.ArrayList;
//import java.util.List;
import net.minecraft.init.Blocks;
//import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.RenderItem;
//import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
//import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraftforge.client.model.ModelLoader;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = PrintFromMinecraft.MODID, version = PrintFromMinecraft.VERSION)
public class PrintFromMinecraft {
    public static final String MODID = "printfromminecraft";
    public static final String VERSION = "1.0";
    
    public static MagicPrintWand magicPrintWand;
    
    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        //register the wand texture and model
        magicPrintWand = new MagicPrintWand();
    }
    
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        magicPrintWand.initModel();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        //add the recipe to create a magicPrintWand
        GameRegistry.addRecipe(new ItemStack(magicPrintWand),
            "XXX",
            "XXX",
            "XXX",
            'X', Blocks.DIRT
        );
    }
    
    
    
    @EventHandler
    public void init(FMLPostInitializationEvent event) {
        
        
    }
    
    @EventHandler
    public void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new WriteToSTL());
    }
    
    
}