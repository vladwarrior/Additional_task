package org.bsuir.task.repository;

import org.bsuir.task.entity.Group;

import java.util.*;

public class GroupRepository {
    //Thread safe singleton with Initialization On-Demand Holder
    //source: https://avaldes.com/creating-a-thread-safe-singleton-class-with-examples/
    private final Map<Integer, LinkedList<Group>> storage;

    private static class Holder {
        private static final GroupRepository instance = new GroupRepository();
    }

    private GroupRepository() {
        storage = new HashMap<>();
    }

    public static GroupRepository getInstance() {
        return Holder.instance;
    }

    public List<Group> getGroups() {
        Map<Integer, LinkedList<Group>> copy = Map.copyOf(storage);
        List<Group> result = new ArrayList<>();

        for (LinkedList<Group> list : copy.values()) {
            result.addAll(list);
        }

        return result;
    }

    public List<Group> getGroupsByCourse(int courseNumber) {
        Map<Integer, LinkedList<Group>> copy = Map.copyOf(storage);

        if (copy.containsKey(courseNumber)) {
            List<Integer> keys = new ArrayList<>(copy.keySet());

            for (Integer integer : keys) {
                if (integer == courseNumber) {
                    return copy.get(integer);
                }
            }
        }

        return null;
    }

    public boolean removeGroup(Group groupToRemove) {
        for (LinkedList<Group> list : storage.values()) {
            if (list.contains(groupToRemove)) {
                return list.remove(groupToRemove);
            }
        }
        return false;
    }

    public boolean addGroup(Group group) {
        int courseNumber = group.getCourseNumber();

        if (storage.containsKey(courseNumber)) {
            List<Integer> keys = new ArrayList<>(storage.keySet());

            for (Integer integer : keys) {
                if (integer == courseNumber) {
                    return storage.get(integer).add(group);
                }
            }
        }
        return false;
    }
}
