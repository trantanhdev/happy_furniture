package com.example.hf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Helper {

  /**
   * parse a string to a Date
   *
   * @param str
   * @return
   */
  public static Date strToDate(String str) {
    try {
      return new SimpleDateFormat("MM/dd/yyyy").parse(str);
    } catch (Exception ex) {
      return null;
    }
  }

  /**
   * using stream to get only one
   *
   * @param <T>
   * @return
   */
  public static <T> Collector<T, ?, T> toSingleton() {
    return Collectors.collectingAndThen(
        Collectors.toList(),
        list -> {
          if (list.size() != 1) {
            throw new IllegalStateException();
          }
          return list.get(0);
        }
    );
  }
}
