package org.ugoptimizer.model;

/**
 * Shared domain model for a campus security service request (incident)
 * reported to the Emergency Response Optimizer.
 *
 * <p>Represents a request such as a medical emergency, fire outbreak, theft,
 * assault, lost property report, or traffic accident. The type is what drives
 * resource matching: {@link #matchesResourceType(String)} maps a request type
 * to the resource type that can respond to it.</p>
 */
public class ServiceRequest {

    /** Type for medical emergencies, handled by an ambulance. */
    public static final String TYPE_MEDICAL = "Medical Emergency";
    /** Type for fire outbreaks, handled by a fire unit. */
    public static final String TYPE_FIRE = "Fire Outbreak";
    /** Type for theft reports, handled by a security patrol. */
    public static final String TYPE_THEFT = "Theft";
    /** Type for assault reports, handled by a security patrol. */
    public static final String TYPE_ASSAULT = "Assault";
    /** Type for lost property reports, handled by a security patrol. */
    public static final String TYPE_LOST_PROPERTY = "Lost Property";
    /** Type for traffic accidents, handled by a security patrol. */
    public static final String TYPE_TRAFFIC = "Traffic Accident";

    private String id;
    private String type;
    private String severity;
    private String location;
    private String status;
    private String timestamp;

    /**
     * Constructs a new service request.
     *
     * @param id        the unique request identifier, e.g. {@code INC001}
     * @param type      the type of request, e.g. {@link #TYPE_MEDICAL}
     * @param severity  the severity level, e.g. {@code HIGH}
     * @param location  the campus location where the request occurred
     * @param status    the current lifecycle status, e.g. {@code OPEN}
     * @param timestamp the time the request was reported
     */
    public ServiceRequest(String id, String type, String severity,
                          String location, String status, String timestamp) {
        this.id = id;
        this.type = type;
        this.severity = severity;
        this.location = location;
        this.status = status;
        this.timestamp = timestamp;
    }

    /**
     * Determines whether a resource of the given type can respond to this
     * request.
     *
     * <p>The mapping used by the dispatch system is:</p>
     * <ul>
     *   <li>Medical Emergency / Medical → {@code Ambulance}</li>
     *   <li>Fire Outbreak / Fire → {@code Fire Unit}</li>
     *   <li>Theft, Assault, Lost Property, Traffic Accident →
     *       {@code Security Patrol}</li>
     * </ul>
     * <p>Comparison is case-insensitive and trims surrounding whitespace.
     * Unknown request types match no resource type.</p>
     *
     * @param resourceType the resource type to test; may be {@code null}
     * @return {@code true} if a resource of {@code resourceType} can respond
     */
    public boolean matchesResourceType(String resourceType) {
        if (resourceType == null || type == null) {
            return false;
        }
        String normalizedType = type.trim();
        String normalizedResourceType = resourceType.trim();
        if (matchesAny(normalizedType, TYPE_MEDICAL, "Medical")) {
            return normalizedResourceType.equalsIgnoreCase("Ambulance");
        }
        if (matchesAny(normalizedType, TYPE_FIRE, "Fire")) {
            return normalizedResourceType.equalsIgnoreCase("Fire Unit");
        }
        if (matchesAny(normalizedType, TYPE_THEFT, TYPE_ASSAULT, TYPE_LOST_PROPERTY, TYPE_TRAFFIC)) {
            return normalizedResourceType.equalsIgnoreCase("Security Patrol");
        }
        return false;
    }

    /**
     * Checks whether {@code value} equals any of the supplied candidates,
     * ignoring case.
     *
     * @param value      the value to compare
     * @param candidates the accepted values
     * @return {@code true} if the value matches at least one candidate
     */
    private static boolean matchesAny(String value, String... candidates) {
        for (String candidate : candidates) {
            if (value.equalsIgnoreCase(candidate)) {
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceRequest that)) {
            return false;
        }
        if (id == null) {
            return that.id == null;
        }
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public String toString() {
        return "ServiceRequest{id='" + id + '\''
                + ", type='" + type + '\''
                + ", severity='" + severity + '\''
                + ", location='" + location + '\''
                + ", status='" + status + '\''
                + ", timestamp='" + timestamp + '\''
                + '}';
    }
}
