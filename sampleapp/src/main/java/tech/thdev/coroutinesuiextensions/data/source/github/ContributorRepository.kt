package tech.thdev.coroutinesuiextensions.data.source.github

import tech.thdev.coroutinesuiextensions.network.GitHubService

class ContributorRepository private constructor(private val apiService: GitHubService) {

    fun loadRepository(owner: String, repo: String) =
            apiService.contributors(owner, repo)
}