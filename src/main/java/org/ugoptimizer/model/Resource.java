package org.ugoptimizer.model;

/**
 * Shared domain model for an emergency response resource available on campus.
 *
 * <p>Examples include security patrol teams, ambulances, fire service units
 * and campus safety officers. Each resource carries the attributes the greedy
 * assignment algorithm needs: availability, resource type, estimated response
 * time and current workload. Two resources are considered equal when their
 * resource identifiers match.</p>
 */
public class Resource {

    /** Resource type for ambulances. */
    public static final String TYPE_AMBULANCE = "Ambulance";
    /** Resource type for fire service units. */
    public static final String TYPE_FIRE_UNIT = "Fire Unit";
    /** Resource type for security patrol teams. */
    public static final String TYPE_SECURITY_PATROL = "Security Patrol";

    private String id;
    private String type;
    private String status;
    private String currentLocation;
    private boolean available;
    private int responseTime;
    private int currentWorkload;

    /**
     * Constructs a new resource.
     *
     * @param id              the unique resource identifier, e.g. {@code AMB001}
     * @param type            the type of resource, e.g. {@link #TYPE_AMBULANCE}
     * @param status          the operational status, e.g. {@code IDLE}
     * @param currentLocation the current location of the resource
     * @param available       whether the resource can be assigned right now
     * @param responseTime    the estimated response time in minutes
     * @param currentWorkload the number of active assignments carried
     */
    public Resource(String id, String type, String status, String currentLocation,
                    boolean available, int responseTime, int currentWorkload) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.currentLocation = currentLocation;
        this.available = available;
        this.responseTime = responseTime;
        this.currentWorkload = currentWorkload;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getCurrentWorkload() {
        return currentWorkload;
    }

    public void setCurrentWorkload(int currentWorkload) {
        this.currentWorkload = currentWorkload;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Resource that)) {
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
        return "Resource{id='" + id + '\''
                + ", type='" + type + '\''
                + ", status='" + status + '\''
                + ", currentLocation='" + currentLocation + '\''
                + ", available=" + available
                + ", responseTime=" + responseTime
                + ", currentWorkload=" + currentWorkload
                + '}';
    }
}
