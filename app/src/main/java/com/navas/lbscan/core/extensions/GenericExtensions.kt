package com.navas.lbscan.core.extensions

inline fun <T> T.inside(block: T.()->Unit){
    block()
}
