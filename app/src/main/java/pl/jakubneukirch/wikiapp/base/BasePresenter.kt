package pl.jakubneukirch.wikiapp.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : MvpView>: Presenter<T> {

    protected var view: T? = null
    protected val disposables = CompositeDisposable()

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onDestroy() {
        disposables.dispose()
    }
}