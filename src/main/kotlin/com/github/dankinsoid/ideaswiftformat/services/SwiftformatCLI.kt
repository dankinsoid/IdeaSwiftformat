package com.github.dankinsoid.ideaswiftformat.services

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.CapturingProcessHandler
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.process.ProcessOutput
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VfsUtil
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Path

@State(name = "swiftformatCache")
class SwiftformatCLI(
    val project: Project,
    val rootPath: String? = null,
    val silent: Boolean = false
): PersistentStateComponent<SwiftformatCLI.State> {

    private var myState = State()
    private val defaultPath = "."

    fun generate(path: String?, onSuccess: () -> Unit = {}) {
        execute(path ?: defaultPath, "Formatting files", listOf()) {
            refresh(path)
            onSuccess()
        }
    }

    fun generateInProjectStyle(path: String?, onSuccess: () -> Unit = {}) {
        execute(path ?: defaultPath, "Analysing files", listOf("--inferoptions")) { str ->
            execute(path ?: defaultPath, "Formatting files", str.split(" ")) {
                refresh(path)
                onSuccess()
            }
        }
    }

    private fun refresh(path: String?) {
        val file = path?.let { VfsUtil.findFile(Path.of(it), false) } ?: project.guessProjectDir()
        file?.refresh(false, true)
    }

    override fun getState(): State {
        return myState
    }

    override fun loadState(state: State) {
        myState = state
    }

    data class State(
        var isAutoUpdate: Boolean = false
    )

    private fun execute(command: String, title: String, arguments: List<String>, onSuccess: (String) -> Unit = {}) {
        ProgressManager.getInstance().run(object : Task.Backgroundable(project, title) {
            override fun run(progressIndicator: ProgressIndicator) {
                val output = execute(commandLine(command, arguments), {
                    progressIndicator.text = it.text
                    progressIndicator.fraction += (1 - progressIndicator.fraction) / 4
                }, {
                    progressIndicator.fraction = 1.0
                })
                if (output.exitCode == 0) {
                    onSuccess(output.stdoutLines.joinToString("\n"))
                }
            }
        })
    }

    private fun execute(commandLine: GeneralCommandLine, onText: (event: ProcessEvent) -> Unit = {}, onTerminate: (event: ProcessEvent) -> Unit = {}): ProcessOutput {
        val output = CapturingProcessHandler(commandLine).also { processHandler ->
            processHandler.addProcessListener(object : ProcessAdapter() {

                override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
                    onText(event)
                }

                override fun processTerminated(event: ProcessEvent) {
                    onTerminate(event)
                }
            })
        }.runProcess()

        if (!silent && output.stderrLines.isNotEmpty()) {
            Notification(
                "SwiftformatNotifications",
                "Swiftformat ${commandLine.parametersList.list.joinToString(" ")}",
                output.stderrLines.joinToString("\n\n"),
                if (output.exitCode == 0) { NotificationType.INFORMATION } else { NotificationType.ERROR }
            ).notify(project)
        }

        return output
    }

    private fun commandLine(command: String, arguments: List<String>): GeneralCommandLine {
        val commandLine = GeneralCommandLine("swiftformat")

        commandLine.workDirectory = File(rootPath ?: project.basePath ?: "")
        commandLine.charset = Charset.forName("UTF-8")
        commandLine.addParameter(command)
        arguments.forEach { commandLine.addParameter(it) }

        return commandLine
    }
}
