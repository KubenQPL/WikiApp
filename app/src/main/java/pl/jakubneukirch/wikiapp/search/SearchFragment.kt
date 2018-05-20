package pl.jakubneukirch.wikiapp.search

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.app.WikiApp
import pl.jakubneukirch.wikiapp.base.BaseFragment
import pl.jakubneukirch.wikiapp.di.AcitivityModule

class SearchFragment: BaseFragment<SearchView, SearchPresenter>(), SearchView {

    companion object {
        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun injectDependencies() {
        WikiApp.component
                .getAcitivityComponent(AcitivityModule(activity as Activity))
                .inject(this)
    }
}