package com.github.dankinsoid.ideaswiftformat.actions

import com.github.dankinsoid.ideaswiftformat.services.SwiftformatCLI
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys
import com.intellij.openapi.project.DumbAware

class GenerateFileInProjectStyle: AnAction(), DumbAware {

    override fun actionPerformed(event: AnActionEvent) {
        val file = event.getData(PlatformCoreDataKeys.VIRTUAL_FILE)
        if (file?.isDirectory != true) {
            event.presentation.isVisible = false
            return
        }
        event.project?.let {
            SwiftformatCLI(it).generateInProjectStyle(file.path)
        }
    }
}
