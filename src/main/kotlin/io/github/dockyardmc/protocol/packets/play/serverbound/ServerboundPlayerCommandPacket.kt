package io.github.dockyardmc.protocol.packets.play.serverbound

import io.github.dockyardmc.extentions.readEnum
import io.github.dockyardmc.extentions.readVarInt
import io.github.dockyardmc.player.PlayerAction
import io.github.dockyardmc.protocol.PacketProcessor
import io.github.dockyardmc.protocol.packets.ServerboundPacket
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import log

// Note: Do not confuse with commands packets, this is packet that
// describes actions of player (if they are sneaking, sprinting etc.)
// idk why they named it "player command" packet, im just following the standard
class ServerboundPlayerCommandPacket(val entityId: Int, val action: PlayerAction): ServerboundPacket {

    override fun handle(processor: PacketProcessor, connection: ChannelHandlerContext, size: Int, id: Int) {
        log("Player id $entityId | action: ${action.name}")
    }

    companion object {
        fun read(buf: ByteBuf): ServerboundPlayerCommandPacket {
            return ServerboundPlayerCommandPacket(buf.readVarInt(), buf.readEnum<PlayerAction>())
        }
    }
}