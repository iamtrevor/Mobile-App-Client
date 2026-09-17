package com.example.mobileappclient.use_cases.read_onboarding

import com.example.mobileappclient.Data.Repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {

    //operator to call the invoke fun without calling it explicitly
    operator fun invoke() : Flow<Boolean>{
        return repository.readingOnBoardingState()
    }

}