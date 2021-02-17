package com.yatochk.secure.travelclockx.di

import android.content.Context
import com.yatochk.secure.travelclockx.location.LocationLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class AppModule {

    @Provides
    @ViewModelScoped
    fun provideLocationLiveData(@ApplicationContext context: Context) = LocationLiveData(context)

}