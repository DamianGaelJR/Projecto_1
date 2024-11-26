package com.example.project1.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ServiceModel(
    var id: Int = 0,
    var name: String = "",
    var username: String = "",
    var password: String = "",
    var description: String = "",
    var imageURL: String? = null
)

@Entity
data class ServiceEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image_url") val imageURL: String?
)

fun ServiceModel.toServiceEntity(): ServiceEntity {
    return ServiceEntity(
        id = this.id,
        name = this.name,
        username = this.username,
        password = this.password,
        description = this.description,
        imageURL = this.imageURL
    )
}

fun List<ServiceModel>.toServiceEntityList(): List<ServiceEntity> {
    return this.map { it.toServiceEntity() }
}