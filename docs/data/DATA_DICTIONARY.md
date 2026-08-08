# Data Dictionary

The first columns in each of the four core datasets match the lecturer-supplied CSV templates exactly. Project-specific extension columns follow the required columns.

## locations.csv

| Column | Type | Required | Meaning | Constraint |
|---|---|---|---|---|
| location_id | INTEGER | Yes | Unique graph/database location ID. | Positive unique integer. |
| name | TEXT | Yes | Verified/localised place or facility name. | Non-empty. |
| area | TEXT | Yes | Project zone label. | Academic Zone / Central Campus / Central Residential / Health Sciences Zone / Legon Hill / Main Entrance / North Campus / South Campus / South Residential / Sports Zone. |
| location_type | TEXT | Yes | Location category. | ACADEMIC / ACADEMIC_ADMIN / ADMIN / ASSEMBLY / COMMERCIAL / EDUCATION / EMERGENCY / GATE / GUEST_SERVICES / HEALTH / LANDMARK / LIBRARY / RECREATION / RELIGIOUS / RESEARCH / RESIDENCE / SECURITY / STUDENT_SUPPORT / TECH_SUPPORT. |
| x_coord | INTEGER | Yes | Synthetic local X coordinate in metres. | Schematic only; not GPS. |
| y_coord | INTEGER | Yes | Synthetic local Y coordinate in metres. | Schematic only; not GPS. |
| operatingHours | TEXT | No | Typical/project operating-hours constraint. | Informational project value. |
| sourceUrl | TEXT | Yes | Web source used to verify place/facility name. | UG or University-affiliated source. |
| coordinateNote | TEXT | Yes | Clarifies coordinate status. | Must not claim survey accuracy. |

## roads.csv

| Column | Type | Required | Meaning | Constraint |
|---|---|---|---|---|
| road_id | INTEGER | Yes | Unique baseline road/link ID. | Positive unique integer. |
| from_location_id | INTEGER | Yes | First endpoint. | FK -> locations.location_id. |
| to_location_id | INTEGER | Yes | Second endpoint. | FK -> locations.location_id; different from from_location_id. |
| distance_km | DECIMAL | Yes | Synthetic route distance in kilometres. | Positive. |
| travel_time_min | DECIMAL | Yes | Synthetic baseline vehicle travel time in minutes. | Positive; varies by distance/traffic/junction delay. |
| condition_weight | DECIMAL | Yes | Baseline road-condition penalty multiplier. | Positive finite. |
| routeLabel | TEXT | Yes | Synthetic descriptive label made from the two endpoint names. | Not an official street/road name. |
| routingWeight | DECIMAL | Yes | Derived weight = travel_time_min × condition_weight. | Positive finite. |
| roadType | TEXT | No | Synthetic campus-road category. | ACCESS_ROAD / CAMPUS_ROAD / MAIN_ROAD / RESIDENTIAL_ROAD. |
| trafficLevel | TEXT | No | Synthetic baseline traffic label. | LOW / MODERATE / HIGH. |
| isBlocked | BOOLEAN | No | Baseline blockage state. | False in seed data; overrides live in road_scenarios.csv. |
| dataNote | TEXT | Yes | Clarifies synthetic/navigation status. | Not navigation-grade. |

## road_scenarios.csv

| Column | Type | Required | Meaning | Constraint |
|---|---|---|---|---|
| scenarioId | INTEGER | Yes | Unique scenario-row ID. | Positive unique integer. |
| scenarioName | TEXT | Yes | Named what-if scenario. | ACCESS_BLOCKAGE_DRILL / EVENT_CROWD / RAINY_EVENING. |
| roadId | INTEGER | Yes | Affected baseline road. | FK -> roads.road_id. |
| routeLabel | TEXT | Yes | Human-readable affected route. | Copied from roads.csv. |
| scenarioStart | TIMESTAMP | Yes | Synthetic UTC start. | ISO-8601. |
| scenarioEnd | TIMESTAMP | Yes | Synthetic UTC end. | After scenarioStart. |
| isBlockedOverride | BOOLEAN | Yes | Scenario blockage override. | True only for blockage drill rows. |
| conditionWeightMultiplier | DECIMAL | Yes | Multiplier applied to baseline condition weight. | Positive. |
| travelTimeMultiplier | DECIMAL | Yes | Multiplier applied to baseline travel time. | Positive. |
| reason | TEXT | Yes | Why the synthetic scenario changes the road. | No claim of a real incident. |
| dataNote | TEXT | Yes | Clarifies that scenario is fictional. | Baseline roads remain unchanged. |

## resources.csv

