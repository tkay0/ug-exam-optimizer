package campussecurity.team2.models;

/**
 * Domain model for a campus security incident reported to the Emergency
 * Response Optimizer.
 *
 * <p>Represents an incident such as a medical emergency, fire outbreak, theft,
 * assault, lost property report, or traffic accident. The type is what drives
 * resource matching: {@link #matchesResourceType(String)} maps an incident
 * type to the resource type that can respond to it.</p>
 */
public class Incident {

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

    private String incidentId;
    private String incidentType;
    private String priority;
    private String location;
    private String status;

    /**
     * Constructs a new incident.
     *
     * @param incidentId   the unique incident identifier, e.g. {@code INC001}
     * @param incidentType the type of incident, e.g. {@link #TYPE_MEDICAL}
     * @param priority     the dispatch priority, e.g. {@code HIGH}
     * @param location     the campus location where the incident occurred
     * @param status       the current lifecycle status, e.g. {@code OPEN}
     */
    public Incident(String incidentId, String incidentType, String priority,
                    String location, String status) {
        this.incidentId = incidentId;
        this.incidentType = incidentType;
        this.priority = priority;
        this.location = location;
        this.status = status;
    }

    /**
     * Determines whether a resource of the given type can respond to this
     * incident.
     *
     * <p>The mapping used by the dispatch system is:</p>
     * <ul>
     *   <li>Medical Emergency / Medical → {@code Ambulance}</li>
     *   <li>Fire Outbreak / Fire → {@code Fire Unit}</li>
     *   <li>Theft, Assault, Lost Property, Traffic Accident →
     *       {@code Security Patrol}</li>
     * </ul>
     * <p>Comparison is case-insensitive and trims surrounding whitespace.
     * Unknown incident types match no resource type.</p>
     *
     * @param resourceType the resource type to test; may be {@code null}
     * @return {@code true} if a resource of {@code resourceType} can respond
     */
    public boolean matchesResourceType(String resourceType) {
        if (resourceType == null || incidentType == null) {
            return false;
        }
        String normalizedType = incidentType.trim();
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
     * @param value       the value to compare
     * @param candidates  the accepted values
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

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Incident that)) {
            return false;
        }
        if (incidentId == null) {
            return that.incidentId == null;
        }
        return incidentId.equals(that.incidentId);
    }

    @Override
    public int hashCode() {
        return incidentId == null ? 0 : incidentId.hashCode();
    }

    @Override
    public String toString() {
        return "Incident{incidentId='" + incidentId + '\''
                + ", incidentType='" + incidentType + '\''
                + ", priority='" + priority + '\''
                + ", location='" + location + '\''
                + ", status='" + status + '\''
                + '}';
    }
}
