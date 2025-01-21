package com.servinetcomputers.api.domain;

/**
 * Generic use case without params.
 *
 * @param <R> the return type
 */
public interface UseCaseWithoutParam<R> {
    /**
     * Execute the use case.
     *
     * @return the return object.
     */
    R call();
}
