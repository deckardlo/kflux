package com.workco.kflux.example

import android.util.Log
import com.workco.karnot.actions.ActionType
import com.workco.karnot.actions.ActionsCreator
import com.workco.karnot.actions.AppAction
import com.workco.karnot.dispatcher.AppDispatcher
import com.workco.kflux.model.ButtonClickedAction

class ExampleActionCreator(dispatcher: AppDispatcher): ActionsCreator(dispatcher = dispatcher) {
    private val TAG: String = "ExampleActionCreator"

    override fun performCreateActionFromType(type: ActionType) {
        Log.d(TAG, "performing create action form type: " + type)

        var action: AppAction? = null
        when (type) {
            ActionType.APP_BUTTON_CLICKED -> action = ButtonClickedAction()
            else -> { Log.d(TAG, "else block") }
        }

        _dispatcher?.dispatch(action)
    }
}