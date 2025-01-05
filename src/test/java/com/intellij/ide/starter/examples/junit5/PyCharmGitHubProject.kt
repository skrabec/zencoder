package com.intellij.ide.starter.examples.junit5

import com.intellij.ide.starter.examples.data.TestCases
import com.intellij.ide.starter.junit5.config.EnableClassFileVerification
import com.intellij.ide.starter.junit5.config.UseLatestDownloadedIdeBuild
import com.intellij.ide.starter.junit5.hyphenateWithClass
import com.intellij.ide.starter.runner.CurrentTestMethod
import com.intellij.ide.starter.runner.Starter
import com.intellij.tools.ide.performanceTesting.commands.CommandChain
import com.intellij.tools.ide.performanceTesting.commands.exitApp
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(EnableClassFileVerification::class)
@ExtendWith(UseLatestDownloadedIdeBuild::class)
class PyCharmGitHubTest {
    @Test
    fun openPythonProjectInPyCharm() {
        val testContext = Starter
            .newContext(CurrentTestMethod.hyphenateWithClass(), TestCases.PY.PublicApis.useRelease())
        testContext.runIDE(commands = CommandChain().exitApp())
    }
}