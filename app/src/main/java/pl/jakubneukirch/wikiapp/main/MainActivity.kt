package pl.jakubneukirch.wikiapp.main

import android.os.Bundle
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.app.WikiApp
import pl.jakubneukirch.wikiapp.base.BaseActivity
import pl.jakubneukirch.wikiapp.di.AcitivityModule
import pl.jakubneukirch.wikiapp.search.SearchFragment

class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        presenter.onCreate()
        fragmentManager.beginTransaction()
                .add(R.id.root, SearchFragment())
                .commit()
    }

    override fun injectDependencies(){
        WikiApp.component
                .getAcitivityComponent(AcitivityModule(this))
                .inject(this)
    }

}
