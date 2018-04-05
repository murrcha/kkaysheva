package ru.job4j.array;

/**
 * ArrayMerge - слияние двух массивов в один
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayMerge {

    /**
     * Method merge - слияние двух отсортированных массивов в один отсортированный
     * @param firstArray - первый массив
     * @param secondArray - второй массив
     * @return отсортированный общий массив
     */
    public int[] merge(int[] firstArray, int[] secondArray) {
        int[] result = new int[firstArray.length + secondArray.length];
        int firstIndex = 0;
        int secondIndex = 0;
        for (int index = 0; index < result.length; index++) {
            if (firstArray[firstIndex] < secondArray[secondIndex]) {
                result[index] = firstArray[firstIndex];
                firstIndex++;
            } else {
                result[index] = secondArray[secondIndex];
                secondIndex++;
            }
            if (firstIndex == firstArray.length) {
                System.arraycopy(secondArray, secondIndex, result, ++index, secondArray.length - secondIndex);
                break;
            }
            if (secondIndex == secondArray.length) {
                System.arraycopy(firstArray, firstIndex, result, ++index, firstArray.length - firstIndex);
                break;
            }
        }
        return result;
    }
}
