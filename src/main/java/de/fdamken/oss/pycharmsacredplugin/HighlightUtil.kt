package de.fdamken.oss.pycharmsacredplugin

import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.codeInsight.daemon.impl.HighlightInfoType
import com.intellij.codeInsight.daemon.impl.SeverityRegistrar
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.ui.JBColor
import java.awt.Color

object HighlightUtil {
    val SACRED_HIGHLIGHT_COLOR =
        JBColor.namedColor("Plugins.Sacred.highlightBackgroundColor", Color(142, 248, 255, 100))
    val SACRED_HIGHLIGHT_SEVERITY = HighlightSeverity("Sacred Information", 250)
    val SACRED_HIGHLIGHT_TEXT_ATTRIBUTES_NAME = "SacredHighlight"

    fun init() {
        val textAttributes = TextAttributes()
        textAttributes.backgroundColor = SACRED_HIGHLIGHT_COLOR
        val tmpTextAttributesKey = TextAttributesKey.createTempTextAttributesKey("SacredHighlight", textAttributes)
        val textAttributesKey = TextAttributesKey.createTextAttributesKey("SacredHighlight", tmpTextAttributesKey)
        val highlightInfoType =
            HighlightInfoType.HighlightInfoTypeImpl(SACRED_HIGHLIGHT_SEVERITY, textAttributesKey, true)
        SeverityRegistrar.registerStandard(highlightInfoType, SACRED_HIGHLIGHT_SEVERITY)
        HighlightDisplayLevel.registerSeverity(SACRED_HIGHLIGHT_SEVERITY, textAttributesKey, null)
    }

    fun getHighlightTextAttributes(): TextAttributesKey {
        return TextAttributesKey.find(SACRED_HIGHLIGHT_TEXT_ATTRIBUTES_NAME)
    }
}
