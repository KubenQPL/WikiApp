package pl.jakubneukirch.wikiapp.base

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import javax.inject.Inject

abstract class BaseFragment<in P: MvpView, T: Presenter<P>>: Fragment(), MvpView {

    @Inject
    protected lateinit var presenter: T

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        attachViewToPresenter()
    }

    override fun showMessage(text: String) {
        Snackbar.make(activity.window.decorView, text, Snackbar.LENGTH_SHORT).show()
    }

    private fun attachViewToPresenter(){
        presenter.attachView(this as P)
    }

    override fun onDestroy() {
        presenter.detachView()
        presenter.onDestroy()
        super.onDestroy()
    }

    abstract fun injectDependencies()
}