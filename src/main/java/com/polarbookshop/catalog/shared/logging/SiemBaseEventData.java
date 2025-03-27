package com.polarbookshop.catalog.shared.logging;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.Date;

@Setter
public class SiemBaseEventData {

    /**
     * Application name of the client application.
     */
    private String appName;

    /**
     * Distinctive ten (10) characters value representing a single event record; formatted alpha-numerically(ex. A1728C27F0).
     */
    private String  uniqueIdentifier;

    /**
     * Full date and time of when the event was recorded in the log file including relevant time-zone information if not in
     * Coordinated Universal Time (UTC); formatted as either:
     * o DD/MMM/YYYY hh:mm:ss -tttt
     * o YYYY-MM-DD hh:mm:ss -tttt.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss-SSSS z", timezone = "America/New_York")
    private Date logTimeStamp;

    /**
     * Identification of the type of event log record being generated using the following qualifiers:
     * o (0A) Security Log
     * o (0B) Audit Log
     * o (0C) Error Log
     * o (0D) Access Log
     * o (0E) Transaction Log
     * o (0F) External Log
     * o (0G) Infrastructure Log.
     */
    private String eventLogClassification;

    /**
     * Full date and time of when the event occurred including relevant time-zone information if not in Coordinated Universal Time (UTC); formatted as either:
     * o DD/MMM/YYYY hh:mm:ss -tttt
     * o YYYY-MM-DD hh:mm:ss -tttt.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss-SSSS z", timezone = "America/New_York")
    private Date eventTimestamp;

    /**
     * Ranking the event specific to the type of record entry created using the following qualifiers:
     * o (00) Trace: Information useful to facilitate debug activities
     * o (10) Debug: Information useful to facilitate diagnostics
     * o (20) Information: General information that will not cause problems
     * o (30) Warning: Issues that may cause problems if not addressed
     * o (40) Error: Critical warnings most likely to cause impact
     * o (50) Fatal: Critical errors resulting in operational impact and data loss.
     */
    private String eventType;

    /**
     * Ranking the event specific to its potential impact using the following qualifiers:
     * o (00) Low: response is required using standard procedures as time permits
     * o (10) Medium: response is required using standard procedures within normal operating structures
     * o (20) High: immediate response is required to assess the situation.
     */
    private String eventPriority;

    /**
     * Ranking the event specific to the type of interaction and communication occurring between systems and users using the following qualifiers:
     * o (00) Initiated: activities associated with the event were started
     * o (10) Suspended: activities associated with the event were temporarily stopped
     * o (20) Interrupted: activities associated with the event have been intervened
     * o (30) Recycled: activities associated with the event were restarted
     * o (40) Terminated: activities associated with the event were terminated
     * o (50) Assigned: characteristic or property was designated to the system or user
     * o (60) Released: characteristic or property was revoked from the system or user.
     */
    private String eventCategory;

    /**
     *Additional detailed information about the event not contained within any other attribute field of the event record.
     */
    private String eventMessage;

    /**
     *Fully qualified name of the system where the event originated; formatted as:
     * o systemname.domainname.
     */
    private String sourceHostName;

    /**
     *IP Address where the event originated; formatted as:
     * o 10.1.1.1 (version 4 standard)
     * o 2001:0db8:0000:0042:0000:8a2e:0370:7334 (version 6 standard)
     */
    private String sourceIPAddress;

    /**
     *Fully qualified name of the system where the event terminated; formatted as:
     * o systemname.domainname.
     */
    private String destinationHostName;

    /**
     *IP Address where the event originated; formatted as:
     *o 10.1.1.1 (version 4 standard)
     *o 2001:0db8:0000:0042:0000:8a2e:0370:7334 (version 6 standard)
     */
    private String destinationIPAddress;

    @JsonProperty("APPNAME")
    public String getAppName() {
        return appName == null ? Logger.DEFAULT_APP_NAME : this.appName;
    }

    @JsonProperty("UID")
    public String getUniqueIdentifier() {
        return stringNullChecker(uniqueIdentifier);
    }

    @JsonProperty("LTSP")
    public Date getLogTimeStamp() {
        return dateNullChecker(logTimeStamp);
    }

    @JsonProperty("ELCS")
    public String getEventLogClassification() {
        return stringNullChecker(eventLogClassification);
    }

    @JsonProperty("ETSP")
    public Date getEventTimestamp() {
        return dateNullChecker(eventTimestamp);
    }

    @JsonProperty("ETYP")
    public String getEventType() {
        return stringNullChecker(eventType);
    }

    @JsonProperty("EPRI")
    public String getEventPriority() {
        return stringNullChecker(eventPriority);
    }

    @JsonProperty("ECAT")
    public String getEventCategory() {
        return stringNullChecker(eventCategory);
    }

    @JsonProperty("EMSG")
    public String getEventMessage() {
        return stringNullChecker(eventMessage);
    }

    @JsonProperty("SHST")
    public String getSourceHostName() {
        return stringNullChecker(sourceHostName);
    }

    @JsonProperty("SIPA")
    public String getSourceIPAddress() {
        return stringNullChecker(sourceIPAddress);
    }


    @JsonProperty("DHST")
    public String getDestinationHostName() {
        return stringNullChecker(destinationHostName);
    }

    @JsonProperty("DIPA")
    public String getDestinationIPAddress() {
        return stringNullChecker(destinationIPAddress);
    }

    public String stringNullChecker(final String val) {
        return val != null ? val : "NULL";
    }

    public Date dateNullChecker(final Date val) {
        return  val != null ? val : new Date();
    }
}
