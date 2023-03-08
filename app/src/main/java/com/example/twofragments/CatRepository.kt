package com.example.twofragments

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatRepository {

    private val listCat: List<Cat> = listOf(
        Cat(1, "barsik"),
        Cat(2, "Murzik"),
        Cat(3, "barsik"),
        Cat(4, "Murzik"),
        Cat(5, "barsik"),
        Cat(6, "Murzik"),
        Cat(7, "barsik"),
        Cat(8, "Murzik"),
    )

    suspend fun getData(): Flow<Cat> {
        return flow {
            for (i in listCat) {
                delay(1000)
                emit(i)
            }
        }
    }
}