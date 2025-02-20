package com.fashi.nasa_pic.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NasaModelClass(val title: String, val image:String, val explanation: String, val date:String) :
    Parcelable
