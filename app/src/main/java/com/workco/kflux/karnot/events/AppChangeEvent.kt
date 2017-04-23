package com.workco.kflux.karnot.events

interface AppChangeEvent {
    fun eventType(): String
    fun eventData(): Any?
}