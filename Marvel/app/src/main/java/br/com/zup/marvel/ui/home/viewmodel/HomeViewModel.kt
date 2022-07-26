package br.com.zup.marvel.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.marvel.data.model.Marvel
import br.com.zup.marvel.data.repository.MarvelRepository
import br.com.zup.marvel.domain.repository.AuthenticationRepository

class HomeViewModel : ViewModel() {
    private val marvelRepository = MarvelRepository()

    private val authenticationRepository = AuthenticationRepository()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage


    private var _marvelListState = MutableLiveData<List<Marvel>>()
    val marvelListState: LiveData<List<Marvel>> = _marvelListState

    fun getListMarvel() {
       val listMarvel =  marvelRepository.getMarvelList()
        _marvelListState.value = listMarvel
    }

    fun getUserName() = authenticationRepository.getNameUser()

    fun getUserEmail() = authenticationRepository.getEmailUser()

    fun logout() = authenticationRepository.logoutOut()
}