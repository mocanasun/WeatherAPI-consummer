package com.example.wheaterapiconsummer

import android.content.Context
import com.example.wheaterapiconsummer.data.LocalRepository
import com.example.wheaterapiconsummer.data.LocalRepositoryImpl
import com.example.wheaterapiconsummer.data.WeatherApiRepository
import com.example.wheaterapiconsummer.data.WeatherApiRepositoryImpl
import com.example.wheaterapiconsummer.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideWeatherApiRepository(): WeatherApiRepository {
        return WeatherApiRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideLocalRepository(@ApplicationContext appContext: Context): LocalRepository {
        return LocalRepositoryImpl(appContext)
    }

    @Provides
    fun provideWeatherUseCase(remote: WeatherApiRepository, local: LocalRepository): WeatherUseCase {
        return WeatherUseCase(remote, local)
    }
}