package com.polarbookshop.catalog.shared.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public final class Markers {

    /**
     * Marker that can be used to mark the log message as potentially containing personally identifiable
     * information.
     * SIEMEvent Marker is to mark the log message as Siem  event and also add data specific to Siem Event.
     * <p>
     * This marker may be added in addition to any other marker, it's more like a tag than anything else
     */
    public static final Marker SENSITIVE = MarkerFactory.getMarker("SENSITIVE");

    public static final Marker SIEMEVENT = MarkerFactory.getMarker("SIEMEvent");

    private Markers() {
    }
}
