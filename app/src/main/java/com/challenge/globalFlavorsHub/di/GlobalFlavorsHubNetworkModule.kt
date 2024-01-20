package com.challenge.globalFlavorsHub.di

import com.challenge.globalFlavorsHub.BuildConfig
import com.challenge.globalFlavorsHub.data.dataSources.GlobalFlavorsHubApiClient
import com.challenge.globalFlavorsHub.ui.Constants
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GlobalFlavorsHubNetworkModule {

    @Provides
    @Singleton
    fun provideProfilerInterceptor(): OkHttpProfilerInterceptor = OkHttpProfilerInterceptor()

    @Provides
    @Singleton
    @RestClient
    fun provideOkHttpClient(
        profilerInterceptor: OkHttpProfilerInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(Constants.TIME_OUT_CONNECTION, TimeUnit.SECONDS)
        .readTimeout(Constants.TIME_OUT_CONNECTION, TimeUnit.SECONDS)
        .writeTimeout(Constants.TIME_OUT_CONNECTION, TimeUnit.SECONDS)
        .addInterceptor(profilerInterceptor)
        .build()

    @Singleton
    @Provides
    @Named("GlobalFlavorsHubRetrofitInstance")
    fun provideGlobalFlavorsHubRetrofit(@RestClient okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideGlobalFlavorsHubApiClient(@Named("GlobalFlavorsHubRetrofitInstance") retrofit: Retrofit): GlobalFlavorsHubApiClient {
        return retrofit.create(GlobalFlavorsHubApiClient::class.java)
    }
}
