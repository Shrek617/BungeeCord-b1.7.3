package net.md_5.bungee.protocol.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.md_5.bungee.protocol.MinecraftInput;
import net.md_5.bungee.protocol.MinecraftOutput;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class PacketFAPluginMessage extends DefinedPacket
{
    private byte[] data;

    private PacketFAPluginMessage()
    {
        super( 0xFA );
    }

    public PacketFAPluginMessage(byte[] data)
    {
        this();
        this.data = data;
    }

    @Override
    public void read(ByteBuf buf)
    {
        data = readArray( buf );
    }

    @Override
    public void write(ByteBuf buf)
    {
        writeArray( data, buf );
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception
    {
        handler.handle( this );
    }

    public DataInput getStream()
    {
        return new DataInputStream( new ByteArrayInputStream( data ) );
    }

    public MinecraftInput getMCStream()
    {
        return new MinecraftInput( Unpooled.wrappedBuffer( data ) );
    }
}
