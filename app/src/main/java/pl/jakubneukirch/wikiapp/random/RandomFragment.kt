package pl.jakubneukirch.wikiapp.random

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.random_fragment.*
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.app.WikiApp
import pl.jakubneukirch.wikiapp.base.BaseFragment
import pl.jakubneukirch.wikiapp.data.model.dto.PageDTO
import pl.jakubneukirch.wikiapp.di.AcitivityModule
import pl.jakubneukirch.wikiapp.info.COLUMN_SNIPPET
import pl.jakubneukirch.wikiapp.info.COLUMN_TITLE
import pl.jakubneukirch.wikiapp.page.PAGE_ID
import pl.jakubneukirch.wikiapp.page.PageActivity

class RandomFragment : BaseFragment<RandomView, RandomPresenter>(), RandomView {

    private val adapter = RandomRecyclerAdapter()
    private lateinit var searchView: android.support.v7.widget.SearchView

    companion object {
        fun newInstance(): RandomFragment = RandomFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.random_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }

    override fun setup() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter.onItemClick = { _, position ->
            presenter.randomItemClicked(position)
        }
        randomRecyclerView.adapter = adapter
        randomRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun injectDependencies() {
        WikiApp.component
                .getAcitivityComponent(AcitivityModule(activity as Activity))
                .inject(this)
    }

    override fun openPageScreen(pageId: Long) {
        val intent = Intent(context, PageActivity::class.java)
        intent.putExtra(PAGE_ID, pageId)
        startActivity(intent)
    }

    override fun setPagesData(list: List<PageDTO>) {
        adapter.list = list
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        setupMenu(menu)
    }

    private fun setupMenu(menu: Menu) {
        searchView = menu.findItem(R.id.searchMenu).actionView as android.support.v7.widget.SearchView
        searchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.searchQuerySubmitted(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean = false
        })
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

}