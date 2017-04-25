package com.workco.kflux

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.workco.karnot.actions.ActionType
import com.workco.karnot.actions.ActionsCreator
import com.workco.karnot.dispatcher.AppDispatcher
import com.workco.karnot.events.AppChangeEvent
import com.workco.kflux.example.ExampleActionCreator
import com.workco.kflux.example.store.ExampleStore
import com.workco.kflux.ui.MainActivityUI
import org.jetbrains.anko.onClick
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    private var _exampleStore: ExampleStore? = null
    private val _exampleDispatcher: AppDispatcher = AppDispatcher()
    private var _exampleActionsCreator: ActionsCreator = ExampleActionCreator(_exampleDispatcher)

    init {
        _exampleStore = ExampleStore(_exampleDispatcher)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout: MainActivityUI = MainActivityUI()
        layout.setContentView(this)

        layout.createButton.onClick {  }

        val button = findViewById(R.id.btn_create_action) as Button
        button?.onClick {
            Log.d(TAG, "button create action clicked")
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
        val textview = findViewById(R.id.text_result_action_view) as TextView

        textview.text = eventData + events?.size
    }
}
