package com.workco.kflux.karnot.actions

import com.workco.kflux.model.AppInit

class ActionAppInit: AppAction(type = ActionType.INIT) {
    override fun getActionData(): Any? {
        return AppInit()
    }

    override fun isActionValid(): Boolean {
        return false
    }

    override fun getActionType(): ActionType {
        return _actionType!!
    }
}