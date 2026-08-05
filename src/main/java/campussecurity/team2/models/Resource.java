package campussecurity.team2.models;

/**
 * Domain model for an emergency response resource available on campus.
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

    private String resourceId;
    private String resourceType;
    private String location;
    private boolean available;
    private int responseTime;
    private int currentWorkload;
    private String status;

    /**
     * Constructs a new resource.
     *
     * @param resourceId     the unique resource identifier, e.g. {@code AMB001}
     * @param resourceType   the type of resource, e.g. {@link #TYPE_AMBULANCE}
     * @param location       the current location of the resource
     * @param available      whether the resource can be assigned right now
     * @param responseTime   the estimated response time in minutes
     * @param currentWorkload the number of active assignments carried
     * @param status         the operational status, e.g. {@code IDLE}
     */
    public Resource(String resourceId, String resourceType, String location,
                    boolean available, int responseTime, int currentWorkload,
                    String status) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.location = location;
        this.available = available;
        this.responseTime = responseTime;
        this.currentWorkload = currentWorkload;
        this.status = status;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        if (!(other instanceof Resource that)) {
            return false;
        }
        if (resourceId == null) {
            return that.resourceId == null;
        }
        return resourceId.equals(that.resourceId);
    }

    @Override
    public int hashCode() {
        return resourceId == null ? 0 : resourceId.hashCode();
    }

    @Override
    public String toString() {
        return "Resource{resourceId='" + resourceId + '\''
                + ", resourceType='" + resourceType + '\''
                + ", location='" + location + '\''
                + ", available=" + available
                + ", responseTime=" + responseTime
                + ", currentWorkload=" + currentWorkload
                + ", status='" + status + '\''
                + '}';
    }
}
