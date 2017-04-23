package com.workco.kflux.example.store

import android.util.Log
import com.workco.kflux.karnot.actions.ActionType
import com.workco.kflux.karnot.actions.AppAction
import com.workco.kflux.karnot.dispatcher.AppDispatcher
import com.workco.kflux.karnot.events.AppChangeEvent
import com.workco.kflux.karnot.store.AppStore

class ExampleStore(dispatcher: AppDispatcher): AppStore(name = "Example", dispatcher = dispatcher) {
    private val TAG: String = "ExampleStore"

    private var _storeChangeEvents: ArrayList<AppChangeEvent>? = ArrayList()

    override fun reduce(action: AppAction?) {
        Log.d(TAG, "reduce action type: " + action?.getActionType())
        when(action?.getActionType()) {
            ActionType.APP_BUTTON_CLICKED -> {
                Log.d(TAG, "create and and emit button clicked")
                createExampleClickEvent(action)
                emitStoreChange()
            }
            else -> { Log.d(TAG, "else block") }

        }
    }

    override fun changeEvent(): AppChangeEvent? {
        return _storeChangeEvents?.last()
    }

    fun createExampleClickEvent(action: AppAction?): Unit {
        Log.d(TAG, "create an example click event")
        val data: String? = action?.getActionData() as String
        val event: ExampleChangeEvent = ExampleChangeEvent(data)

        _storeChangeEvents?.add(event)
    }

    fun getStoreEvents(): ArrayList<AppChangeEvent>? {
        return _storeChangeEvents
    }

    private class ExampleChangeEvent(data: String?): AppChangeEvent {
        private var _data: String? = null

        init {
            _data = data
        }

        override fun eventType(): String {
            return "EXAMPLE_EVENT_TYPE"
        }

        override fun eventData(): Any? {
            return _data
        }
    }
}