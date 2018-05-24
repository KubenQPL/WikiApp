package pl.jakubneukirch.wikiapp.di

import android.app.Application
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.data.WikiApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @AppContext
    fun providesContext() = app.applicationContext

    @Provides
    fun providesResources() = app.resources

    @Provides
    fun providesRetrofit(resources: Resources): Retrofit {
        return Retrofit.Builder()
                .baseUrl(resources.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun providesWikiApi(retrofit: Retrofit): WikiApi = retrofit.create(WikiApi::class.java)
}