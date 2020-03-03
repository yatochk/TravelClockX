package com.yatochk.secure.travelclockx.di

import com.yatochk.secure.travelclockx.FullscreenActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fullscreenActivity: FullscreenActivity)

}