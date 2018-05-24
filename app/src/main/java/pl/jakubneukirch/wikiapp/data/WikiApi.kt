package pl.jakubneukirch.wikiapp.data

import io.reactivex.Single
import pl.jakubneukirch.wikiapp.data.model.SearchPage
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("w/api.php?action=query&format=json&list=search&srwhat=text")
    fun search(@Query("srsearch") searchQuery: String): Single<SearchPage>
}