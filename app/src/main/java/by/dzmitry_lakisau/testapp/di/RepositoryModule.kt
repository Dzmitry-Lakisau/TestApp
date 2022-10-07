package by.dzmitry_lakisau.testapp.di

import by.dzmitry_lakisau.testapp.data.repository.PostRepositoryImpl
import by.dzmitry_lakisau.testapp.data.repository.UserRepositoryImpl
import by.dzmitry_lakisau.testapp.domain.repository.PostRepository
import by.dzmitry_lakisau.testapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun providePostRepository(impl: PostRepositoryImpl): PostRepository
}