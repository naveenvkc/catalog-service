package com.polarbookshop.catalog.shared.rest;

import java.io.Serializable;

public interface IConsumerRequest extends Serializable {
    Boolean isEnableTransactionLog();
}
