package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.MainModuleInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
        }
        MainModuleInitializer.init()
    }

}