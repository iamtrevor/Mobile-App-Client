package com.example.mobileappclient.DI

import android.content.Context
import com.example.mobileappclient.Data.Repository.DataStoreOperationsImpl
import com.example.mobileappclient.Data.Repository.Repository
import com.example.mobileappclient.repository.DataStoreOperations
import com.example.mobileappclient.use_cases.UseCases
import com.example.mobileappclient.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.mobileappclient.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.example.mobileappclient.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.mobileappclient.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.example.mobileappclient.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ) : DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }


    //used to provide the  [ repository ] to both the use cases
    @Provides
    @Singleton
    fun provideUseCases(repository: Repository) : UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository)
        )
    }

}