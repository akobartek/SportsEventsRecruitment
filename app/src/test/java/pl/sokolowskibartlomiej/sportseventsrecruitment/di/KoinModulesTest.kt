package pl.sokolowskibartlomiej.sportseventsrecruitment.di

import android.content.Context
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class KoinModulesTest: KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `verify Koin modules`() {
        checkKoinModules(listOf(networkModule, mainModule)) {
            withInstance<Context>()
        }
    }
}