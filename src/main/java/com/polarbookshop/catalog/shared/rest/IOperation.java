package com.polarbookshop.catalog.shared.rest;

public interface IOperation<I extends IConsumerRequest, O extends IConsumerResponse> {
    /**
     * handle method for the operation.*
     * @param consumerRequest consumer Request PoJo.
     * @return Response consumer Response PoJo
     */
    O handle(I consumerRequest);
}
