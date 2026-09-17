package com.example.mobileappclient.use_cases.save_onboarding

import com.example.mobileappclient.Data.Repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {

    //operator to call the invoke fun without calling it explicitly
    suspend operator fun invoke(completed : Boolean){
        repository.saveOnBoardingState(completed = completed)
    }

}