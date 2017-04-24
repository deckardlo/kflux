package com.workco.kflux.ui

import android.widget.Button
import com.workco.kflux.MainActivity
import com.workco.kflux.R
import org.jetbrains.anko.*

class MainActivityUI: AnkoComponent<MainActivity> {
    private val TAG: String = "MainActivityUI"

    lateinit var createButton: Button

    override fun createView(ui: AnkoContext<MainActivity>) = with (ui) {
        verticalLayout {
            id = R.id.layout_vertical_layout

            createButton = button("Create Action Clicked") {
                id = R.id.btn_create_action
            }

            textView("Flux with anko") {
                id = R.id.text_result_action_view
            }
        }
    }
}