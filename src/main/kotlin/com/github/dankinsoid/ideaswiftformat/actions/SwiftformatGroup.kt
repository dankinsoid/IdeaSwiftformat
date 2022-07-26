package com.github.dankinsoid.ideaswiftformat.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware

class SwiftformatGroup: DefaultActionGroup(), DumbAware {

    override fun actionPerformed(event: AnActionEvent) {
    }

    override fun update(event: AnActionEvent) {
        val file = event.getData(CommonDataKeys.VIRTUAL_FILE)
        if (file?.isDirectory != true && file?.extension != "swift") {
            event.presentation.isVisible = false
        }
    }
}
