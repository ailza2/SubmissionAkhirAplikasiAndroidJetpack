package com.dicoding.submissionakhiraplikasiandroidjetpack.data

import com.dicoding.submissionakhiraplikasiandroidjetpack.model.FruitDataSource
import com.dicoding.submissionakhiraplikasiandroidjetpack.model.OrderFruit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FruitRepository {

    private val orderFruit = mutableListOf<OrderFruit>()

    init {
        if (orderFruit.isEmpty()) {
            FruitDataSource.dummyFruit.forEach {
                orderFruit.add(OrderFruit(it, 0))
            }
        }
    }

    fun getAllFruit(): Flow<List<OrderFruit>> {
        return flowOf(orderFruit)
    }

    fun getOrderFruitById(fruitId: Long): OrderFruit {
        return orderFruit.first {
            it.fruit.id == fruitId
        }
    }

    fun updateOrderFruit(fruitId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderFruit.indexOfFirst { it.fruit.id == fruitId }
        val result = if (index >= 0) {
            val orderFruitItem = orderFruit[index]
            orderFruit[index] = OrderFruit(orderFruitItem.fruit, newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderFruit(): Flow<List<OrderFruit>> {
        return getAllFruit()
            .map { orderFruit ->
                orderFruit.filter { orderFruit ->
                    orderFruit.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: FruitRepository? = null

        fun getInstance(): FruitRepository =
            instance ?: synchronized(this) {
                FruitRepository().apply {
                    instance = this
                }
            }
    }
}
