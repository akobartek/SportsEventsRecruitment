package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pl.sokolowskibartlomiej.sportseventsrecruitment.di.mainModule
import pl.sokolowskibartlomiej.sportseventsrecruitment.di.networkModule

class EventsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@EventsApplication)
            modules(
                networkModule,
                mainModule,
            )
        }
    }
}