package pl.jakubneukirch.wikiapp.base

interface MvpView {
    fun showMessage(text: String)
    fun showMessage(stringId: Int)
}