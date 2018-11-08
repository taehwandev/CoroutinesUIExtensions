package tech.thdev.coroutinesuiextensions.network

object RetrofitFactory {

    const val baseUrl = "https://api.github.com"

    val githubApi: GitHubService by lazy {
        createRetrofit(GitHubService::class.java, baseUrl) { true }
    }
}