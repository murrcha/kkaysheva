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
            String temp = "";
            for (int index = 0; index < department.length; index++) {
                temp += String.format("%s\\", department[index]);
                resultTreeSet.add(temp.substring(0, temp.length() - 1));
            }
        }
        return resultTreeSet.toArray(new String[resultTreeSet.size()]);
    }
}
