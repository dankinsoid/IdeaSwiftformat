package com.github.dankinsoid.ideaswiftformat.actions

import com.github.dankinsoid.ideaswiftformat.services.SwiftformatCLI
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware

class GenerateFile: AnAction(), DumbAware {

    override fun actionPerformed(event: AnActionEvent) {
        val files = event.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY) ?: return
        event.project?.let { pr ->
            files.forEach {
                SwiftformatCLI(pr).generate(it.path)
            }
        }
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = e.project != null
    }
}
