package com.dicoding.submissionakhiraplikasiandroidjetpack.di

import com.dicoding.submissionakhiraplikasiandroidjetpack.data.FruitRepository

object Injection {
    fun provideRepository(): FruitRepository {
        return FruitRepository.getInstance()
    }
}