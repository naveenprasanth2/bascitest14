package org.example.tesco.tesco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@AllArgsConstructor
public class FindShifts {

    //    public List<List<Integer>> findShifts(Employee employee, int startTime, int endTime) {
//        List<List<Integer>> shifts = new ArrayList<>();
//        List<Department> departments = employee.getDepartments().stream()
//                .sorted(Comparator.comparingInt(Department::getStartTime))
//                .toList();
//        int tempTime = startTime;
//        List<Integer> tempList;
//        for (int i = 0; i < departments.size(); i++) {
//            tempList = new ArrayList<>();
//            if ((departments.get(i).getStartTime() >= tempTime) && (departments.get(i).getEndTime() <= endTime)) {
//                tempList.add(tempTime);
//                tempTime++;
//            }else {
//                shifts.add(tempList);
//            }
//        }
//        return shifts;
//    }
    public List<List<Integer>> findShifts(Employee employee, int startTime, int endTime) {
        List<List<Integer>> shifts = new ArrayList<>();
        List<Integer> shiftTimings = IntStream.rangeClosed(startTime, endTime).boxed().toList();
        List<Department> departments = employee.getDepartments().stream()
                .sorted(Comparator.comparingInt(Department::getStartTime))
                .toList();
//        System.out.println(shiftTimings);
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            if (shiftTimings.contains(departments.get(i).getStartTime())
                    && shiftTimings.contains(departments.get(i).getEndTime())) {
                tempList = IntStream.rangeClosed(departments.get(i).getStartTime(), departments.get(i).getEndTime()).boxed().toList();
            }
            shifts.add(tempList);
        }
        //8,9,10 ,, 10,11,12,, 14,15,16,17,18,19
        List<Integer> timings = shifts.stream().flatMap(Collection::stream).distinct().toList();
        //[8,9,10,11,12],[14,15,16,17,18,19]
        int start = timings.getFirst();
        List<List<Integer>> finalList = new ArrayList<>();
        for (int i = 1; i < timings.size(); i++) {
            if ((timings.get(i - 1) + 1 != timings.get(i))) {
                finalList.add(IntStream.of(start, timings.get(i - 1)).boxed().toList());
                start = timings.get(i);
            }
        }
        //[8,12]
        return finalList;
    }
}
