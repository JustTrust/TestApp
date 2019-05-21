package com.test.testapp.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.test.testapp.network.NetworkProvider
import com.test.testapp.network.models.Item
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by a.belichenko on 20.05.2019.
 * mail: a.belichenko@gmail.com
 */
class MainActivityVM : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val list: MutableLiveData<List<Item>> = MutableLiveData()

    fun getListOfStrings(): LiveData<List<Item>> = list

    fun updateList() {
        disposable.add(
            NetworkProvider.instance.getApi().getSitesList()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    list.postValue(it?.items ?: emptyList())
                },
                    { Timber.e(it) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}