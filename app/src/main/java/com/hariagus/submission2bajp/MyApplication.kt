package com.hariagus.submission2bajp

import android.app.Application
import com.hariagus.submission2bajp.di.appModule
import com.hariagus.submission2bajp.di.repositoryModule
import com.hariagus.submission2bajp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}