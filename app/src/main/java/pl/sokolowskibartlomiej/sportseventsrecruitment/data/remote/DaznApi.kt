package pl.sokolowskibartlomiej.sportseventsrecruitment.data.remote

import pl.sokolowskibartlomiej.sportseventsrecruitment.data.remote.responses.EventRemote
import retrofit2.http.GET

interface DaznApi {
    @GET("getEvents")
    suspend fun getEvents(): List<EventRemote>

    @GET("getSchedule")
    suspend fun getSchedule(): List<EventRemote>
}