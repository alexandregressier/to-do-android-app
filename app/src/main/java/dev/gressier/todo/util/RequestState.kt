package dev.gressier.todo.util

sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val value: T): RequestState<T>()
    object Empty : RequestState<Nothing>()
    data class Error(val t: Throwable): RequestState<Nothing>()
}