package io.github.dockyardmc.sidebar

import cz.lukynka.Bindable
import cz.lukynka.BindableList
import io.github.dockyardmc.events.Events
import io.github.dockyardmc.events.PlayerJoinEvent
import io.github.dockyardmc.player.PersistentPlayer
import io.github.dockyardmc.player.Player
import io.github.dockyardmc.player.contains
import io.github.dockyardmc.player.sendPacket
import io.github.dockyardmc.protocol.packets.play.clientbound.*
import java.util.UUID

class Sidebar(initialTitle: String, builder: Sidebar.() -> Unit) {

    val title: Bindable<String> = Bindable(initialTitle)
    val viewers: BindableList<PersistentPlayer> = BindableList()
    private val innerLines: MutableMap<Int, SidebarLine> = mutableMapOf()
    val lines get() = innerLines.toList()

    private val objective = UUID.randomUUID().toString()

    private val createPacket get() = ClientboundScoreboardObjectivePacket(objective, ScoreboardMode.CREATE, title.value, ScoreboardType.INTEGER)
    private val displayPacket get() = ClientboundDisplayObjectivePacket(ObjectivePosition.SIDEBAR, objective)

    fun setGlobalLine(value: String) {
        setGlobalLine(16 - innerLines.size, value)
    }

    fun setPlayerLine(value: (Player) -> String) {
        setPlayerLine(16 - innerLines.size, value)
    }

    fun setGlobalLine(line: Int, value: String) {
        innerLines[line] = GlobalSidebarLine(value)
        viewers.values.forEach { sendLinePacket(it.toPlayer(), line) }
    }

    fun setPlayerLine(line: Int, value: (Player) -> String) {
        innerLines[line] = PersonalizedSidebarLine(value)
        viewers.values.forEach { sendLinePacket(it.toPlayer(), line) }
    }

    private fun sendCreatePackets(player: Player) {
        player.sendPacket(createPacket)
        player.sendPacket(displayPacket)
    }

    private fun sendLinesPackets(player: Player) {
        innerLines.forEach {
            sendLinePacket(player, it.key)
        }
    }

    fun sendLinePacket(player: Player, line: Int) {
        player.sendPacket(ClientboundUpdateScorePacket(objective, line, getLine(line, player)))
    }

    private fun getLine(line: Int, player: Player): String {
        val value = when(val it = innerLines[line]) {
            is GlobalSidebarLine -> it.value
            is PersonalizedSidebarLine -> it.getValue(player)
            else -> ""
        }
        return value.replace("'", "")
    }

    init {
        builder.invoke(this)
        //TODO Item remove, clear sidebar
        viewers.itemAdded { event ->
            val player = event.item.toPlayer()
            sendCreatePackets(player)
            sendLinesPackets(player)
        }
        viewers.itemRemoved { event ->
            val player = event.item.toPlayer()
            lines.forEach {
                val packet = ClientboundResetScorePacket("line-${it.first}", objective)
                player.sendPacket(packet)
            }
        }
        title.valueChanged {
            val packet = ClientboundScoreboardObjectivePacket(objective, ScoreboardMode.EDIT_TEXT, it.newValue, ScoreboardType.INTEGER)
            viewers.sendPacket(packet)
        }

        Events.on<PlayerJoinEvent> {
            if(viewers.contains(it.player)) {
                sendCreatePackets(it.player)
                sendLinesPackets(it.player)
            }
        }
    }
}