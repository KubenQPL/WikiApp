package pl.jakubneukirch.wikiapp.di

import android.app.Application
import android.content.res.Resources
import com.google.gson.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.data.WikiApi
import pl.jakubneukirch.wikiapp.data.model.api.page.PageObject
import pl.jakubneukirch.wikiapp.data.model.api.page.PageThumbnail
import pl.jakubneukirch.wikiapp.data.model.api.page.PagesQuery
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @AppContext
    fun providesContext() = app.applicationContext

    @Provides
    fun providesResources() = app.resources

    @Singleton
    @Provides
    fun providesGson(): Gson {
        val builder = GsonBuilder()
        val deserializer = object : JsonDeserializer<PagesQuery> {
            override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext): PagesQuery {
                val jsonObject = json.asJsonObject["pages"].asJsonObject
                val list = arrayListOf<PageObject>()
                var pageObject: PageObject

                var thumbnail: PageThumbnail?
                jsonObject.keySet().forEach {
                    if(jsonObject[it].asJsonObject["thumbnail"] != null){
                        thumbnail = context.deserialize(jsonObject[it].asJsonObject["thumbnail"].asJsonObject, PageThumbnail::class.java)
                    } else {
                        thumbnail = null
                    }
                    pageObject = PageObject(
                            jsonObject[it].asJsonObject["pageid"].asLong,
                            jsonObject[it].asJsonObject["title"].asString,
                            jsonObject[it].asJsonObject["extract"].asString,
                            thumbnail
                    )
                    list.add(pageObject)
                }
                return PagesQuery(list)
            }
        }
        builder.registerTypeAdapter(PagesQuery::class.java, deserializer)
        return builder.create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(resources: Resources, gson: Gson): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        return Retrofit.Builder()
                .baseUrl(resources.getString(R.string.base_url))
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    fun providesWikiApi(retrofit: Retrofit): WikiApi = retrofit.create(WikiApi::class.java)
}