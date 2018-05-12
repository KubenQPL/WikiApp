package pl.jakubneukirch.wikiapp.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getAcitivityComponent(module: AcitivityModule): ActivityComponent
}