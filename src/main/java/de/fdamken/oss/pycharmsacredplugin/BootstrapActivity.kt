package de.fdamken.oss.pycharmsacredplugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class BootstrapActivity : StartupActivity {
    override fun runActivity(project: Project) {
        ServiceUtil.getSacredService(project).init()

        HighlightUtil.init()
    }
}
