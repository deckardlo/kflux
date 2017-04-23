package com.workco.kflux.karnot.callbacks

import com.workco.kflux.karnot.actions.AppAction
import com.workco.kflux.karnot.events.AppChangeEvent
import io.reactivex.functions.Consumer

class AppCallback {
    var onAction: Consumer<AppAction>? = null
    var onEmittChange: Consumer<AppChangeEvent>? = null
}