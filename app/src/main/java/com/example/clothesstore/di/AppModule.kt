package com.example.clothesstore.di

import com.example.clothesstore.data.remote.api.ProductApi
import com.example.clothesstore.data.remote.repository.ProductRepositoryImp
import com.example.clothesstore.data.remote.repository.RemoteProductListService
import com.example.clothesstore.domain.repository.ProductRepository
import com.example.clothesstore.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideProductListApi(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideProductRepository(
        service: RemoteProductListService
    ): ProductRepository =
        ProductRepositoryImp(service)

}