package anatolii.pozniak.catsproject.retrofit

import com.google.gson.annotations.SerializedName


data class Name(val last: String? = null, val first: String? = null)
data class User(val name: Name? = null, val _id: String? = null)

data class CatsFact(
    val _id: String,
    val text: String?,
    val type: String?,
    val user: User?, val upvotes: String?
)


data class ApiResponse(@SerializedName("all") val all: List<Any> = mutableListOf())