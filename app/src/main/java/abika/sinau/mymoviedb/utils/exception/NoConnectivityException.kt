package abika.sinau.mymoviedb.utils.exception

import java.io.IOException

class NoConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String
        get() = "Koneksi Internet Anda Terputus"
    // You can send any message whatever you want from here.
}