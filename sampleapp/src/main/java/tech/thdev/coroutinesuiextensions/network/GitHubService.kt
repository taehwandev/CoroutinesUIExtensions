package tech.thdev.coroutinesuiextensions.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import tech.thdev.coroutinesuiextensions.data.Contributor

interface GitHubService {

    /**
     * https://developer.github.com/v3/repos/#list-contributors
     */
    @GET("/repos/{owner}/{repo}/contributors")
    fun contributors(
            @Path("owner") owner: String,
            @Path("repo") repo: String
    ): Single<List<Contributor>>
}