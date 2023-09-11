package com.example.statemachine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.statemachine.mocknetwork.StateRepository
import com.example.statemachine.mocknetwork.StateRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StateViewModel(private val repository: StateRepository = StateRepositoryImpl()): ViewModel() {
    private var stateList = ""

    val states: LiveData<String> get() = _states
    private val _states: MutableLiveData<String> = MutableLiveData("")

    fun getState() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getState().collect {
                stateList+=it
                _states.postValue(stateList)

                // State validation check
                if (stateList.length == 1 && stateList == "b") {
                    _states.postValue(stateList+ " FAILED")
                    stateList = ""
                }
                else if (stateList.length == 2) {
                    //aa cd de ee
                    when (stateList) {
                        "aa"-> {
                            _states.postValue(stateList+ " OK")
                            stateList = ""
                        }
                        "cd"-> {
                            _states.postValue(stateList+ " OK")
                            stateList = ""
                        }
                        "de"-> {
                            _states.postValue(stateList+ " OK")
                            stateList = ""
                        }
                        "ee"-> {
                            _states.postValue(stateList+ " OK")
                            stateList = ""
                        }

                        "ab"-> {

                        }

                        else -> {
                            _states.postValue(stateList+ " FAILED")
                            stateList = ""
                        }
                    }
                }
                else if (stateList.length == 3) {
                    stateList = if (stateList == "abc") {
                        _states.postValue(stateList+ " OK")
                        ""
                    } else {
                        _states.postValue(stateList+ " FAILED")
                        ""
                    }
                }
            }

        }
    }
}