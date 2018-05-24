package pl.jakubneukirch.wikiapp.data

import javax.inject.Inject

class PageRepository @Inject constructor(private val wikiApi: WikiApi) {
    fun search(query: String) = wikiApi.search(query)
}