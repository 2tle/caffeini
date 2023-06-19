package io.twotle.uimakercompose.repository.backend

import io.twotle.uimakercompose.model.AddCaffeine
import io.twotle.uimakercompose.model.CaffeineList
import io.twotle.uimakercompose.model.MsgResponse
import io.twotle.uimakercompose.model.Token
import io.twotle.uimakercompose.model.UserCaffeine
import io.twotle.uimakercompose.model.UserSignIn
import io.twotle.uimakercompose.model.UserSignUp
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface IBackendService {
    @POST("auth/local")
    suspend fun signIn(@Body userSignIn: UserSignIn) : Response<Token>

    @POST("auth/new")
    suspend fun signUp(@Body userSignUp: UserSignUp) : Response<MsgResponse>

    @POST("caffeine")
    suspend fun addCaffeineDrink(@Header("Authorization") token: String,@Body body: AddCaffeine) : Response<ResponseBody>

    @GET("caffeine/today")
    suspend fun getTodayCaffeine(@Header("Authorization") token: String) : Response<CaffeineList>

    @GET("caffeine/recommend")
    suspend fun getRecommend(@Header("Authorization") token: String) : Response<UserCaffeine>
}
