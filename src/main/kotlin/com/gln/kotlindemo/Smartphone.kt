package com.gln.kotlindemo

import javax.persistence.*

@Entity
@Table(name = "smartphone")
data class Smartphone(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
                      val name:String, val brand:String, val os:OsType, val price:Double? = 0.0 ) {
}

enum class OsType {
    ANDROID, IOS
}