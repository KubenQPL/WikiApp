package pl.jakubneukirch.wikiapp.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import javax.inject.Inject

abstract class BaseFragment<in P: MvpView, T: Presenter<P>>: Fragment(), MvpView {

    @Inject
    protected lateinit var presenter: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        attachViewToPresenter()
    }

    override fun showMessage(text: String) {
        Snackbar.make(activity!!.window.decorView.findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT).show()
    }

    override fun showMessage(stringId: Int) {
        showMessage(getString(stringId))
    }

    private fun attachViewToPresenter(){
        presenter.attachView(this as P)
    }

    override fun onDestroy() {
        presenter.detachView()
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        menu.clear()
    }

    abstract fun injectDependencies()
}