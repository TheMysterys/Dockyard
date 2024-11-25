package io.github.dockyardmc.protocol.packets.play.clientbound

import io.github.dockyardmc.entity.Entity
import io.github.dockyardmc.extentions.writeVarInt
import io.github.dockyardmc.item.EquipmentSlot
import io.github.dockyardmc.item.ItemStack
import io.github.dockyardmc.item.writeItemStack
import io.github.dockyardmc.protocol.packets.ClientboundPacket

class ClientboundSetEntityEquipmentPacket(val entity: Entity, val equipment: Map<EquipmentSlot, ItemStack>): ClientboundPacket() {

    init {
        data.writeVarInt(entity.entityId)
        val equipmentList = mutableMapOf<Int, ItemStack>()
        equipmentList[0] = equipment.getOrDefault(EquipmentSlot.MAIN_HAND, ItemStack.AIR)
        equipmentList[1] = equipment.getOrDefault(EquipmentSlot.OFF_HAND, ItemStack.AIR)
        equipmentList[2] = equipment.getOrDefault(EquipmentSlot.BOOTS, ItemStack.AIR)
        equipmentList[3] = equipment.getOrDefault(EquipmentSlot.LEGGINGS, ItemStack.AIR)
        equipmentList[4] = equipment.getOrDefault(EquipmentSlot.CHESTPLATE, ItemStack.AIR)
        equipmentList[5] = equipment.getOrDefault(EquipmentSlot.HELMET, ItemStack.AIR)
        equipmentList[6] = equipment.getOrDefault(EquipmentSlot.BODY, ItemStack.AIR)

        var index = 0
        equipmentList.forEach {
            val last = index++ == equipmentList.size - 1
            var slotEnum = it.key.toByte()
            if (!last) slotEnum = (slotEnum.toInt() or 0x80).toByte()

            data.writeByte(slotEnum.toInt())
            data.writeItemStack(it.value)
        }
    }

}


fun getMergedEquipmentData(base: Map<EquipmentSlot, ItemStack>, merge: Map<EquipmentSlot, ItemStack>?): Map<EquipmentSlot, ItemStack> {
    if(merge == null) return base
    val newMap: MutableMap<EquipmentSlot, ItemStack> = mutableMapOf()

    newMap.putAll(base)
    merge.forEach {
        newMap[it.key] = it.value
    }

    return newMap
}