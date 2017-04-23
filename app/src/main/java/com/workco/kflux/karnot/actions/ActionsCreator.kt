package com.workco.kflux.karnot.actions

import android.util.Log
import com.workco.kflux.karnot.dispatcher.AppDispatcher

abstract class ActionsCreator(dispatcher: AppDispatcher) {
    private val TAG: String = "ActionsCreator"

    protected var _dispatcher: AppDispatcher? = null

    init {
        _dispatcher = dispatcher
    }

    fun createAction(actionType: ActionType): Unit {
        Log.d(TAG, "create action: " + actionType)
        performCreateActionFromType(actionType)
    }

    abstract protected fun performCreateActionFromType(type: ActionType): Unit
}