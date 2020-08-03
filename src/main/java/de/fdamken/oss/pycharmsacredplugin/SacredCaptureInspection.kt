package de.fdamken.oss.pycharmsacredplugin

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.python.psi.PyElementVisitor
import com.jetbrains.python.psi.PyFunction
import com.jetbrains.python.psi.PyNamedParameter

class SacredCaptureInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PyElementVisitor() {
            override fun visitPyNamedParameter(node: PyNamedParameter) {
                val pyFunction = PsiTreeUtil.getParentOfType(node, PyFunction::class.java) ?: return
                val sacredDecorator = SacredDecorator.determineSacredDecoration(pyFunction) ?: return
                if (!sacredDecorator.captureIngredients) {
                    return
                }

                holder.registerProblem(node, "Captured by Sacred.")
            }
        }
    }
}
