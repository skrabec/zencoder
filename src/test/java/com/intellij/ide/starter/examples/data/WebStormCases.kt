package com.intellij.ide.starter.examples.data

import com.intellij.ide.starter.ide.IdeProductProvider
import com.intellij.ide.starter.project.GitHubProject
import com.intellij.ide.starter.project.TestCaseTemplate

object WebStormCases: TestCaseTemplate(IdeProductProvider.WS) {
    val WebStormCases = withProject(
        GitHubProject.fromGithub(
            branchName = "main",
            repoRelativeUrl = "https://github.com/skrabec/ws-js_repo"
        )
    )
}
