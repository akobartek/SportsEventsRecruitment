package pl.sokolowskibartlomiej.sportseventsrecruitment.di

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pl.sokolowskibartlomiej.sportseventsrecruitment.data.remote.DaznApi
import pl.sokolowskibartlomiej.sportseventsrecruitment.data.remote.network.CacheInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://us-central1-dazn-sandbox.cloudfunctions.net/"
private const val CACHE_CONTROL_HEADER = "Cache-Control"

private fun provideHttpClient(appContext: Context): OkHttpClient =
    OkHttpClient.Builder()
        .cache(Cache(File(appContext.cacheDir, "http-cache"), 7 * 1024 * 1024))
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .addNetworkInterceptor { chain ->
            val cacheControl = CacheControl.Builder()
                .maxAge(24, TimeUnit.HOURS)
                .build()
            chain.proceed(chain.request()).newBuilder()
                .header(CACHE_CONTROL_HEADER, cacheControl.toString())
                .build()
        }
        .addInterceptor(CacheInterceptor(appContext))
        .build()

private fun provideRetrofit(httpClient: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
            )
        )
        .build()

private inline fun <reified T> createUrlService(retrofit: Retrofit) =
    retrofit.create(T::class.java)

val networkModule = module {
    single { provideHttpClient(androidContext()) }
    single { provideRetrofit(get()) }
    single { createUrlService<DaznApi>(get()) }
}