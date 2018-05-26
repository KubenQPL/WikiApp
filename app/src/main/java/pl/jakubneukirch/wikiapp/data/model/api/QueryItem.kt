package pl.jakubneukirch.wikiapp.data.model.api

import pl.jakubneukirch.wikiapp.data.model.api.search.SearchItem

data class QueryItem(
        val search: List<SearchItem>
)