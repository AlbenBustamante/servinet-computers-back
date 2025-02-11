package com.servinetcomputers.api.core.usecase;

/**
 * Generic use case with two params without return.
 *
 * @param <P1> The FIRST Param type.
 * @param <P2> The SECOND Param type.
 */
public interface UseCaseBiParamWithoutReturn<P1, P2> {
    /**
     * Execute the use case.
     *
     * @param param1 the first param.
     * @param param2 the second param.
     */
    void call(P1 param1, P2 param2);
}
