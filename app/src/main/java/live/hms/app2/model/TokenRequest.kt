package live.hms.app2.model

import com.google.gson.annotations.SerializedName

data class TokenRequest(
  @SerializedName("room_id") val roomId: String,
  @SerializedName("user_id") val userId: String,
  @SerializedName("role") val role: String
)