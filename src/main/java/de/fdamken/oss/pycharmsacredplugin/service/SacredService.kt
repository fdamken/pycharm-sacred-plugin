package de.fdamken.oss.pycharmsacredplugin.service

interface SacredService {
    fun init()

    fun addIngredient(name: String)

    fun isIngredientCaptured(name: String): Boolean
}
