package pl.jakubneukirch.wikiapp.di

import dagger.Subcomponent
import pl.jakubneukirch.wikiapp.info.InfoFragment
import pl.jakubneukirch.wikiapp.main.MainActivity
import pl.jakubneukirch.wikiapp.page.PageActivity
import pl.jakubneukirch.wikiapp.random.RandomFragment

@Subcomponent(modules = [AcitivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: PageActivity)
    fun inject(fragment: InfoFragment)
    fun inject(fragment: RandomFragment)
}