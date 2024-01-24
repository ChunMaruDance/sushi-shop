package com.chunmaru.sushishop.data.hilt

import android.content.Context
import com.chunmaru.sushishop.data.api.ServiceApi
import com.chunmaru.sushishop.data.api.ServiceController
import com.chunmaru.sushishop.data.storage.DataStoreManager
import com.chunmaru.sushishop.presentation.navigation.NavigationStackController
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    private const val BASE_URL = "http://192.168.1.4:8080"

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providesServiceApi(retrofit: Retrofit): ServiceApi = retrofit.create(ServiceApi::class.java)


    @Singleton
    @Provides
    fun providesDataStoreManager(@ApplicationContext context: Context)
            : DataStoreManager = DataStoreManager(context)

    @Singleton
    @Provides
    fun providesNavigationStackController(): NavigationStackController = NavigationStackController()

    @Singleton
    @Provides
    fun provideServiceController(serviceApi: ServiceApi): ServiceController =
        ServiceController(serviceApi)


}