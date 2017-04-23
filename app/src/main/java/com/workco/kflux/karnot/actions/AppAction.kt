package com.workco.kflux.karnot.actions

abstract class AppAction(type: ActionType) {
    protected var _actionType: ActionType? = type

    abstract fun getActionType(): ActionType
    abstract fun getActionData(): Any?
    abstract fun isActionValid(): Boolean
}