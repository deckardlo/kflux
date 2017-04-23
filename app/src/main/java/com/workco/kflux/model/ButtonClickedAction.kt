package com.workco.kflux.model

import com.workco.kflux.karnot.actions.ActionType
import com.workco.kflux.karnot.actions.AppAction

class ButtonClickedAction: AppAction(type = ActionType.APP_BUTTON_CLICKED) {

    private var data: Any? = null
    private var clicks: Int = 0

    override fun getActionData(): Any? {
        val message: String = "HelloButton clicked: " + ++clicks
        data = message

        return data
    }

    override fun isActionValid(): Boolean {
        return data != null
    }

    override fun getActionType(): ActionType {
        return _actionType!!
    }
}