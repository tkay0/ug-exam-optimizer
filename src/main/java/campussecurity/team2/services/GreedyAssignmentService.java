package campussecurity.team2.services;

import campussecurity.team2.algorithms.GreedyAssignment;
import campussecurity.team2.models.Incident;
import campussecurity.team2.models.Resource;

/**
 * Service facade for the greedy resource assignment flow.
 *
 * <p>This class is the stable entry point used by the rest of the system. It
 * is responsible only for <b>validating inputs</b>, delegating the decision to
 * {@link GreedyAssignment}, and returning the assigned resource. It deliberately
 * contains <b>no algorithm logic</b> — all selection rules live in
 * {@link GreedyAssignment}.</p>
 */
public class GreedyAssignmentService {

    /**
     * Assigns the best available resource to {@code incident}.
     *
     * <p>Invalid inputs (a {@code null} incident or a {@code null} resource
     * array) are handled gracefully by returning {@code null}. A {@code null}
     * return also means no suitable resource exists.</p>
     *
     * @param incident  the incident requiring a resource; may be {@code null}
     * @param resources the candidate resources; may be {@code null}
     * @return the assigned resource, or {@code null} if none could be assigned
     */
    public Resource assign(Incident incident, Resource[] resources) {
        if (incident == null || resources == null) {
            return null;
        }
        return GreedyAssignment.assignBestResource(incident, resources);
    }
}
