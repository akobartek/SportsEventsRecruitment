package pl.sokolowskibartlomiej.sportseventsrecruitment.data.repository

import com.google.gson.GsonBuilder
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import pl.sokolowskibartlomiej.sportseventsrecruitment.data.remote.DaznApi
import pl.sokolowskibartlomiej.sportseventsrecruitment.data.remote.responses.EventRemote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class EventsRepositoryImplTest {

    private val timeout = 20.toDuration(DurationUnit.SECONDS)
    private val client = OkHttpClient.Builder().build()
    private val mockServer = MockWebServer()
    private val api = Retrofit.Builder()
        .baseUrl(mockServer.url("/"))
        .client(client)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
            )
        )
        .build()
        .create(DaznApi::class.java)
    private lateinit var repository: EventsRepositoryImpl
    private val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    private val sampleEventObj = EventRemote(
        id = "3",
        title = "Tottenham v Man City",
        subtitle = "UEFA Champions League",
        date = sdf.parse("2024-04-18T03:00:31.896Z")!!,
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310511685198_image-header_pDach_1554872450000.jpeg?alt=media&token=5524d719-261e-49e6-abf3-a74c30df3e27",
        videoUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
    )

    @Before
    fun setup() {
        repository = EventsRepositoryImpl(api)
    }

    @Test
    fun `test mapping events from response`() = runTest(timeout = timeout) {
        mockServer.enqueueResponse("getEvents-success.json", 200)
        val events = api.getEvents()
        Assert.assertEquals(sampleEventObj, events[2])
    }

    @Test
    fun `test loading events success path`() = runTest(timeout = timeout) {
        mockServer.enqueueResponse("getEvents-success.json", 200)
        val result = repository.loadEvents()
        Assert.assertTrue(result.isSuccess)
        val events = result.getOrElse { listOf() }
        Assert.assertEquals(16, events.size)
        Assert.assertEquals(sampleEventObj.toDomainObject(), events[2])
    }

    @Test
    fun `test loading events fail path`() = runTest(timeout = timeout) {
        mockServer.enqueue(MockResponse().setResponseCode(503))
        val result = repository.loadEvents()
        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun `test loading schedule success path`() = runTest(timeout = timeout) {
        mockServer.enqueueResponse("getSchedule-success.json", 200)
        val result = repository.loadSchedule()
        Assert.assertTrue(result.isSuccess)
        val schedule = result.getOrElse { listOf() }
        Assert.assertEquals(16, schedule.size)
        val event = schedule[2]
        Assert.assertEquals("Mavericks @ Grizzlies", event.title)
        Assert.assertEquals("NBA", event.subtitle)
        Assert.assertEquals(sdf.parse("2024-04-19T00:00:31.164Z"), event.date)
        Assert.assertTrue(event.imageUrl.isNotEmpty())
        Assert.assertNull(event.videoUrl)
    }

    @Test
    fun `test loading schedule fail path`() = runTest(timeout = timeout) {
        mockServer.enqueue(MockResponse().setResponseCode(503))
        val result = repository.loadSchedule()
        Assert.assertTrue(result.isFailure)
    }

    private fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-responses/$fileName")
        val source = inputStream?.let { inputStream.source().buffer() }
        source?.let {
            enqueue(
                MockResponse()
                    .setResponseCode(code)
                    .setBody(source.readString(StandardCharsets.UTF_8))
            )
        }
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }
}