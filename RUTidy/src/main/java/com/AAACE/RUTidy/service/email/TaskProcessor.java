package com.AAACE.RUTidy.service.email;

import java.util.*;
import java.util.stream.Collectors;
import com.AAACE.RUTidy.model.Task;

public class TaskProcessor {

    public Map<Integer, Map<String, List<Task>>> processTasks(List<Task> tasks) {
        // Group by Group ID with explicit casting
        Map<Integer, List<Task>> groupedByGroup = tasks.stream()
            .collect(Collectors.groupingBy(task -> (Integer) task.getGroup().getGroupID()));

        Map<Integer, Map<String, List<Task>>> groupedAndSorted = new HashMap<>();
        for (Map.Entry<Integer, List<Task>> groupEntry : groupedByGroup.entrySet()) {
            Map<String, List<Task>> sortedByStatus = groupEntry.getValue().stream()
                .collect(Collectors.groupingBy(Task::getStatus,
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            list.sort(Comparator.comparing(Task::getDueDate)
                                                .thenComparing(Task::getPriority));
                            return list;
                        }
                    )));
            groupedAndSorted.put(groupEntry.getKey(), sortedByStatus);
        }

        return groupedAndSorted;
    }
}

