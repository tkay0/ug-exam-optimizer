# Dataset Provenance and Localisation Note

## Project
University of Ghana Campus Security and Emergency Response Optimizer

## Purpose
Coursework seed data for graph routing, reachability, MST algorithms, queue/priority scheduling, searching, sorting, resource assignment, database loading, testing, and later performance experiments.

## Real/localised content
`locations.csv` uses real University of Ghana/Legon place and facility names verified from University of Ghana web pages and the University-affiliated Radio Univers campus guide. Every location row includes a `sourceUrl`.

## Synthetic content
The following are deliberately synthetic:
- local X/Y coordinates;
- road/link connectivity, distances, travel times, traffic levels and road-condition weights;
- resource units and availability states;
- service requests, urgency, deadlines and statuses;
- audit events.

They are not live operational data.

## Privacy
No student/staff names, victim details, phone numbers, medical details, or other personal data are included. Resource names are generic unit labels.

## Coordinates and roads
The project brief permits latitude/longitude or local coordinates. This dataset uses schematic local coordinates in metres with the Main University Gate as a project origin. They are not surveyed GPS data.

The 100 roads are synthetic graph links derived from the schematic model. They are suitable for coursework algorithms, not real navigation.

## Algorithm-run dataset
`algorithm_runs.csv` contains 30 planned benchmark rows. `timeNs`, `memoryKb`, and `dateRun` are intentionally blank. Fill them only from real runs of the team's Java implementation. Do not submit fabricated timings as empirical evidence.

## Official template alignment
The first columns of `locations.csv`, `roads.csv`, `service_requests.csv`, and `resources.csv` use the exact lecturer-supplied template names and ordering. Existing project-specific extension columns follow those required columns. `algorithm_runs.csv`, `road_scenarios.csv`, and `audit_events.csv` retain their purpose-specific project schemas.

## Sources
- https://www.ug.edu.gh/campus/main
- https://old1.ug.edu.gh/about/overview
- https://univers.ug.edu.gh/finding-your-way-on-the-streets-of-legon-a-level-100-guide/
- https://www.ug.edu.gh/aad/accomodation
- https://old1.ug.edu.gh/academics/colleges
- https://law.ug.edu.gh/about-us
- https://www.ug.edu.gh/careers/contact-us
- https://www.ug.edu.gh/announcement/vacancies-senior-level-administrative-positions-safety-and-security-services
- https://ugcs.ug.edu.gh/service-catalogue/academic-computing/design-library-systems-and-research

## Generation date
2026-08-08


## Road-model upgrade
The baseline `roads.csv` now contains:
- a descriptive `routeLabel` constructed from the endpoint location names;
- varied synthetic `travel_time_min` values based on route distance, baseline traffic class, and a deterministic junction/turn delay;
- a derived `routingWeight = travel_time_min × condition_weight`.

The route labels are descriptive project labels, not claims about official street names.

## Controlled road scenarios
`road_scenarios.csv` contains 12 fictional what-if records across three scenarios:
- `ACCESS_BLOCKAGE_DRILL` — temporary blocked links chosen so the 50-location graph remains connected;
- `EVENT_CROWD` — elevated travel-time multipliers;
- `RAINY_EVENING` — increased condition/travel penalties.

These scenarios let the team test rerouting and changing constraints without corrupting the baseline seed graph.
