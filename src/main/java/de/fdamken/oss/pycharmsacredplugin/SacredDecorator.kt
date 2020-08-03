package de.fdamken.oss.pycharmsacredplugin

import com.jetbrains.python.psi.PyFunction

enum class SacredDecorator(val decoratorName: String, val captureIngredients: Boolean) {
    CAPTURE("capture", true),
    AUTOMAIN("automain", true),
    CONFIG("config", false);

    companion object {
        fun determineSacredDecoration(function: PyFunction): SacredDecorator? {
            val decoratorList = function.decoratorList ?: return null
            for (experimentVariableName in PluginConfig.getExperimentVariableNames()) {
                for (decorator in values()) {
                    if (decoratorList.findDecorator("$experimentVariableName.${decorator.decoratorName}") != null) {
                        return decorator
                    }
                }
            }
            return null
        }
    }
}
