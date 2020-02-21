package com.navas.lbscan.core.extensions

inline fun <T> T.inside(block: T.()->Unit){
    block()
}

inline fun <T> Iterable<T>.hasValue(predicate: (T)->Boolean): T?{
    for(let: T in this){
        if(predicate(let)){
            return let
        }
    }
    return null
}

