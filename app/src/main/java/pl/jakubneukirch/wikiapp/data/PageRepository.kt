package pl.jakubneukirch.wikiapp.data

import io.reactivex.Single
import pl.jakubneukirch.wikiapp.common.getMultipleParameters
import pl.jakubneukirch.wikiapp.data.model.api.page.PagesResponse
import javax.inject.Inject

class PageRepository @Inject constructor(private val wikiApi: WikiApi) {

    fun search(query: String) = wikiApi.search(query)

    fun getRandomPages(): Single<PagesResponse> = wikiApi.getRandom()
            .flatMap {
                val ids = it.query.random.map { it.id }
                wikiApi.getMultiplePages(getMultipleParameters(ids))
            }

    fun getPage(pageId: Long): Single<PagesResponse> = wikiApi.getPage(pageId)

}