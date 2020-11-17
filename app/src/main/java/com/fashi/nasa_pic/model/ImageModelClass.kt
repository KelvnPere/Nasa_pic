package com.fashi.nasa_pic.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ImageModelClass(val title: String, val image:String, val explanation: String, val date:String) :
    Parcelable
