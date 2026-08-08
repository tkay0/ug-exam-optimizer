# UG Security Optimizer Dataset Bundle

## Required operational datasets
- `data/locations.csv` — 50 records
- `data/roads.csv` — 100 baseline road records
- `data/service_requests.csv` — 300 records
- `data/resources.csv` — 30 records

Each core CSV begins with the exact columns and ordering from the lecturer-supplied template. Project-specific extension columns follow those required columns.

## Scenario/testing datasets
- `data/road_scenarios.csv` — 12 controlled road-condition/blockage scenario records
- `data/audit_events.csv` — 60 synthetic audit/undo records

## Performance dataset
- `data/algorithm_runs.csv` — 30 PLANNED rows.
- Actual `timeNs`, `memoryKb`, and `dateRun` must come from Java performance runs.

## Road-model improvements
- `routeLabel` provides a readable endpoint-based route description.
- Baseline `travel_time_min` now varies with distance, traffic class and junction delay.
- `routingWeight` is derived from travel time and road-condition weight.
- Baseline `roads.csv` stays unblocked.
- `road_scenarios.csv` provides separate blockage/congestion/rain scenarios.

## Supporting documentation
- `DATA_DICTIONARY.md`
- `DATASET_PROVENANCE.md`
- `VALIDATION_REPORT.txt`
- `dataset_manifest.json`

## Important
Route labels, coordinates, links, travel times, conditions and scenario events are synthetic coursework data and must not be presented as official University of Ghana road/navigation information.
