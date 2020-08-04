package de.fdamken.oss.pycharmsacredplugin.service.impl

import de.fdamken.oss.pycharmsacredplugin.service.SacredService

class SacredServiceImpl : SacredService {
    private val ingredients = mutableListOf<String>()

    override fun init() {}

    override fun addIngredient(name: String) {
        ingredients.add(name)
    }

    override fun isIngredientCaptured(name: String) = ingredients.contains(name)
}
