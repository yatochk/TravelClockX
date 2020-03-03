package com.yatochk.secure.travelclockx.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

    @Provides
    fun provideContext(): Context =
        app

}