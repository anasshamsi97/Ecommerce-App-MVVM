package com.example.ecommerceapp.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ecommerceapp.network.Result
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import retrofit2.Response

open class BaseRepository {

    protected fun <T> getResult(call: suspend () -> Response<T>): LiveData<Result<T>> =
        liveData(Dispatchers.IO) {
            try {
                emit(Result.Loading)
                val response = call()
                emit(gatherResults(response))
            } catch (e: Exception) {
            }
        }

    private fun <T> gatherResults(response: Response<T>): Result<T> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null)
                Result.Success(
                    body
                )
            else Result.Error("NO RESULT FOUND")
        } else Result.Error(
            "${response.code()} ${
                JSONObject(response.errorBody()?.string())["message"]
            }"
        )
    }
}
