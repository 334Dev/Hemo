package com.dev334.blood.util.retrofit;

import com.dev334.blood.model.ApiResponse;
import com.dev334.blood.model.Blood;
import com.dev334.blood.model.BloodBank;
import com.dev334.blood.model.GovApiResponse;
import com.dev334.blood.model.Schedule;
import com.dev334.blood.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //@Headers("auth_token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MWI3YTNkOGViMjYxYjdjMjJkNGYzNjAiLCJpYXQiOjE2Mzk0MjUxMDF9.iRKfrKeuH26HFyqVHnPSmLgQlFH2KAbSTFm5a2yP4g8")
    @GET("/api/data/users")
    Call<List<User>> getUsers();


    @POST("/api/user/register")
    Call<ApiResponse> registerUser(@Body User user);

    @POST("/api/user/login")
    Call<User> loginUser(@Body User user);

    @POST("/api/user/create")
    Call<ApiResponse> createUser(@Body User user);

    @GET("api/user/users/{id}")
    Call<User> getUser(@Path("id") String id);

    @GET("api/blood/req")
    Call<List<Blood>> getBloodReq(@Query("location") String location, @Query("blood") String blood);

    @POST("/api/blood/req")
    Call<ApiResponse> reqBlood(@Body Blood blood);

    @GET("/resource/fced6df9-a360-4e08-8ca0-f283fc74ce15?api-key=579b464db66ec23bdd00000127eaf05b3f0e45765d7131e141b41c0c&format=json&offset=0&limit=100")
    Call<GovApiResponse> getBloodBank(@Query(value="filters[__district]", encoded = true) String location);

    @POST("api/blood/schedule")
    Call<ApiResponse> schedule(@Body Schedule schedule);

    @GET("api/admin/schedule")
    Call<List<Schedule>> getSchedule(@Query("bank_id") String bank_id,@Query("pending") String pending);

    @PATCH("api/admin/schedule/approval")
    Call<ApiResponse> setApproval(@Query("id") String id,@Query("approval") String approval);
 }
