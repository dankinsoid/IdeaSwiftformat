package com.github.dankinsoid.ideaswiftformat.actions

import com.github.dankinsoid.ideaswiftformat.services.SwiftformatCLI
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.project.DumbAware

class AutoFormatAction: ToggleAction(), DumbAware {

    override fun isSelected(e: AnActionEvent): Boolean {
        return e.project?.let {
            SwiftformatCLI(it).state.isAutoUpdate
        } ?: false
    }

    override fun setSelected(e: AnActionEvent, isTurnOn: Boolean) {
        e.project?.let {
            val cli = SwiftformatCLI(it)
            val state = cli.state
            state.isAutoUpdate = isTurnOn
            cli.loadState(state)
        }
    }

    override fun update(e: AnActionEvent) {}
}
