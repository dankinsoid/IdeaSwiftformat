package com.github.dankinsoid.ideaswiftformat.actions

import com.github.dankinsoid.ideaswiftformat.services.SwiftformatCLI
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys
import com.intellij.openapi.project.DumbAware

class GenerateFile: AnAction(), DumbAware {

    override fun actionPerformed(event: AnActionEvent) {
        val file = event.getData(PlatformCoreDataKeys.VIRTUAL_FILE) ?: return
        event.project?.let {
            SwiftformatCLI(it).generate(file.path)
        }
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabled = e.project != null
    }
}
