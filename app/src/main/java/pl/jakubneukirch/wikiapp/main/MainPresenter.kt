package pl.jakubneukirch.wikiapp.main

import pl.jakubneukirch.wikiapp.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(): BasePresenter<MainView>() {
    fun onCreate(){
        view?.setup()
    }
}