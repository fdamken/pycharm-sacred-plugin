package de.fdamken.oss.pycharmsacredplugin

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.python.psi.PyAssignmentStatement
import com.jetbrains.python.psi.PyElementVisitor
import com.jetbrains.python.psi.PyExpression
import com.jetbrains.python.psi.PyFunction

class ConfigInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PyElementVisitor() {
            // TODO: Force re-evaluation of annotator after getting the latest information about the experiment config.
            override fun visitPyFunction(node: PyFunction) {
                if (SacredDecorator.determineSacredDecoration(node) != SacredDecorator.CONFIG) {
                    return
                }
                PsiTreeUtil.findChildrenOfType(node, PyAssignmentStatement::class.java).forEach { assignment ->
                    assignment.targets.forEach { target -> addIngredient(holder, assignment, target) }
                }
            }
        }
    }

    private fun addIngredient(holder: ProblemsHolder, assignment: PyAssignmentStatement, target: PyExpression) {
        val name = target.name ?: return
        ServiceUtil.getSacredService(holder.project).addIngredient(name)
    }
}
