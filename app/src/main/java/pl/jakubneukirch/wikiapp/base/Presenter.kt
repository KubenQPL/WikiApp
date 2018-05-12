package pl.jakubneukirch.wikiapp.base

interface Presenter<in T : MvpView> {
    fun attachView(view: T)
    fun detachView()
    fun onDestroy()
}