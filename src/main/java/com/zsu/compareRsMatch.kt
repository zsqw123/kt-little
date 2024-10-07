package com.zsu

class ItemImpl {
    val items: List<ImplItem> = emptyList()
}

sealed interface ImplItem {
    class Const(val item: ImplItemConst) : ImplItem
    class Function : ImplItem
}

class Expr

class Ident

sealed interface ImplItemConst {
    val expr: Expr get() = TODO()
    val ident: Expr get() = TODO()

    class TypePath(val path: Path) : ImplItemConst
}

class Path {
    fun isStrictSame(other: Path): Boolean {
        readConstItemsFromImplBody(ItemImpl(), Path())
        return true
    }
}

fun readConstItemsFromImplBody(
    implBody: ItemImpl,
    expectedType: Path,
) = implBody.items.asSequence()
    .filterIsInstance<ImplItem.Const>()
    .map { it.item }
    .filterIsInstance<ImplItemConst.TypePath>()
    .filter { it.path.isStrictSame(expectedType) }
    .map { it.expr to it.ident }.toList()

