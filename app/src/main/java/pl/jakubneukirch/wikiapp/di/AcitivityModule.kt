package pl.jakubneukirch.wikiapp.di

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AcitivityModule(private val activity: Activity) {
    @Provides
    @ActivityContext
    fun providesContext(): Context = activity
}