| Column | Type | Required | Meaning | Constraint |
|---|---|---|---|---|
| resource_id | INTEGER | Yes | Unique resource ID. | Positive unique integer. |
| resource_type | TEXT | Yes | Resource category. | AMBULANCE / CCTV_TECHNICIAN / CROWD_CONTROL_TEAM / FIRE_RESPONSE_UNIT / FIRST_AID_TEAM / INVESTIGATION_TEAM / MOTORCYCLE_PATROL / PATROL_OFFICER / PATROL_VEHICLE / RAPID_RESPONSE_TEAM. |
| home_location_id | INTEGER | Yes | Normal base location. | FK -> locations.location_id. |
| capacity | INTEGER | Yes | Project capacity measure. | Positive. |
| availability_status | TEXT | Yes | Availability state. | AVAILABLE / BUSY / OFF_DUTY / MAINTENANCE. |
| resourceName | TEXT | No | Synthetic unit label. | No personal data. |
| currentLocation | INTEGER | No | Current project location. | FK -> locations.location_id. |
| shiftStart | TEXT | No | Synthetic shift start. | HH:MM. |
| shiftEnd | TEXT | No | Synthetic shift end. | HH:MM. |
| dataNote | TEXT | Yes | Confirms synthetic status. | No live operational asset implied. |

## service_requests.csv

| Column | Type | Required | Meaning | Constraint |
|---|---|---|---|---|
| request_id | INTEGER | Yes | Unique request ID. | Positive unique integer. |
| source_location_id | INTEGER | Yes | Dispatch/request origin. | FK -> locations.location_id. |
| destination_location_id | INTEGER | Yes | Target/incident location. | FK -> locations.location_id. |
| category | TEXT | Yes | Synthetic security/emergency request category. | ACCESS_CONTROL / CCTV_FAULT / CROWD_CONTROL / EMERGENCY_TRANSPORT / FIRE_ALARM / MEDICAL_EMERGENCY / NIGHT_PATROL_REQUEST / ROAD_OBSTRUCTION / SECURITY_ESCORT / SUSPICIOUS_ACTIVITY / THEFT_REPORT / WELFARE_CHECK. |
| urgency | INTEGER | Yes | Urgency from 1 to 5. | 1..5. |
| time_submitted | TIMESTAMP | Yes | Synthetic UTC submission time. | ISO-8601. |
| deadline | TIMESTAMP | Yes | Synthetic target response deadline. | After time_submitted. |
| status | TEXT | Yes | Request status. | PENDING / ASSIGNED / IN_PROGRESS / COMPLETED / CANCELLED. |
| requiredResourceType | TEXT | No | Preferred resource class. | AMBULANCE / CCTV_TECHNICIAN / CROWD_CONTROL_TEAM / FIRE_RESPONSE_UNIT / INVESTIGATION_TEAM / MOTORCYCLE_PATROL / PATROL_OFFICER / PATROL_VEHICLE / RAPID_RESPONSE_TEAM. |
| peopleAffected | INTEGER | No | Synthetic prioritisation count. | >=1. |
| priorityScore | INTEGER | No | Synthetic helper score. | 0..100. |
| description | TEXT | No | Generic fictional request description. | Not a real incident. |
| dataNote | TEXT | Yes | Confirms fictional nature. | No personal data. |

## algorithm_runs.csv

| Column | Type | Required | Meaning | Constraint |
|---|---|---|---|---|
| runId | INTEGER | Yes | Planned experiment-run ID. | 1..30 in current plan. |
| algorithmName | TEXT | Yes | Algorithm to benchmark. | BFS / DIJKSTRA / KRUSKAL. |
| inputSize | INTEGER | Yes | Planned size. | Positive. |
| timeNs | INTEGER | Final evidence | Measured runtime in nanoseconds. | Blank until actual Java benchmark. |
| memoryKb | DECIMAL | Final evidence | Measured/estimated memory in KB. | Blank until actual Java benchmark. |
| dateRun | DATE/TIMESTAMP | Final evidence | Actual experiment date. | Blank until measured. |
| status | TEXT | No | Benchmark status. | PLANNED / MEASURED; current rows are PLANNED. |
| experimentGroup | TEXT | No | Groups repeated runs. | Algorithm_size. |
| runNumber | INTEGER | No | Repeat number. | 1..3. |
| dataNote | TEXT | Yes | Prevents placeholders being treated as empirical evidence. | Replace after real benchmarking. |

## audit_events.csv

| Column | Type | Required | Meaning | Constraint |
|---|---|---|---|---|
| eventId | INTEGER | Yes | Unique audit event ID. | Positive unique integer. |
| eventType | TEXT | Yes | Synthetic event category. | REQUEST_ASSIGNED / REQUEST_CREATED / REQUEST_STATUS_CHANGED / RESOURCE_STATUS_CHANGED / ROAD_BLOCK_UPDATED / UNDO_ACTION. |
| timestamp | TIMESTAMP | Yes | Synthetic UTC event time. | ISO-8601. |
| entityType | TEXT | Yes | Referenced entity category. | SERVICE_REQUEST/RESOURCE/ROAD. |
| entityId | INTEGER | Yes | Referenced synthetic entity ID. | Must exist in target table. |
| actorType | TEXT | No | Synthetic actor category. | SYSTEM / DISPATCH_OPERATOR; no personal identity. |
| details | TEXT | No | Generic project event description. | Synthetic. |
| dataNote | TEXT | Yes | Confirms synthetic nature. | No personal data. |
