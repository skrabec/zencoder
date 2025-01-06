package com.intellij.ide.starter.examples.junit5

import com.intellij.ide.starter.examples.data.TestCases
import com.intellij.ide.starter.junit5.config.EnableClassFileVerification
import com.intellij.ide.starter.junit5.config.UseLatestDownloadedIdeBuild
import com.intellij.ide.starter.junit5.hyphenateWithClass
import com.intellij.ide.starter.runner.CurrentTestMethod
import com.intellij.ide.starter.runner.Starter
import com.intellij.tools.ide.performanceTesting.commands.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(EnableClassFileVerification::class)
@ExtendWith(UseLatestDownloadedIdeBuild::class)
class WebStormGitHubTest {
    @Test
    fun openJavaScriptProjectInWebStorm() {
        val testContext = Starter
            .newContext(CurrentTestMethod.hyphenateWithClass(), TestCases.WS.WebStormCases.useRelease())
        testContext.runIDE(commands = CommandChain()
            .waitForSmartMode()  // Wait for potential indexing after pull
            .openFile("test.js")
            .waitForSmartMode()
            .goto(1, 1)
            .delayType(
                delayMs = 50,
                text = "console.log('Hello from test');",
                calculateAnalyzesTime = true,
                disableWriteProtection = true
            )
            .pressKey(Keys.ENTER)
            .delayType(
                50,
                "const testValue = 42;",
                calculateAnalyzesTime = true,
                disableWriteProtection = true
            )
            .exitApp()
        )
    }
}