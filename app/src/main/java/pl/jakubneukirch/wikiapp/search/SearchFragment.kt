package pl.jakubneukirch.wikiapp.search

import android.app.Activity
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SimpleCursorAdapter
import android.view.*
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.app.WikiApp
import pl.jakubneukirch.wikiapp.base.BaseFragment
import pl.jakubneukirch.wikiapp.di.AcitivityModule

class SearchFragment: BaseFragment<SearchView, SearchPresenter>(), SearchView {

    private lateinit var searchView: android.support.v7.widget.SearchView

    companion object {
        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }

    override fun setup() {
    }

    override fun showSearchResults(cursor: Cursor) {
        searchView.suggestionsAdapter = SimpleCursorAdapter(
                context,
                R.layout.search_item,
                cursor,
                arrayOf(COLUMN_TITLE, COLUMN_SNIPPET),
                intArrayOf(R.id.searchedTitleTextView, R.id.searchedSnippetTextView),
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        )
    }

    override fun injectDependencies() {
        WikiApp.component
                .getAcitivityComponent(AcitivityModule(activity as Activity))
                .inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        setupMenu(menu)
    }

    private fun setupMenu(menu: Menu) {
        searchView = menu.findItem(R.id.searchMenu).actionView as android.support.v7.widget.SearchView
        searchView.setOnQueryTextListener(object: android.support.v7.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.searchQuerySubmitted(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean = false
        })
    }
}