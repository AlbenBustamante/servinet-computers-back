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
     * @param param the param.
     * @return the object type.
     */
    R call(P param);
}
