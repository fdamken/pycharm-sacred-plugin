package de.fdamken.oss.pycharmsacredplugin

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.python.psi.PyFunction
import com.jetbrains.python.psi.PyNamedParameter

class CaptureAnnotator : Annotator {
    override fun annotate(psiElement: PsiElement, holder: AnnotationHolder) {
        if (psiElement !is PyNamedParameter) {
            return
        }

        val pyFunction = PsiTreeUtil.getParentOfType(psiElement, PyFunction::class.java) ?: return
        val sacredDecorator = SacredDecorator.determineSacredDecoration(pyFunction) ?: return
        if (!sacredDecorator.captureIngredients) {
            return
        }

        val sacredService = ServiceUtil.getSacredService(psiElement.project)
        val parameterName = psiElement.name ?: return
        if (sacredService.isIngredientCaptured(parameterName)) {
            val message = "Parameter <$parameterName> is captured by Sacred!"
            holder.newAnnotation(HighlightUtil.SACRED_HIGHLIGHT_SEVERITY, message)
                .textAttributes(HighlightUtil.getHighlightTextAttributes())
                .create()
        }
    }
}
