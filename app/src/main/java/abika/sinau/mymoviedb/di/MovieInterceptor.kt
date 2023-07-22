package abika.sinau.mymoviedb.di

import abika.sinau.mymoviedb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class MovieInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val httpUrl = original.url.newBuilder()
        val newHttpUrl = httpUrl.addQueryParameter("api_key", BuildConfig.API_KEY).build()

        val request = original.newBuilder()
            .url(newHttpUrl)
            .build()

        return chain.proceed(request)
    }
}