package de.fdamken.oss.pycharmsacredplugin

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import de.fdamken.oss.pycharmsacredplugin.service.SacredService

object ServiceUtil {
    fun getSacredService(project: Project): SacredService {
        return ServiceManager.getService(project, SacredService::class.java)
    }
}
