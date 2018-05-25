package pl.jakubneukirch.wikiapp.info

import android.database.MatrixCursor
import android.os.Build
import android.text.Html
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.wikiapp.base.BasePresenter
import pl.jakubneukirch.wikiapp.data.PageRepository
import pl.jakubneukirch.wikiapp.data.model.api.SearchItem
import javax.inject.Inject

const val COLUMN_ID = "page_id"
const val COLUMN_TITLE = "title"
const val COLUMN_SNIPPET = "snippet"

class InfoPresenter @Inject constructor() : BasePresenter<InfoView>() {}