package edu.ucne.antillasaquadexapp.data.remote

import edu.ucne.antillasaquadexapp.data.remote.dto.EspecieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EspecieApi {
    @GET("api/Especies")
    suspend fun getEspecies(@Query("page") page: Int = 1): List<EspecieDto>

    @GET("api/Especies/{id}")
    suspend fun getEspecieById(@Path("id") id: Int): Response<EspecieDto>
}
