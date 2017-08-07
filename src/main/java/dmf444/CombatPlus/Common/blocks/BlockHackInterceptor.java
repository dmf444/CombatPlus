package dmf444.CombatPlus.Common.blocks;

import dmf444.CombatPlus.Common.TileEntity.TileInterception;
import dmf444.CombatPlus.init.ItemRegistry;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BlockHackInterceptor extends BasicBlock {

    public static final PropertyBool EXPLOSIVE = PropertyBool.create("explosive");
    public static final PropertyBool INTERCEPTION = PropertyBool.create("interception");

    public BlockHackInterceptor() {
        super(Material.CIRCUITS);
        this.setName("interceptor");
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.blockHardness = 2.0F;
        this.setDefaultState(this.blockState.getBaseState().withProperty(EXPLOSIVE, false).withProperty(INTERCEPTION, false));
    }

    @Override
    public TileEntity createNewTileEntity(World wolrd, int meta) {
        return new TileInterception();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
        if(player.getHeldItem(hand) != null) {
            boolean isIntercepting = state.getValue(BlockHackInterceptor.INTERCEPTION);
            boolean isExplosive = state.getValue(BlockHackInterceptor.EXPLOSIVE);
            if (player.getHeldItem(hand).getItem().equals(ItemRegistry.UPGRADE_CARD) && !isIntercepting && !isExplosive) {
                world.setBlockState(pos, state.withProperty(INTERCEPTION, true));
                world.notifyBlockUpdate(pos, state, state.withProperty(INTERCEPTION, true), 3);
                return true;
            } else if (player.getHeldItem(hand).getItem().equals(ItemRegistry.EXPLOSIVE_CARD) && isIntercepting && !isExplosive) {
                world.setBlockState(pos, state.withProperty(EXPLOSIVE, true));
                world.notifyBlockUpdate(pos, state, state.withProperty(EXPLOSIVE, true), 3);
                return true;
            }
        }
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, EXPLOSIVE, INTERCEPTION, PROPERTY_FACING);
    }

    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        if(state.getValue(EXPLOSIVE)){
            i = 1;
        }
        if(state.getValue(INTERCEPTION)){
            i += 2;
        }
        return i;
    }

    public IBlockState getStateFromMeta(int meta)
    {
        switch (meta){
            case 1:
                return this.getDefaultState().withProperty(EXPLOSIVE, true).withProperty(INTERCEPTION, false);
            case 2:
                return this.getDefaultState().withProperty(EXPLOSIVE, false).withProperty(INTERCEPTION, true);
            case 3:
                return this.getDefaultState().withProperty(EXPLOSIVE, true).withProperty(INTERCEPTION, true);
            default:
                return this.getDefaultState().withProperty(EXPLOSIVE, false).withProperty(INTERCEPTION, false);
        }
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState s) {
        return EnumBlockRenderType.MODEL;
    }

    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileInterception interceptor = (TileInterception) world.getTileEntity(pos);
        interceptor.setOwner(placer.getName());
    }
}
