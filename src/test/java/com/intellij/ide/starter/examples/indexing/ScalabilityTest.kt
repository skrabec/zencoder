package com.intellij.ide.starter.examples.indexing

import com.intellij.ide.starter.ide.IdeProductProvider
import com.intellij.ide.starter.junit5.hyphenateWithClass
import com.intellij.ide.starter.models.TestCase
import com.intellij.ide.starter.project.LocalProjectInfo
import com.intellij.ide.starter.runner.CurrentTestMethod
import com.intellij.ide.starter.runner.Starter
import com.intellij.ide.starter.runner.TestContainerImpl
import com.intellij.tools.ide.metrics.collector.starter.metrics.extractIndexingMetrics
import com.intellij.tools.ide.performanceTesting.commands.CommandChain
import com.intellij.tools.ide.performanceTesting.commands.exitApp
import com.intellij.tools.ide.performanceTesting.commands.waitForSmartMode
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.extension.ExtendWith
import java.nio.file.Paths

@Disabled("Requires local installation of IDE, configs and project")
class ScalabilityTest {
  @Test
  fun scalabilityOfIndexingTest() {
    //CONFIGURATION
    //provide path to your local project
    val testCase = TestCase(IdeProductProvider.IU, LocalProjectInfo(Paths.get("C:/Users/skrab/PycharmProjects/getting-started-python")))
      //provide the required release
      .useRelease("2023.1.2")

    //provide path to config with valid license
    val config = Paths.get("C:/Program Files/JetBrains/PyCharm Community Edition 2023.1.2/license")
    //provide path to plugins if needed
    val plugins = Paths.get("C:/Program Files/JetBrains/PyCharm Community Edition 2023.1.2/plugins")

    val processorCounts = listOf(1, 2, 4, 8, 16, 32, 64)
    val results = mutableMapOf<Int, List<Long>>()
    for (processorCount in processorCounts) {
      val context = Starter
        .newContext("${CurrentTestMethod.hyphenateWithClass()}_$processorCount", testCase)
        .copyExistingConfig(config)
        .copyExistingPlugins(plugins)
        .setActiveProcessorCount(processorCount)

      val commands = CommandChain().waitForSmartMode().exitApp()

      val result = context.runIDE(commands = commands)
      val indexingMetrics = extractIndexingMetrics(result) //todo change to extractOldIndexingMetrics for 2023.2
      results[processorCount] = listOf(indexingMetrics.totalIndexingTimeWithoutPauses, indexingMetrics.totalScanFilesTimeWithoutPauses)
    }
    println("\n###RESULTS###")
    println("Number of processors, Total Indexing Time, Total Scanning Time")
    for (result in results) {
      println(result.key.toString() + "," + result.value.joinToString(","))
    }
    println("######\n")
  }
}