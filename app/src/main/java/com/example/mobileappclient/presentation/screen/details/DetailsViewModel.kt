package com.example.mobileappclient.presentation.screen.details


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.use_cases.UseCases
import com.example.mobileappclient.utils.Constants.DETAILS_ARGUMENTS_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _selectedHero = MutableStateFlow<Hero?>(null)
    val selectedHero : StateFlow<Hero?> = _selectedHero.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(DETAILS_ARGUMENTS_KEY)
            _selectedHero.value = heroId?.let{useCases.getSelectedHeroUseCase(heroId = heroId)}
        }
    }

}