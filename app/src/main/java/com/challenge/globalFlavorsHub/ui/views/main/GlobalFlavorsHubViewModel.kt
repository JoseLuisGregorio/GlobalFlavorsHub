package com.challenge.globalFlavorsHub.ui.views.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.globalFlavorsHub.utils.extensions.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GlobalFlavorsHubViewModel @Inject constructor() : ViewModel() {

    var currentScroll: Int = 0

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _toolbarTitle = MutableLiveData<String?>()
    val toolbarTitle = _toolbarTitle.asLiveData()

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing = _isRefreshing.asLiveData()

    var refreshRequest: (() -> Unit)? = null

    fun finishRefreshRequest() {
        _isRefreshing.value = false
    }

    fun isLoading(isLoading: Boolean) {
        if (isLoading != _isLoading.value) _isLoading.value = isLoading
    }
}
