package com.nizamsetiawan.app.fasttrackedu.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast

fun Context.downloadPdf(fullUrl: String, name: String) {
    val request = DownloadManager.Request(Uri.parse(fullUrl))
        .setTitle(name) // Menampilkan judul dengan menggunakan nama event
        .setDescription("Downloading guide book...") // Deskripsi di notification bar
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setAllowedOverMetered(true) // Izinkan download dengan data seluler
        .setAllowedOverRoaming(true) // Izinkan download saat roaming
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "GuideBook_${name.replace(" ", "_")}.pdf") // Lokasi penyimpanan dengan nama file dinamis

    val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)

    Toast.makeText(this, "Download started...", Toast.LENGTH_SHORT).show()
}
