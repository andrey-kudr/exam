package io.github.adven27.concordion.extensions.exam.nosql

import io.github.adven27.concordion.extensions.exam.core.ExamPlugin
import io.github.adven27.concordion.extensions.exam.nosql.commands.SetCommand

class NoSqlPlugin @JvmOverloads constructor(
    val dbTester: NoSqlDBTester
) : ExamPlugin {

    override fun commands() =
        listOf(
            SetCommand("nosql-set", dbTester)
        )

    @Suppress("EmptyFunctionBlock")
    override fun setUp() {
    }
    @Suppress("EmptyFunctionBlock")
    override fun tearDown() {
    }
}