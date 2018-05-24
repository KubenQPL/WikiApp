package pl.jakubneukirch.wikiapp.search

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
import pl.jakubneukirch.wikiapp.data.model.SearchItem
import javax.inject.Inject

const val COLUMN_ID = "page_id"
const val COLUMN_TITLE = "title"
const val COLUMN_SNIPPET = "snippet"

class SearchPresenter @Inject constructor(private val repository: PageRepository) : BasePresenter<SearchView>() {

    private val columns = arrayOf("_id",COLUMN_ID, COLUMN_TITLE, COLUMN_SNIPPET)

    fun onCreate() {
        view?.setup()
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
                    fromHtml(item.snippet)
            ))
        }
        view?.showSearchResults(cursor)
    }

    private fun fromHtml(html: String) : String{
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString()
        }else{
            return Html.fromHtml(html).toString()
        }
    }
}