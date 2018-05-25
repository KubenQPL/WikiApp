package pl.jakubneukirch.wikiapp.di

import android.app.Application
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.data.WikiApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val app: Application) {
    @Provides
    @AppContext
    fun providesContext() = app.applicationContext

    @Provides
    fun providesResources() = app.resources

    @Provides
    fun providesRetrofit(resources: Resources): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        return Retrofit.Builder()
                .baseUrl(resources.getString(R.string.base_url))
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun providesWikiApi(retrofit: Retrofit): WikiApi = retrofit.create(WikiApi::class.java)
}