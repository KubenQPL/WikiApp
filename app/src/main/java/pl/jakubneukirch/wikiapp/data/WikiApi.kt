package pl.jakubneukirch.wikiapp.data

import io.reactivex.Single
import pl.jakubneukirch.wikiapp.data.model.api.page.PagesResponse
import pl.jakubneukirch.wikiapp.data.model.api.random.RandomResponse
import pl.jakubneukirch.wikiapp.data.model.api.search.SearchPage
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("w/api.php?action=query&format=json&list=search&srwhat=text")
    fun search(@Query("srsearch") searchQuery: String): Single<SearchPage>

    @GET("w/api.php?action=query&format=json&rnnamespace=0&list=random&rnlimit=5")
    fun getRandom(): Single<RandomResponse>

    @GET("w/api.php?action=query&format=json&prop=extracts%7Cpageimages&exlimit=5&exintro&piprop=thumbnail&pithumbsize=200")
    fun getMultiplePages(@Query("pageids") pageids: String): Single<PagesResponse>
}