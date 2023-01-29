package cn.nukkit.nbt.tag;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.nbt.stream.NBTInputStream;
import cn.nukkit.nbt.stream.NBTOutputStream;

import java.io.IOException;
import java.util.Arrays;

public class IntArrayTag extends Tag {
    public int[] data;

    @PowerNukkitXOnly
    @Since("1.19.60-r1")
    public IntArrayTag(int[] data) {
        super("");
        this.data = data;
    }

    public IntArrayTag(String name) {
        super(name);
    }

    public IntArrayTag(String name, int[] data) {
        super(name);
        this.data = data;
    }

    @Override
    void write(NBTOutputStream dos) throws IOException {
        dos.writeInt(data.length);
        for (int aData : data) {
            dos.writeInt(aData);
        }
    }

    @Override
    void load(NBTInputStream dis) throws IOException {
        int length = dis.readInt();
        data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = dis.readInt();
        }
    }

    public int[] getData() {
        return data;
    }

    @Override
    public int[] parseValue() {
        return this.data;
    }

    @Override
    public byte getId() {
        return TAG_Int_Array;
    }

    @Override
    public String toString() {
        return "IntArrayTag " + this.getName() + " [" + data.length + " bytes]";
    }

    @Override
    public String toSNBT() {
        return Arrays.toString(data).replace("[", "[I;");
    }

    @Override
    public String toSNBT(int space) {
        return Arrays.toString(data).replace("[", "[I;");
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            IntArrayTag intArrayTag = (IntArrayTag) obj;
            return ((data == null && intArrayTag.data == null) || (data != null && Arrays.equals(data, intArrayTag.data)));
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
    
    @Override
    public Tag copy() {
        int[] cp = new int[data.length];
        System.arraycopy(data, 0, cp, 0, data.length);
        return new IntArrayTag(getName(), cp);
    }
}
