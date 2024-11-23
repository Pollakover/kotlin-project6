package com.example.kotlin_project6

import android.content.Context
import androidx.room.Room
import com.example.kotlin_project6.retrofit.api.MainApi
import com.example.kotlin_project6.retrofit.model.AppDatabase
import com.example.kotlin_project6.retrofit.model.QuotesDao
import com.example.kotlin_project6.retrofit.model.QuotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://dummyjson.com"

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "quotes.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideQuoteDao(db: AppDatabase): QuotesDao {
        return db.quoteDao()
    }

    @Singleton
    @Provides
    fun provideQuoteRepository(quoteDao: QuotesDao): QuotesRepository {
        return QuotesRepository(quoteDao)
    }
}
