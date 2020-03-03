package com.yatochk.secure.travelclockx.di

import androidx.lifecycle.ViewModel
import com.yatochk.secure.travelclockx.location.LocationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    internal abstract fun locationViewModel(viewModel: LocationViewModel): ViewModel

}