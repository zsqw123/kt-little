package com.zsu.asm.rs.compare

import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import java.io.File

// execute root is java-asm project root
fun main() {
    val file = File("asm/tests/res/bytecode/CompileTesting.class")
    val bytes = file.readBytes()

    // preload to prevent java class loading
    readNode(bytes)
    readNode(bytes)
    readNode(bytes)

    val readStart = System.nanoTime()
    readNode(bytes)
    val end = System.nanoTime()
    val duration = (end - readStart) / 1000
    println("read class cost $duration us")
}

fun readNode(bytes: ByteArray) {
    val classNode = ClassNode()
    val classReader = ClassReader(bytes)
    classReader.accept(classNode, 0)
}