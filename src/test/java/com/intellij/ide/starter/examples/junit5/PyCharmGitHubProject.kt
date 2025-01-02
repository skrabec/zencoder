package com.intellij.ide.starter.examples.junit5

import com.intellij.ide.starter.examples.Setup
import com.intellij.ide.starter.ide.IDETestContext
import com.intellij.tools.ide.performanceTesting.commands.CommandChain
import com.intellij.tools.ide.performanceTesting.commands.exitApp
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class PyCharmGitHubTest {
    companion object {
        private val logger = LoggerFactory.getLogger(PyCharmGitHubTest::class.java)

        private lateinit var context: IDETestContext

        @BeforeAll
        @JvmStatic
        fun initContext() {
            context = Setup.setupTestContext()
        }
    }

    @Test
    fun openProject() {
        context.copy().executeDuringIndexing().runIDE(
            commands = CommandChain().exitApp(),
            launchName = "openProjectTest"
        )
    }
}