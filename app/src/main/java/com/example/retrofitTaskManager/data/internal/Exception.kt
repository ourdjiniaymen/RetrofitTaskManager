package com.example.retrofitTaskManager.data.internal

import java.io.IOException


class NoConnectivityException: IOException()
class GetDataFromApiException(message : String): IOException(message)