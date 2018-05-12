package pl.jakubneukirch.wikiapp.app

import android.app.Application
import pl.jakubneukirch.wikiapp.di.AppComponent
import pl.jakubneukirch.wikiapp.di.AppModule
import pl.jakubneukirch.wikiapp.di.DaggerAppComponent

class WikiApp: Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}