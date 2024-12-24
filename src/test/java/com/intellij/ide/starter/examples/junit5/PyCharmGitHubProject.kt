package com.intellij.ide.starter.examples.junit5

import com.intellij.ide.starter.examples.Setup
import com.intellij.ide.starter.ide.IDETestContext
import com.intellij.ide.starter.ide.IdeProductProvider
import com.intellij.ide.starter.junit5.hyphenateWithClass
import com.intellij.ide.starter.models.TestCase
import com.intellij.ide.starter.runner.CurrentTestMethod
import com.intellij.ide.starter.runner.Starter
import com.intellij.ide.starter.project.LocalProjectInfo
import com.intellij.tools.ide.performanceTesting.commands.CommandChain
import com.intellij.tools.ide.performanceTesting.commands.exitApp
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class PyCharmGitHubTest {
    companion object {
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