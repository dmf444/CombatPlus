package dmf444.CombatPlus.Core;


import net.minecraftforge.common.config.Configuration;

import java.io.File;


public class ConfigHandler
{
    public static int RFperT;
    public static double Multiplyer;
    public static int tickWait;

    public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        Configuration configuration = new Configuration(configFile);



        try
        {
            // Load the configuration file
            configuration.load();


            // Read in properties from configuration file
            //configValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "This is an example config value").getBoolean(true);
            RFperT = configuration.get(Configuration.CATEGORY_GENERAL, "RF per Tick", 1500, "RF produced per 1 redstone").getInt(1500);
            Multiplyer = configuration.get(Configuration.CATEGORY_GENERAL, "Multiplier", 9.5, "Amount to multiply when using a block of redstone").getDouble(9.5);
            tickWait = configuration.get(Configuration.CATEGORY_GENERAL, "Ticks to wait", 150, "Ticks needed before the next item is consumed by the heater").getInt(150);
        }
        catch (Exception e)
        {
            // Log the exception
        }
        finally
        {
            //Save the configuration file
            configuration.save();
        }
    }
}