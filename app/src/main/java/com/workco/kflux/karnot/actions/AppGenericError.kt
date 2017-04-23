package com.workco.kflux.karnot.actions

import com.workco.kflux.model.GenericError

class AppGenericError: AppAction(type = ActionType.ERROR) {

    override fun getActionData(): Any? {
        return GenericError()
    }

    override fun isActionValid(): Boolean {
        return true
    }

    override fun getActionType(): ActionType {
        return _actionType!!
    }
}