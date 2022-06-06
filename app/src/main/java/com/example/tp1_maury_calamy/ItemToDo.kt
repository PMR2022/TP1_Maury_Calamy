package com.example.tp1_maury_calamy

data class ItemToDo(var description:String, var fait:Boolean=false){
    fun setFait(){
        this.fait = true
    }
}
