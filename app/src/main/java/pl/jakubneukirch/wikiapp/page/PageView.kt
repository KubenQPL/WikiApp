package pl.jakubneukirch.wikiapp.page

import android.text.Spanned
import pl.jakubneukirch.wikiapp.base.MvpView

interface PageView: MvpView {
    fun setupToolbar(title: String)
    fun setImage(imageUrl: String)
    fun setDescription(description: Spanned)
}