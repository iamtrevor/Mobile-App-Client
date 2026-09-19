package com.example.mobileappclient.use_cases

import com.example.mobileappclient.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.mobileappclient.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.mobileappclient.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase
)
