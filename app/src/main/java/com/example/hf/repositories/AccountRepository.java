package com.example.hf.repositories;

import com.example.hf.util.Helper;
import com.example.hf.views.data.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepository {

  private static List<Account> srcAccounts = new ArrayList<>();

  static {
    srcAccounts.add(new Account(1, "John", "Doe", Helper.strToDate("01/01/1990"),
        "001 abc street, Happy city", "johndoe@example.com", "1111111111",
        Helper.strToDate("12/20/2019")));
    srcAccounts.add(new Account(2, "Jane", "Doe", Helper.strToDate("01/02/1990"),
        "002 xyz street, Sad city", "janedoe@example.com", "2222222222",
        Helper.strToDate("12/19/2019")));
  }

  /**
   * get Account by Id
   *
   * @param id
   * @return
   */
  public Account getById(int id) {
    Account foundAccount = srcAccounts.stream().filter(account -> id == account.getId())
        .collect(Helper.toSingleton());
    return foundAccount;
  }

}
