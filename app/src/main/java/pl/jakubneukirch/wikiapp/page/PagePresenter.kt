package pl.jakubneukirch.wikiapp.page

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.jakubneukirch.wikiapp.base.BasePresenter
import pl.jakubneukirch.wikiapp.data.PageRepository
import pl.jakubneukirch.wikiapp.data.model.dto.PageDTO
import pl.jakubneukirch.wikiapp.data.model.dto.mapPageDTO
import javax.inject.Inject

class PagePresenter @Inject constructor(private val repository: PageRepository) : BasePresenter<PageView>() {

    private lateinit var page: PageDTO

    fun onCreate(pageId: Long) {
        loadPage(pageId)
    }

    private fun loadPage(pageId: Long) {
        disposables += repository.getPage(pageId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            page = mapPageDTO(it.query.pages[0])
                            if(page.imageUrl.isNotEmpty()){
                                view?.setImage(page.imageUrl)
                            }
                            view?.setupToolbar(page.title)
                            view?.setDescription(page.description)
                        }
                )
    }
}