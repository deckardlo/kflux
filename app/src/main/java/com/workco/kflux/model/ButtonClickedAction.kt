package com.workco.kflux.model

import com.workco.karnot.actions.ActionType
import com.workco.karnot.actions.AppAction

class ButtonClickedAction: AppAction(type = ActionType.APP_BUTTON_CLICKED) {

    private var data: Any? = null

    override fun getActionData(): Any? {
        val message: String = "HelloButton clicked: "
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