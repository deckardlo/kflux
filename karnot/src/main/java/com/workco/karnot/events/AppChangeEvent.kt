package com.workco.karnot.events

interface AppChangeEvent {
    fun eventType(): String
    fun eventData(): Any?
}