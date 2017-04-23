package com.workco.kflux

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.workco.kflux.example.ExampleActionCreator
import com.workco.kflux.example.store.ExampleStore
import com.workco.kflux.karnot.actions.ActionType
import com.workco.kflux.karnot.actions.ActionsCreator
import com.workco.kflux.karnot.dispatcher.AppDispatcher
import com.workco.kflux.karnot.events.AppChangeEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    private var _exampleStore: ExampleStore? = null
    private val _exampleDispatcher: AppDispatcher = AppDispatcher()
    private var _exampleActionsCreator: ActionsCreator? = null

    init {
        _exampleActionsCreator = ExampleActionCreator(_exampleDispatcher)

        _exampleStore = ExampleStore(_exampleDispatcher)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_action_hello?.onClick {
            Log.d(TAG, "create action button clicked")
            _exampleActionsCreator?.createAction(ActionType.APP_BUTTON_CLICKED)
        }

        _exampleStore?.onChange {
            Log.d(TAG, "update ui to: " + it.eventType())
            updateUi(it.eventData())
        }
    }

    private fun updateUi(data: Any?): Unit {
        Log.d(TAG, "update ui to: " + data)
        val eventData: String = data as String
        val events: ArrayList<AppChangeEvent>? = _exampleStore?.getStoreEvents()
        text_kotlin_flux?.text = eventData + " - " + events?.size
    }
}
