package com.workco.kflux.karnot.dispatcher

import android.util.Log
import com.workco.kflux.karnot.actions.AppAction
import com.workco.kflux.karnot.callbacks.AppCallback
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

class AppDispatcher {
    private val TAG: String = "AppDispatcher"

    private val _actionDispatcher: BehaviorSubject<AppAction>? = BehaviorSubject.create()

    private val _compositeDisposable: CompositeDisposable? = CompositeDisposable()

    fun dispatch(action: AppAction?) : Unit {
        Log.d(TAG, "dispatch action: " + action)
        _startDispatching(action)
    }

    fun register(callback: AppCallback?): Disposable? {
        Log.d(TAG, "registering store callbacks")
        val disposable: Disposable? = _actionDispatcher?.subscribe(callback?.onAction)
        _compositeDisposable?.add(disposable)
        return disposable
    }

    fun unregister(disposable: Disposable): Unit {
        _compositeDisposable?.remove(disposable)
    }

    fun unregisterAll(): Unit {
        _compositeDisposable?.dispose()
        _compositeDisposable?.clear()
    }

    private fun _startDispatching(action: AppAction?): Unit {
        Log.d(TAG, "Start dispatching action: " + action?.getActionType())

        _actionDispatcher?.onNext(action)
    }
}
