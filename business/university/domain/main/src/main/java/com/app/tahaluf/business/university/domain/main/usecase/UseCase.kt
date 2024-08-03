package com.app.tahaluf.business.university.domain.main.usecase

sealed interface UseCase {
    interface Suspending<R> : UseCase {
        suspend operator fun invoke(): R
    }

    interface SuspendingParameterized<T, R> : UseCase {
        suspend operator fun invoke(param: T): R
    }
}
