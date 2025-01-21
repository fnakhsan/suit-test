package com.akhsan.suittest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.akhsan.suittest.domain.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    fun getName() = useCase.getName().asLiveData(Dispatchers.IO)
}