package pl.jakubneukirch.wikiapp.di

import dagger.Subcomponent
import pl.jakubneukirch.wikiapp.main.MainActivity
import pl.jakubneukirch.wikiapp.search.SearchFragment

@Subcomponent(modules = [AcitivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: SearchFragment)
}