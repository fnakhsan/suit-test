package com.akhsan.suittest.presentation

import androidx.lifecycle.ViewModel
import com.akhsan.suittest.domain.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    suspend fun saveName(name: String) = useCase.saveName(name)

}