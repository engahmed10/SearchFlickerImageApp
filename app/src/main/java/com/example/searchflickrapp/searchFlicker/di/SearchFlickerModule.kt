package com.example.searchflickrapp.searchFlicker.di
            
import com.example.searchflickrapp.searchFlicker.data.api.SearchFlickerApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchFlickerModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): SearchFlickerApiService {
        return retrofit.create(SearchFlickerApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/feeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
}
