package com.example.goodfood.core.data.repository


import com.example.goodfood.core.data.source.local.IRestaurantDataSource
import com.example.goodfood.core.domain.model.Restaurant
import com.example.goodfood.core.domain.repository.IRestaurantRepository
import com.example.goodfood.core.helper.InitialDataSource
import com.example.goodfood.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RestaurantRepositoryImpl(private val restaurantDataSource: IRestaurantDataSource) :
    IRestaurantRepository {
    override fun getAllRestaurant(): Flow<List<Restaurant>> {
        return restaurantDataSource.getAllRestaurant().map { restaurant ->
            DataMapper.mapRestaurantEntitiesToDomain(restaurant)
        }
    }

    override suspend fun isListRestaurantEmpty(): Boolean {
        return restaurantDataSource.isRestaurantEmpty()
    }

    override suspend fun insertAllRestaurant() {
        restaurantDataSource.insertAllRestaurant(InitialDataSource.getRestaurants())
    }

    override suspend fun insert(restaurant: Restaurant) {
        restaurantDataSource.insert(DataMapper.mapRestaurantDomainToEntity(restaurant))
    }

    override suspend fun delete(restaurant: Restaurant) {
        restaurantDataSource.delete(DataMapper.mapRestaurantDomainToEntity(restaurant))
    }

    override suspend fun update(restaurant: Restaurant) {
        restaurantDataSource.update(DataMapper.mapRestaurantDomainToEntity(restaurant))
    }


}