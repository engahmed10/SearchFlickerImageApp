package com.example.searchflickrapp.searchFlicker.data.response

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@SuppressLint("ParcelCreator")
@VersionedParcelize
data class Item(
    val author: String?,
    val author_id: String?,
    val date_taken: String?,
    val description: String?,
    val link: String?,
    val media: Media?,
    val published: String?,
    val tags: String?,
    val title: String?
):Parcelable {
    override fun describeContents(): Int {
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
    }
}