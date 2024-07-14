package com.zsu.asm

import org.objectweb.asm.ClassReader
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FrameNode
import org.objectweb.asm.tree.MethodNode

inline fun <reified T> readNode(): ClassNode {
    val className = T::class.java.name
    return ClassNode().also {
        ClassReader(className).accept(it, ClassReader.EXPAND_FRAMES)
    }
}

fun MethodNode.printFrames() {
    println("$name $desc:")
    val frames = instructions.filterIsInstance<FrameNode>()
    for (frame in frames) {
        //   UNINITIALIZED_THIS
        val locals = frame.local
        val stack = frame.stack
        val localsAsString = locals.flatMap { it.readAsFrameType() }.joinToString(prefix = "[", postfix = "]")
        val stackAsString = stack.flatMap { it.readAsFrameType() }.joinToString(prefix = "[", postfix = "]")
        println("$localsAsString $stackAsString")
    }
}

private fun String.l() = listOf(this)

private fun Any.readAsFrameType(): List<String> = when (this) {
    Opcodes.TOP -> "top".l()
    Opcodes.INTEGER -> "int".l()
    Opcodes.FLOAT -> "float".l()
    Opcodes.LONG -> listOf("long", "top")
    Opcodes.DOUBLE -> listOf("double", "top")
    Opcodes.NULL -> "null".l()
    Opcodes.UNINITIALIZED_THIS -> "uninitialized_this".l()
    is String -> this.l()
    else -> error("Unknown frame type: $this")
}

fun main() {
    val javaNodeSample = readNode<ShowFrameSample>()
    javaNodeSample.methods.forEach {
        it.printFrames()
        println()
    }
}
