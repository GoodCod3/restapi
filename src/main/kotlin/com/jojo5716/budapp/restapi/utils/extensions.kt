package com.jojo5716.budapp.restapi.utils

fun <E> MutableSet<E>.update(element: E): Boolean {
    return this.remove(element) && this.add(element)
}