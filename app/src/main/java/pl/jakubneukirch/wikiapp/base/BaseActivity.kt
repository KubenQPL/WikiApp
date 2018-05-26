package pl.jakubneukirch.wikiapp.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<in P : MvpView, T : Presenter<P>> : AppCompatActivity(), MvpView {
    @Inject protected lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        attachViewToPresenter()
    }

    override fun showMessage(text: String) {
        Snackbar.make(window.decorView.findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT).show()
    }

    override fun showMessage(stringId: Int) {
        showMessage(getString(stringId))
    }

    private fun attachViewToPresenter() {
        presenter.attachView(this as P)
    }

    override fun onDestroy() {
        presenter.detachView()
        presenter.onDestroy()
        super.onDestroy()
    }

    abstract fun injectDependencies()
}