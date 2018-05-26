package pl.jakubneukirch.wikiapp.random

import android.database.MatrixCursor
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.base.BasePresenter
import pl.jakubneukirch.wikiapp.data.PageRepository
import pl.jakubneukirch.wikiapp.data.model.api.search.SearchItem
import pl.jakubneukirch.wikiapp.data.model.dto.PageDTO
import pl.jakubneukirch.wikiapp.info.COLUMN_ID
import pl.jakubneukirch.wikiapp.info.COLUMN_SNIPPET
import pl.jakubneukirch.wikiapp.info.COLUMN_TITLE
import javax.inject.Inject

class RandomPresenter @Inject constructor(private val repository: PageRepository) : BasePresenter<RandomView>() {

    private val columns = arrayOf("_id", COLUMN_ID, COLUMN_TITLE, COLUMN_SNIPPET)

    fun onCreate() {
        view?.setup()
        loadRandom()
    }

    private fun loadRandom() {
        disposables += repository.getRandomPages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            view?.setPagesData(it.query.pages.map {
                                PageDTO(
                                        it.pageId,
                                        it.title,
                                        spannedFromHtml(it.extract),
                                        it.thumbnail?.url ?: ""
                                )
                            })
                        },
                        onError = {
                            Log.d("Error", it.message)
                            view?.showMessage(R.string.error)
                        }
                )
    }

    fun searchQuerySubmitted(text: String) {
        disposables += repository.search(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            Log.d("apiCall", "$it")
                            updateSearchResults(it.query.search)
                        }
                )
    }

    private fun updateSearchResults(list: List<SearchItem>) {
        val cursor = MatrixCursor(columns)
        list.forEachIndexed { id, item ->
            cursor.addRow(arrayOf(
                    id,
                    item.pageId,
                    item.title,
                    stringFromHtml(item.snippet)
            ))
        }
        view?.showSearchResults(cursor)
    }

    private fun stringFromHtml(html: String): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
        } else {
            return Html.fromHtml(html).toString()
        }
    }

    private fun spannedFromHtml(html: String): Spanned {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        } else {
            return Html.fromHtml(html)
        }
    }
}