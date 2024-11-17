package com.example.clothesstore.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clothesstore.domain.model.BasketEntity
import com.example.clothesstore.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    //Wishlist table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(product: Product)

    @Query("SELECT * from wishlist_tbl")
    fun getFavourites(): Flow<List<Product>>

    @Delete
    suspend fun deleteFavourite(product: Product)


    //Basket table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToBasket(product: BasketEntity)

    @Query("SELECT * from basket_tbl")
    fun getAllItems(): Flow<List<BasketEntity>>

    @Delete
    suspend fun deleteItem(product: BasketEntity)

}