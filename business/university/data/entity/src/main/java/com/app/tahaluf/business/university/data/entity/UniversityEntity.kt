package com.app.tahaluf.business.university.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "university_entity")
data class UniversityEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("primary_key_id")
    val id: Int = 0,
    @SerializedName("country")
    val country: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("state-province")
    val stateProvince: String?,
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String,
    @SerializedName("web_pages")
    val webPages: List<String>,
    @SerializedName("domains")
    val domains: List<String>,
)
