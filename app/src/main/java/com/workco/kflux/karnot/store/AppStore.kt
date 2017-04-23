package com.workco.kflux.karnot.store

import android.util.Log
import com.workco.kflux.karnot.actions.AppAction
import com.workco.kflux.karnot.callbacks.AppCallback
import com.workco.kflux.karnot.dispatcher.AppDispatcher
import com.workco.kflux.karnot.events.AppChangeEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject

abstract class AppStore(name: String, dispatcher: AppDispatcher) {
    private val TAG: String = "AppStore"

    protected var _dispatcher: AppDispatcher? = null
    protected var _storeName: String? = null
    protected var _disposableRegister: CompositeDisposable? = null

    protected var _storeChangeEmitter: BehaviorSubject<AppChangeEvent>? = BehaviorSubject.create()

    init {
        _dispatcher = dispatcher
        _storeName = name

        val disposable: Disposable? = _dispatcher?.register(provideStoreDispatcherCallbacks())
        _disposableRegister?.add(disposable)
    }

    protected fun emitStoreChange(): Unit {
        val event: AppChangeEvent? = changeEvent()

        Log.d(TAG, "emit store change event: " + event?.eventType())

        _storeChangeEmitter?.onNext(event)
    }

    fun provideStoreDispatcherCallbacks(): AppCallback {
        val callback: AppCallback = AppCallback()

        callback.onAction = Consumer { reduce(it) }

        return callback
    }

    fun addListener(listener: AppCallback): Disposable? {
        val disposable: Disposable? = _storeChangeEmitter?.subscribe(listener.onEmittChange)
        _disposableRegister?.add(disposable)
        return disposable
    }

    fun clearDisposables(): Unit {
        _disposableRegister?.clear()
    }

    /**
     * @param {AppAction}
     *
     * Might be called by provideStoreCallback to reduce the application states to valid one
     */
    abstract fun reduce(action: AppAction?): Unit

    abstract fun changeEvent(): AppChangeEvent?
}