package ru.job4j.sort;

import java.util.*;
import java.util.regex.Pattern;

/**
 * SortDepartment
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class SortDepartment {

    /**
     * Method sortAscending
     * @param departmentArray
     * @return
     */
    public String[] sortAscending(String[] departmentArray) {
        List<String> sourceList = Arrays.asList(departmentArray);
        Set<String> resultTreeSet = new TreeSet<>();
        for (String departmentString : sourceList) {
            String[] department = departmentString.split(Pattern.quote("\\"));
            StringBuilder temp = new StringBuilder();
            for (int index = 0; index < department.length; index++) {
                temp.append(department[index]).append("\\");
                resultTreeSet.add(temp.substring(0, temp.length() - 1));
            }
        }
        return resultTreeSet.toArray(new String[resultTreeSet.size()]);
    }

    /**
     * Method sortDescending
     * @param departmentArray
     * @return
     */
    public String[] sortDescending(String[] departmentArray) {
        List<String> sourceList = Arrays.asList(this.sortAscending(departmentArray));
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String left, String right) {
                int result = Integer.compare(left.length(), right.length());
                return result == 0 ? right.compareTo(left) : result;
            }
        };
        List<List<String>> partsList = this.splitByRoot(sourceList);
        Collections.reverse(partsList);
        List<String> resultList = new ArrayList<>();
        for (List<String> part : partsList) {
            Collections.sort(part, comparator);
            resultList.addAll(part);
        }
        return resultList.toArray(new String[sourceList.size()]);
    }

    /**
     * Method splitByRoot - делит список отделов по корневому отделу на подсписки
     * @param list - отсортированный по возрастанию список отделов
     * @return список подсписков
     */
    private List<List<String>> splitByRoot(List<String> list) {
        List<String> source = new ArrayList<>(list);
        List<List<String>> result = new ArrayList<>();
        List<String> partByRoot = new ArrayList<>();
        String root = list.get(0);
        source.add(list.get(0));
        for (String department : source) {
            if (department.startsWith(root)) {
                partByRoot.add(department);
            } else {
                List<String> part = new ArrayList<>(partByRoot);
                result.add(part);
                partByRoot.clear();
                root = department;
                partByRoot.add(root);
            }
        }
        return result;
    }
}
