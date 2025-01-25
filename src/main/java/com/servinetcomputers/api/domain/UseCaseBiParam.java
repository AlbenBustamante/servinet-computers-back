package com.servinetcomputers.api.domain;

/**
 * Generic use case that requires two params.
 *
 * @param <R>  The Return type.
 * @param <P1> The FIRST Param type.
 * @param <P2> The SECOND Param type.
 */
public interface UseCaseBiParam<R, P1, P2> {
    /**
     * Execute the use case.
     *
     * @param param1 the first param.
     * @param param2 the second param.
     * @return the object type.
     */
    R call(P1 param1, P2 param2);
}
