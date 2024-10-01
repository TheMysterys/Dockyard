package io.github.dockyardmc.registry

import io.github.dockyardmc.registry.registries.PotionEffect
import io.github.dockyardmc.registry.registries.PotionEffectRegistry

object PotionEffects {
    val SPEED = PotionEffectRegistry["minecraft:speed"]
    val SLOWNESS = PotionEffectRegistry["minecraft:slowness"]
    val HASTE = PotionEffectRegistry["minecraft:haste"]
    val MINING_FATIGUE = PotionEffectRegistry["minecraft:mining_fatigue"]
    val STRENGTH = PotionEffectRegistry["minecraft:strength"]
    val INSTANT_HEALTH = PotionEffectRegistry["minecraft:instant_health"]
    val INSTANT_DAMAGE = PotionEffectRegistry["minecraft:instant_damage"]
    val JUMP_BOOST = PotionEffectRegistry["minecraft:jump_boost"]
    val NAUSEA = PotionEffectRegistry["minecraft:nausea"]
    val REGENERATION = PotionEffectRegistry["minecraft:regeneration"]
    val RESISTANCE = PotionEffectRegistry["minecraft:resistance"]
    val FIRE_RESISTANCE = PotionEffectRegistry["minecraft:fire_resistance"]
    val WATER_BREATHING = PotionEffectRegistry["minecraft:water_breathing"]
    val INVISIBILITY = PotionEffectRegistry["minecraft:invisibility"]
    val BLINDNESS = PotionEffectRegistry["minecraft:blindness"]
    val NIGHT_VISION = PotionEffectRegistry["minecraft:night_vision"]
    val HUNGER = PotionEffectRegistry["minecraft:hunger"]
    val WEAKNESS = PotionEffectRegistry["minecraft:weakness"]
    val POISON = PotionEffectRegistry["minecraft:poison"]
    val WITHER = PotionEffectRegistry["minecraft:wither"]
    val HEALTH_BOOST = PotionEffectRegistry["minecraft:health_boost"]
    val ABSORPTION = PotionEffectRegistry["minecraft:absorption"]
    val SATURATION = PotionEffectRegistry["minecraft:saturation"]
    val GLOWING = PotionEffectRegistry["minecraft:glowing"]
    val LEVITATION = PotionEffectRegistry["minecraft:levitation"]
    val LUCK = PotionEffectRegistry["minecraft:luck"]
    val BAD_LUCK = PotionEffectRegistry["minecraft:unluck"]
    val SLOW_FALLING = PotionEffectRegistry["minecraft:slow_falling"]
    val CONDUIT_POWER = PotionEffectRegistry["minecraft:conduit_power"]
    val DOLPHINS_GRACE = PotionEffectRegistry["minecraft:dolphins_grace"]
    val BAD_OMEN = PotionEffectRegistry["minecraft:bad_omen"]
    val HERO_OF_THE_VILLAGE = PotionEffectRegistry["minecraft:hero_of_the_village"]
    val DARKNESS = PotionEffectRegistry["minecraft:darkness"]
    val TRIAL_OMEN = PotionEffectRegistry["minecraft:trial_omen"]
    val RAID_OMEN = PotionEffectRegistry["minecraft:raid_omen"]
    val WIND_CHARGED = PotionEffectRegistry["minecraft:wind_charged"]
    val WEAVING = PotionEffectRegistry["minecraft:weaving"]
    val OOZING = PotionEffectRegistry["minecraft:oozing"]
    val INFESTED = PotionEffectRegistry["minecraft:infested"]
}

data class AppliedPotionEffect(
    var effect: PotionEffect,
    var duration: Int,
    var level: Int = 1,
    var showParticles: Boolean = false,
    var showBlueBorder: Boolean = false,
    var showIconOnHud: Boolean = false,
    var startTime: Long? = null,
)