package org.ia

fun main(args: Array<String>) {
    listOf(1,2, 3,4).map {
        it * 2
    }.flatMap {
        listOf(it, it)
    }.reduce { acc, elem ->
        acc + elem
    }
}