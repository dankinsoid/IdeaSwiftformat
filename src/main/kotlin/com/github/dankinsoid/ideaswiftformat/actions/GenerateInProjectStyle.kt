package com.github.dankinsoid.ideaswiftformat.actions

import com.github.dankinsoid.ideaswiftformat.services.SwiftformatCLI
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware

class GenerateInProjectStyle: AnAction(), DumbAware {

    override fun actionPerformed(p0: AnActionEvent) {
        p0.project?.let {
            SwiftformatCLI(it).generateInProjectStyle(null)
        }
    }

    override fun update(e: AnActionEvent) {}
}
