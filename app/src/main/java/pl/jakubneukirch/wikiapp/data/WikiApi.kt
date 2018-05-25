package pl.jakubneukirch.wikiapp.data

import io.reactivex.Single
import pl.jakubneukirch.wikiapp.data.model.api.PagesResponse
import pl.jakubneukirch.wikiapp.data.model.api.RandomResponse
import pl.jakubneukirch.wikiapp.data.model.api.SearchPage
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("w/api.php?action=query&format=json&list=search&srwhat=text")
    fun search(@Query("srsearch") searchQuery: String): Single<SearchPage>

    @GET("w/api.php?action=query&format=json&list=random&rnlimit=5")
    fun getRandom(): Single<RandomResponse>

    @GET("w/api.php?action=query&format=json&prop=revisions&rvprop=content")
    fun getMultiplePages(@Query("pageids") pageids: String): Single<PagesResponse>
}