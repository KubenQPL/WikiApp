package pl.jakubneukirch.wikiapp.page

import android.os.Bundle
import android.text.Spanned
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.page_activity.*
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.app.WikiApp
import pl.jakubneukirch.wikiapp.base.BaseActivity
import pl.jakubneukirch.wikiapp.di.AcitivityModule

const val PAGE_ID = "page_id"
const val NO_ID: Long = -1

class PageActivity : BaseActivity<PageView, PagePresenter>(), PageView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_activity)
        presenter.onCreate(intent.getLongExtra(PAGE_ID, NO_ID))
    }

    override fun setupToolbar(title: String) {
        pageToolbar.title = title
        setSupportActionBar(pageToolbar)
    }

    override fun injectDependencies() {
        WikiApp.component
                .getAcitivityComponent(AcitivityModule(this))
                .inject(this)
    }

    override fun setImage(imageUrl: String) {
        pageImageView.visibility = View.VISIBLE
        Picasso.get()
                .load(imageUrl)
                .into(pageImageView)
    }

    override fun setDescription(description: Spanned) {
        pageDescriptionTextView.text = description
    }
}