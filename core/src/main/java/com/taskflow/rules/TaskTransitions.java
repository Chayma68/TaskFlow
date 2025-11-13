package com.taskflow.rules;

import com.taskflow.model.TaskStatus;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * Règles de transition autorisées entre statuts Kanban.
 * Indépendant de Spring/JPA : on peut l'appeler depuis n'importe quel service.
 */
public final class TaskTransitions {

    private static final Map<TaskStatus, Set<TaskStatus>> ALLOWED = new EnumMap<>(TaskStatus.class);

    static {
        // Définis ici les transitions permises :
        ALLOWED.put(TaskStatus.TODO,  EnumSet.of(TaskStatus.DOING));                 // TODO -> DOING
        ALLOWED.put(TaskStatus.DOING, EnumSet.of(TaskStatus.TODO, TaskStatus.DONE)); // DOING -> TODO | DONE
        ALLOWED.put(TaskStatus.DONE,  EnumSet.noneOf(TaskStatus.class));             // DONE = terminal
    }

    private TaskTransitions() {}

    /** True si la transition (from -> to) est autorisée. */
    public static boolean isAllowed(TaskStatus from, TaskStatus to) {
        return ALLOWED.getOrDefault(from, Collections.emptySet()).contains(to);
    }

    /** Valide la transition, sinon lève une IllegalStateException. */
    public static void checkOrThrow(TaskStatus from, TaskStatus to) {
        if (!isAllowed(from, to)) {
            throw new IllegalStateException("Transition interdite : " + from + " -> " + to);
        }
    }

    /** Renvoie la liste immuable des cibles autorisées pour un statut donné. */
    public static Set<TaskStatus> allowedTargets(TaskStatus from) {
        return Collections.unmodifiableSet(ALLOWED.getOrDefault(from, Collections.emptySet()));
    }
}
