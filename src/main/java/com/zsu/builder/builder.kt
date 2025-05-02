package com.zsu.builder

import kotlin.properties.Delegates

data class FileSystemNode(
    val name: String,
    val path: String,
    val size: Long,
)

class BuildSystemNode {
    var name: String by Delegates.notNull()
    var path: String by Delegates.notNull()
    var size: Long by Delegates.notNull()

    companion object {
        inline operator fun invoke(block: BuildSystemNode.() -> Unit) = BuildSystemNode().apply(block)
    }
}