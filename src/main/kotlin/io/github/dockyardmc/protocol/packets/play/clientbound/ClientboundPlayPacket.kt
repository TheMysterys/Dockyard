package io.github.dockyardmc.protocol.packets.play.clientbound

import io.github.dockyardmc.extentions.writeStringArray
import io.github.dockyardmc.extentions.writeUtf
import io.github.dockyardmc.extentions.writeVarInt
import io.github.dockyardmc.player.GameMode
import io.github.dockyardmc.protocol.packets.ClientboundPacket
import io.github.dockyardmc.protocol.packets.ProtocolState

class ClientboundPlayPacket(
    entityId: Int,
    isHardcore: Boolean,
    dimensionNames: MutableList<String>,
    maxPlayers: Int,
    viewDistance: Int,
    simulationDistance: Int,
    reducedDebugInfo: Boolean,
    enableRespawnScreen: Boolean,
    doLimitedCrafting: Boolean,
    dimensionType: String,
    dimensionName: String,
    hashedSeed: Long,
    gameMode: GameMode,
    previousGameMode: GameMode,
    isDebug: Boolean,
    isFlat: Boolean,
    portalCooldown: Int,
): ClientboundPacket(0x29, ProtocolState.PLAY) {

    init {
        data.writeInt(entityId)
        data.writeBoolean(isHardcore)
        data.writeStringArray(dimensionNames)
        data.writeVarInt(maxPlayers)
        data.writeVarInt(viewDistance)
        data.writeVarInt(simulationDistance)
        data.writeBoolean(reducedDebugInfo)
        data.writeBoolean(enableRespawnScreen)
        data.writeBoolean(doLimitedCrafting)
        data.writeUtf(dimensionType)
        data.writeUtf(dimensionName)
        data.writeLong(hashedSeed)
        data.writeByte(gameMode.ordinal)
        data.writeByte(previousGameMode.ordinal)
        data.writeBoolean(isDebug)
        data.writeBoolean(isFlat)

        data.writeBoolean(false) // has death location
//        data.writeUtf("minecraft:world") // death dimension
//        data.writePosition(Location(0, 0, 0)) // death location

        data.writeVarInt(portalCooldown)
    }
}