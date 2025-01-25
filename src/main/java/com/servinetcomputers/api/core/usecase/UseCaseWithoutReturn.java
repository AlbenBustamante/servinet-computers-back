package com.servinetcomputers.api.core.usecase;

/**
 * Generic use case without return type.
 *
 * @param <P> the param type
 */
public interface UseCaseWithoutReturn<P> {
    /**
     * Execute the use case.
     *
     * @param param the param.
     */
    void call(P param);
}
