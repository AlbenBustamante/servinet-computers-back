package com.servinetcomputers.api.domain;

/**
 * Generic use case.
 *
 * @param <R> Return type.
 * @param <P> Param type.
 */
public interface UseCase<R, P> {
    /**
     * Execute the use case.
     *
     * @param param the params.
     * @return the return object.
     */
    R call(P param);
}
