package com.yudi.test2.api.models.other

/**
 * @author Yudi Rahmat
 */

data class Errors(val errors: ArrayList<String>) {
    override fun toString(): String {
        return errors.toString()
    }
}