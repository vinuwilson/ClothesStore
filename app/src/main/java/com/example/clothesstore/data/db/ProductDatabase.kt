package com.example.clothesstore.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clothesstore.domain.model.BasketEntity
import com.example.clothesstore.domain.model.Product

@Database(entities = [Product::class, BasketEntity::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun wishListDao(): ProductDao
}