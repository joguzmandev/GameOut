package org.softhk.gameout.game

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.softhk.gameout.model.Result
import org.softhk.gameout.utils.SharedPreferenceItem

class GameViewModel constructor(var repositoryAPI:GameRepository.GameRepositoryAPI,var sharedPreferences: SharedPreferences) : ViewModel() {


    private var _repositoryAPI:GameRepository.GameRepositoryAPI? = null
    private var _listOfGames = MutableLiveData<List<Result>>()


    init {
        _repositoryAPI = repositoryAPI
        _listOfGames = _repositoryAPI!!.getGamesMutableLiveData()
    }


    fun getGamesAPI(){
         var pageSize:Int? = null
        if(sharedPreferences.contains(SharedPreferenceItem.SHARED_PREFERECES_ELEMENT_TO_SHOW_RECYCLER)){
            pageSize = sharedPreferences.getInt(SharedPreferenceItem.SHARED_PREFERECES_ELEMENT_TO_SHOW_RECYCLER,SharedPreferenceItem.SHARED_PREFERENCES_SHOW_DEFAULT_ITEM_RECYCLERVIEW)
        }else{
            pageSize = SharedPreferenceItem.SHARED_PREFERENCES_SHOW_DEFAULT_ITEM_RECYCLERVIEW
        }

        _repositoryAPI!!.getGamesAPI(1,pageSize!!)

    }

    fun getGameLiveData():LiveData<List<Result>>{
        return this._listOfGames
    }



}