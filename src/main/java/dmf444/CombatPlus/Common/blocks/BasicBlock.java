package dmf444.CombatPlus.Common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BasicBlock extends Block implements ITileEntityProvider {
    public static final PropertyDirection PROPERTY_FACING = PropertyDirection.create("facing");

    public BasicBlock(Material materialIn) {
        super(materialIn);
    }

    public void setName(String name){
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState s) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public boolean isOpaqueCube(IBlockState s) {
        return false;
    }

    boolean canRotateVertical(){return false;}

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {PROPERTY_FACING});
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(PROPERTY_FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(PROPERTY_FACING, EnumFacing.getFront(meta));
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        if (canRotateVertical()) {
            return this.getDefaultState().withProperty(PROPERTY_FACING, BlockPistonBase.getFacingFromEntity(pos, placer));
        }
        else {
            return this.getDefaultState().withProperty(PROPERTY_FACING, placer.getHorizontalFacing().getOpposite());
        }
    }
}
