package app.plantdiary.individualassignment304832.service

import app.plantdiary.individualassignment304832.RetrofitClientInstance
import app.plantdiary.individualassignment304832.dao.ICountryDAO
import app.plantdiary.individualassignment304832.dto.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class CountryService{
    suspend fun fetchCountries(): List<Country>? {
        return withContext(Dispatchers.IO) {
            val retrofit = RetrofitClientInstance.retrofitInstance?.create(ICountryDAO::class.java)
            val countries = async { retrofit?.fetchCountries() }
            return@withContext countries.await()?.awaitResponse()?.body()
        }
    }
}