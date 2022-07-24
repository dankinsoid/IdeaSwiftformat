package com.github.dankinsoid.ideaswiftformat.listeners

import com.github.dankinsoid.ideaswiftformat.services.SwiftformatCLI
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import java.util.*

internal class SwiftformatProjectListener : ProjectManagerListener {

    private val changedFiles: MutableSet<String> = mutableSetOf()
    private var lastUpdateDate = Date()

    override fun projectOpened(project: Project) {
        if (!swiftformat(project).state.isAutoUpdate) {
            return
        }
        val bus = project.messageBus

        bus.connect().subscribe(VirtualFileManager.VFS_CHANGES, object : BulkFileListener {

            override fun before(events: MutableList<out VFileEvent>) {
                changedFiles.addAll(
                    events
                        .filter {
                            it.isFromSave && it.file?.extension == "swift"
                        }
                        .mapNotNull { it.file?.name }
                )
                updateWithThrottle(project)
            }
        })
    }

    override fun projectClosed(project: Project) {
        update(project)
        if (project.basePath?.contains(".idea") != true) {
            return
        }
    }

    private fun updateWithThrottle(project: Project) {
        val now = Date()
        if (now.time - lastUpdateDate.time > 5_000 && changedFiles.isNotEmpty()) {
            update(project)
            lastUpdateDate = now
        }
    }

    private fun update(project: Project) {
        if (changedFiles.isNotEmpty()) {
            generate(project)
        }
    }

    private fun generate(project: Project) {
        swiftformat(project).generate(null) {
            changedFiles.clear()
        }
    }

    private fun swiftformat(project: Project): SwiftformatCLI = SwiftformatCLI(project, rootPath(project), true)

    private fun rootPath(project: Project): String? = null
}
