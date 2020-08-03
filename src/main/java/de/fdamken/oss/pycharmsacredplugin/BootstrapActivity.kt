package de.fdamken.oss.pycharmsacredplugin

import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.codeInsight.daemon.impl.HighlightInfoType
import com.intellij.codeInsight.daemon.impl.SeverityRegistrar
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.intellij.ui.JBColor
import java.awt.Color

class BootstrapActivity : StartupActivity {
    override fun runActivity(project: Project) {
        val color = JBColor.namedColor("Plugins.Sacred.highlightBackgroundColor", Color(142, 248, 255, 100))

        val textAttributes = TextAttributes()
        textAttributes.backgroundColor = color
        val tmpTextAttributesKey = TextAttributesKey.createTempTextAttributesKey("SacredHighlight", textAttributes)
        val textAttributesKey = TextAttributesKey.createTextAttributesKey("SacredHighlight", tmpTextAttributesKey)
        val highlightInfoType =
            HighlightInfoType.HighlightInfoTypeImpl(CommonConstants.SACRED_HIGHLIGHT_SEVERITY, textAttributesKey, true)
        SeverityRegistrar.registerStandard(highlightInfoType, CommonConstants.SACRED_HIGHLIGHT_SEVERITY)
        HighlightDisplayLevel.registerSeverity(CommonConstants.SACRED_HIGHLIGHT_SEVERITY, textAttributesKey, null)
    }
}
