package pl.jakubneukirch.wikiapp.info

import pl.jakubneukirch.wikiapp.base.BasePresenter
import javax.inject.Inject

const val COLUMN_ID = "page_id"
const val COLUMN_TITLE = "title"
const val COLUMN_SNIPPET = "snippet"

class InfoPresenter @Inject constructor() : BasePresenter<InfoView>() {}